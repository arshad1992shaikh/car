//The Rental class represents a rental transaction between a customer and a car. 
//It contains information about the car being rented, the customer renting the car, the date the rental starts, and the date the car is returned.
//The class also includes methods for marking a car as returned and extending the rental period. Here’s a detailed explanation of the code:
package management;
//Java Packages: Packages in Java are used to group related classes. 
//The java.util package contains utility classes that are commonly used in Java programs,
//such as collections framework classes, date and time facilities, and other utility classes.
//Enables Date Handling: By importing java.util.Date, the program can create instances of the Date class,
//manipulate date objects, compare dates, and perform various date-related operations without needing to prefix Date with java.util.
import java.util.Date;//Enables Date Handling: By importing java.util.Date, the program can create instances of the Date class, manipulate date objects,
                      //compare dates, and perform various date-related operations without needing to prefix Date with java.util.

/*The Rental class represents a rental transaction between a customer and a car. It contains information about the car being rented, 
 * the customer renting the car, the date the rental starts, and the date the car is returned.
 *  The class also includes methods for marking a car as returned and extending the rental period. 
 *  Here’s a detailed explanation of the code:*/
class Rental {
    private Car car;
    private Customer customer;
    private Date rentalDate;
    private Date returnDate;

    /*The constructor initializes a new rental with the specified car, customer, and rentalDate.
       It checks if rentalDate is null or in the future, throwing an IllegalArgumentException if so.
       It sets the fields car, customer, and rentalDate.
       The returnDate is initialized to null since the car hasn't been returned yet.
       The car is marked as rented by calling this.car.markAsRented().
       The customer is updated to return the car using this.customer.returnCar(car) (this seems to be an error,).Getters*/
    public Rental(Car car, Customer customer, Date rentalDate) throws Exception {
        if (rentalDate == null || rentalDate.after(new Date())) {
            throw new IllegalArgumentException("Rental date cannot be null or in the future");
        }
        this.car = car;
        this.customer = customer;
        this.rentalDate = rentalDate;
        this.returnDate = null;
        this.car.markAsRented();
        this.customer.returnCar(car);
        
    }
    //These methods return the car, customer, rental date, and return date respectively.
	public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    /*This method sets the return date of the rental.
      It checks if the returnDate is before the rentalDate, throwing an IllegalArgumentException if so.
      It sets this.returnDate to the given returnDate.
      The car is marked as returned by calling car.markAsReturned().
      The customer is updated to return the car using customer.returnCar(car).*/
    public void returnCar(Date returnDate) {
        if (returnDate.before(rentalDate)) {
            throw new IllegalArgumentException("Return date cannot be before rental date");
        }
        this.returnDate = returnDate;
        car.markAsReturned();
        customer.returnCar(car);     
    }
    
    /*This method extends the rental period by setting a new return date.
      It checks if the newReturnDate is before the current returnDate, throwing an IllegalArgumentException if so.
      It sets this.returnDate to the new return date.*/
    public void extendRental(Date newReturnDate) {
        if (newReturnDate.before(returnDate)) {
            throw new IllegalArgumentException("New return date cannot be before the current return date.");
        }
        this.returnDate = newReturnDate;
    }
}
