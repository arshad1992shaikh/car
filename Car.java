package management;
/*
The line import java.util.Date; is a statement in Java that imports the Date class from the java.util package. Here's a detailed explanation of this line and its context:
import java.util.Date;
Purpose: This import statement makes the Date class available in your code.
Without this import statement, you would have to use the full class name (java.util.Date) every time you reference the Date class in your code.
Package: java.util is a package in Java that contains utility classes like Date, Calendar, ArrayList, HashMap, etc.
Class: Date is a class in the java.util package that represents a specific instant in time, with millisecond precision.*/
import java.util.Date;

class Car {
	
    private String licensePlate;// Stores the car's license plate number.
    private String brand;//Stores the car's brand (e.g., Toyota)
    private String model;//Stores the car's model (e.g., Corolla)
    private String color;
    private int year;
    private boolean available;//A boolean flag indicating whether the car is currently available for rent
    private Date returnDate;//Stores the date the car is expected to be returned.

    /*Car Constructor: Initializes a new Car object.
      Checks if the licensePlate is null or empty and throws an IllegalArgumentException if so.
      Checks if the year is a positive number and throws an IllegalArgumentException if not.
      Initializes the fields licensePlate, brand, model, color, and year with the provided values.
      Sets the available field to true, indicating the car is available by default when created.*/
    public Car(String licensePlate, String brand, String model, String color, int year) {
    	
        if (licensePlate == null || licensePlate.isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be null or empty");
        }
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be a positive number");
        }
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.available = true;  // Default to available when a car is created
    }
//Getters: These methods return the values of their respective fields.
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public Date getReturnDate() {
        return returnDate;
    }
    //setReturnDate: Sets the returnDate field to the provided Date object.
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
//markAsRented: Marks the car as rented by setting the available field to false.
    public void markAsRented() {
        this.available = false;
    }
//markAsReturned: Marks the car as returned by setting the available field to true and clearing the returnDate.
    public void markAsReturned() {
        this.available = true;
        this.returnDate = null;
    }
}
