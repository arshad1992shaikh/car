package management;

import java.util.Calendar;

class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private Car[] rentedCars;//An array to store the cars rented by the customer.
    private int numRentedCars;// A counter to keep track of the number of cars currently rented by the customer.

    public Customer(String customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rentedCars = new Car[10]; //Creates an array rentedCars with a fixed size of 10 to store rented cars.
        this.numRentedCars = 0;//initializes numRentedCars to 0
    }
/*Getters for customerId, name, email, and phoneNumber return their respective fields.
  getRentedCars returns the array of rented cars. 
  If rentedCars is null, it initializes it to an empty array before returning.*/
    public Car[] getRentedCars() {
        if (rentedCars == null) {
            rentedCars = new Car[0];//Ensure it's not null, provide an empty array
        }
        return rentedCars;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
/*rentCar: This method handles the process of renting a car.
It first checks if the customer already has the car rented using containsCar.
If the car is already rented by this customer, it throws an exception.
Otherwise, it calculates the return date by adding the number of rental days to the current date using a Calendar instance.
It sets the car's return date, marks the car as rented, and adds the car to the customer's list of rented cars using addCar.*/
    public void rentCar(Car car, int rentalDays) throws Exception {
        if (containsCar(car)) {
            throw new Exception("Car is already rented by this customer.");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, rentalDays);
        car.setReturnDate(calendar.getTime());
        car.markAsRented();
        addCar(car);
    }
/*containsCar: This method checks if the customer already has the specified car rented.
It iterates through the rentedCars array.
If it finds a car with the same license plate, it returns true.
Otherwise, it returns false.*/
    private boolean containsCar(Car car) {
        for (int i = 0; i < numRentedCars; i++) {
            if (rentedCars[i].getLicensePlate().equals(car.getLicensePlate())) {
                return true;
            }
        }
        return false;
    }
/*addCar: This method adds a car to the customer's list of rented cars.
It checks if there is space available in the rentedCars array.
If there is space, it adds the car to the array and increments numRentedCars.
If there is no space available, it prints an error message.*/
    private void addCar(Car car) {
        if (numRentedCars < rentedCars.length) {
            rentedCars[numRentedCars] = car;
            numRentedCars++;
        } else {
            System.out.println("Maximum number of rented cars reached. Cannot rent more.");
        }
    }
    /*returnCar: This method handles the process of returning a rented car.
It iterates through the rentedCars array.
If it finds the car to be returned, it replaces it with the last car in the array and sets the last position to null.
It decrements numRentedCars and marks the car as returned.*/
    public void returnCar(Car car) {
        for (int i = 0; i < numRentedCars; i++) {
            if (rentedCars[i].getLicensePlate().equals(car.getLicensePlate())) {
                rentedCars[i] = rentedCars[numRentedCars - 1]; // Replace the returned car with the last car in the array
                rentedCars[numRentedCars - 1] = null; // Set the last position to null
                numRentedCars--;
                car.markAsReturned();
                return;
            }
        }
    }
}
