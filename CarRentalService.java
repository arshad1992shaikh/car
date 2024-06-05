
/*Implement additional methods, such as a method to extend the rental period, considering the implications on return dates and validations.
   - Create a `CarRentalService` class to manage the overall car rental service, keeping track of all cars, customers, and rentals,
   - and ensuring no duplicate license plates or customer IDs.*/
package management;
import java.util.Calendar;
import java.util.Date;

class CarRentalService {
	//cars, customers, rentals: Arrays to store instances of Car, Customer, and Rental respectively.
    private Car[] cars;
    private Customer[] customers;
    private Rental[] rentals;
    //numCars, numCustomers, numRentals: Counters to keep track of the number of cars, customers, and rentals in the system.
    private int numCars;
    private int numCustomers;
    private int numRentals;

    public CarRentalService(int maxCars, int maxCustomers, int maxRentals) {
    	//Initializes the arrays with specified maximum sizes.
        this.cars = new Car[maxCars];
        this.customers = new Customer[maxCustomers];
        this.rentals = new Rental[maxRentals];
        this.numCars = 0;
        this.numCustomers = 0;
        this.numRentals = 0;
    }
   // Adds a Car or Customer to the respective array if there is space available.
    //Otherwise, it prints an error message indicating the array is full.
    public void addCar(Car car) {
        if (numCars < cars.length) {
            cars[numCars] = car;
            numCars++;
        } else {
            System.out.println("Maximum number of cars reached. Cannot add more.");
        }
    }

    public void addCustomer(Customer customer) {
        if (numCustomers < customers.length) {
            customers[numCustomers] = customer;
            numCustomers++;
        } else {
            System.out.println("Maximum number of customers reached. Cannot add more.");
        }
    }
    //Finds the customer and car based on the provided IDs.
    //Checks if the car is available.
    //If all conditions are met, it creates a new rental, adds it to the rentals array, and prints success messages.
    // If any condition is not met, it prints an error message.
    public void rentCar(String customerId, String licensePlate, int rentalDays) {
        Customer customer = findCustomerById(customerId);
        Car car = findCarByLicensePlate(licensePlate);

        if (customer != null && car != null && car.isAvailable()) {
            try {
                customer.rentCar(car, rentalDays);
                Rental rental = new Rental(car, customer, new Date());
                addRental(rental);
                System.out.println("Car rented until: " + car.getReturnDate());
                System.out.println("Car rented successfully.");
            } catch (Exception e) {
                System.out.println("Error renting car: " + e.getMessage());
            }
        } else {
            System.out.println("Customer or car not found, or car is not available.");
        }
    }
   /*Finds the rental based on customer ID and car license plate.
   If the rental is found, it processes the return, updating the return date and marking the car as available.
   If the rental is not found, it prints an error message.*/
    public void returnCar(String customerId, String licensePlate, Date returnDate) {
        Rental rental = findRental(customerId, licensePlate);

        if (rental != null) {
            try {
                rental.returnCar(returnDate);
                System.out.println("Car returned successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error returning car: " + e.getMessage());
            }
        } else {
            System.out.println("Rental not found for the given customer and car.");
        }
    }
    /*Finds the rental based on customer ID and car license plate.
      If the rental is found, it extends the rental period by the specified number of days.
      If the rental is not found, it prints an error message.*/
    public void extendRental(String customerId, String licensePlate, int additionalDays) {
        Rental rental = findRental(customerId, licensePlate);

        if (rental != null) {
            try {
                Date returnDate = rental.getReturnDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(returnDate);
                calendar.add(Calendar.DAY_OF_MONTH, additionalDays);
                returnDate = calendar.getTime();
                rental.extendRental(returnDate);
                System.out.println("Rental period extended successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error extending rental: " + e.getMessage());
            }
        } else {
            System.out.println("Rental not found for the given customer and car.");
        }
    }
    //This method searches for and returns a Customer object from the customers array based on a given customer ID.
    /*How it works:
       It iterates through the customers array using a for loop.
       For each Customer object, it checks if the customerId matches the given customerId.
      If a match is found, the method returns the corresponding Customer object.
      If no match is found after checking all customers, the method returns null.*/
    private Customer findCustomerById(String customerId) {
        for (int i = 0; i < numCustomers; i++) {
            if (customers[i].getCustomerId().equals(customerId)) {
                return customers[i];
            }
        }
        return null;
    }
   // This method searches for and returns a Customer object from the customers array based on a given customer ID.
    /*How it works:
      It iterates through the cars array using a for loop.
      For each Car object, it checks if the licensePlate matches the given licensePlate.
      If a match is found, the method returns the corresponding Car object.
      If no match is found after checking all cars, the method returns null.*/
    private Car findCarByLicensePlate(String licensePlate) {
        for (int i = 0; i < numCars; i++) {
            if (cars[i].getLicensePlate().equals(licensePlate)) {
                return cars[i];
            }
        }
        return null;
    }
//This method searches for and returns a Rental object from the rentals array based on a given customer ID and license plate.
    /*
     * How it works:
     It iterates through the rentals array using a for loop.
     For each Rental object, it checks if both the Car's license plate and the Customer's ID match the given values.
     If both match, the method returns the corresponding Rental object.
     If no match is found after checking all rentals, the method returns null.*/
    private Rental findRental(String customerId, String licensePlate) {
        for (int i = 0; i < numRentals; i++) {
            if (rentals[i].getCar().getLicensePlate().equals(licensePlate) &&
                rentals[i].getCustomer().getCustomerId().equals(customerId)) {
                return rentals[i];
            }
        }
        return null;
    }
//This method adds a Rental object to the rentals array.
    /*How it works:
     It first checks if there is space available in the rentals array by comparing numRentals with the length of the array.
     If there is space, it adds the given Rental object to the array at the position indicated by numRentals.
     It then increments the numRentals counter by one.
     If there is no space available (i.e., the array is full), it prints an error message indicating that the maximum number of rentals has been reached.*/
    private void addRental(Rental rental) {
        if (numRentals < rentals.length) {
            rentals[numRentals] = rental;
            numRentals++;
        } else {
            System.out.println("Maximum number of rentals reached. Cannot add more.");
        }
    }
}
