import java.io.FileWriter;
import java.io.IOException;

public class Ticket {

    private int row;
    private int seat;
    private double price;
    private Person passenger;


    public Ticket(int row, int seat, Person passenger, double price) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.passenger = passenger;
    }

    // Getters
    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public double getPrice() {
        return price;
    }

    public Person getPassenger() {
        return passenger;
    }

    // Setters
    public void setRow(int row) {
        this.row = row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPassenger(Person passenger) {
        this.passenger = passenger;
    }


    public void printTicketInfo() {
        System.out.println("\nTicket Information:");
        System.out.println("  - Row: " + (char)('A' + row));
        System.out.println("  - Seat: " + seat);
        System.out.printf("  - Price: $%.2f\n", price);
        System.out.println("  - Passenger Information:");
        passenger.printInfo();
    }


    // Save ticket information to a file with Row letter and Column number
    public void save() throws IOException {
        char rowLetter = (char) ('A' + row);
        String filename = String.valueOf(rowLetter) + seat + ".txt";

        try (FileWriter writer = new FileWriter(filename)) {
            // Write passenger information to the file
            writer.write("Name: " + passenger.getName() + "\n");
            writer.write("Surname: " + passenger.getSurname() + "\n");
            writer.write("Email: " + passenger.getEmail() + "\n");

            // Write ticket price
            writer.write("Price: $" + String.format("%.2f", price) + "\n");


        } catch (IOException e) {
            System.out.println("An error occurred while saving ticket information: " + e.getMessage());
        }
    }
}

