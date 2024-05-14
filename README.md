# Ticket Booking System

This is a simple Java project for managing tickets in a theater or similar venue. It provides classes for representing individuals (`Person`) and tickets (`Ticket`). 

## Person Class

The `Person` class encapsulates personal information including name, surname, and email. It offers methods to access and modify this information, as well as a utility method to print the person's details.

## Ticket Class

The `Ticket` class represents a ticket for an event, storing details such as row, seat number, price, and the passenger's information (held as a `Person` object). It includes methods for accessing and updating ticket information, along with functionality to print the ticket details and save them to a file.

### Usage

To use this code in your Java application, follow these steps:

1. **Instantiate a Person**: Create an instance of the `Person` class with the individual's details (name, surname, email).
   
2. **Create a Ticket**: Instantiate a `Ticket` object with the relevant information including row number, seat number, price, and the `Person` object representing the passenger.
   
3. **Print Ticket Information**: Utilize the `printTicketInfo()` method to display the ticket details.
   
4. **Save Ticket Information**: Optionally, use the `save()` method to store the ticket information in a file.

Here's a basic example:

```java
public class Main {
    public static void main(String[] args) {
        // Create a person
        Person person = new Person("John", "Doe", "john@example.com");

        // Create a ticket for the person
        Ticket ticket = new Ticket(1, 10, person, 25.00);

        // Print ticket information
        ticket.printTicketInfo();

        // Save ticket information to a file
        try {
            ticket.save();
            System.out.println("Ticket information saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving ticket information: " + e.getMessage());
        }
    }
}
