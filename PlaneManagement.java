import java.io.IOException;
import java.util.Scanner;

public class PlaneManagement {
    private static final int MAX_TICKETS = 52;
    private static Ticket[] ticketsSold = new Ticket[MAX_TICKETS];
    private static int ticketsCount = 0;

    public static void main(String[] args) {
        // Welcome message and initialization of seat plan
        System.out.println("Welcome to the Plane Management application");



        char[][] Seat_Plan_Array = {
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', '0', '0'}
        };



        // Main loop for user interaction
        while (true) {
            // Display user menu
            System.out.println("\n*************************************************");
            System.out.println("*\t\t\t\t Menu Options \t\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.println("1. Buy a Seat");
            System.out.println("2. Cancel a Seat Reservation");
            System.out.println("3. Find First Available Seat");
            System.out.println("4. Print Seat Plan");
            System.out.println("5. Print Tickets Information");
            System.out.println("6. Search Ticket");
            System.out.println("0. Exit");
            System.out.println("\n*************************************************");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(new Scanner(System.in).nextLine());

            // Handle user choice using a switch statement
            switch (choice) {
                case 0:
                    System.out.println("Exiting the program...");
                    return;
                case 1:
                    buySeat(Seat_Plan_Array);
                    break;
                case 2:
                    cancelSeat(Seat_Plan_Array);
                    break;
                case 3:
                    findFirstAvailable(Seat_Plan_Array);
                    break;
                case 4:
                    printSeatPlan(Seat_Plan_Array);
                    break;
                case 5:
                    print_tickets_info(Seat_Plan_Array);
                    break;
                case 6:
                    Search_Ticket();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to buy a seat
    public static void buySeat(char[][] Seat_Plan_Array) {
        Scanner scanner = new Scanner(System.in);

        // Display seat plan to book
        System.out.println("\nSeat Plan:");
        for (int row = 0; row < Seat_Plan_Array.length; row++) {
            System.out.print("  Row " + (char) ('A' + row) + ": ");
            for (int column = 0; column < Seat_Plan_Array[row].length; column++) {
                System.out.print(Seat_Plan_Array[row][column] + " ");
            }
            System.out.println();
        }

        try {

            System.out.println("\nAvailable Seats: (format: RowLetter SeatNumber)");
            System.out.print("Enter the desired row letter (A-D): ");
            String rowString = scanner.nextLine().toUpperCase();


            if (!rowString.matches("[A-D]")) {
                System.out.println("Invalid row letter. Please enter A, B, C, or D.");
                return;
            }

            int row = rowString.charAt(0) - 'A';


            System.out.print("Enter the desired seat number (1-" + Seat_Plan_Array[row].length + "): ");
            int seatNumber = Integer.parseInt(scanner.nextLine());


            if (seatNumber < 1 || seatNumber > Seat_Plan_Array[row].length) {
                System.out.println("Invalid seat selection. Seat is out of range.");
                return;
            }

            int column = seatNumber - 1;


            if (Seat_Plan_Array[row][column] != 'O') {
                System.out.println("This seat is not available for booking.");
                return;
            }


            double ticketPrice = calculatePrice(seatNumber);


            System.out.print("\nEnter passenger First name: ");
            String passengerName = scanner.nextLine();
            System.out.print("Enter passenger surname: ");
            String passengerSurname = scanner.nextLine();
            System.out.print("Enter passenger email: ");
            String passengerEmail = scanner.nextLine();
            Person passenger = new Person(passengerName, passengerSurname, passengerEmail);


            Ticket ticket = new Ticket(row, seatNumber, passenger, ticketPrice);


            Seat_Plan_Array[row][column] = 'X';
            System.out.println("\nSeat successfully booked!");
            System.out.println("  - Passenger Information:");
            passenger.printInfo();
            System.out.println("  - Selected Seat: Row " + (char) ('A' + row) + ", Seat " + (seatNumber));

            // Save ticket information to a file
            ticket.save();
            System.out.println("Ticket information saved successfully.");


            ticketsSold[ticketsCount++] = ticket;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving ticket information: " + e.getMessage());
        }
    }




    // Method to cancel a seat reservation
    public static void cancelSeat(char[][] Seat_Plan_Array) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.print("Enter row letter (A-D): ");
            String rowString = scanner.nextLine().toUpperCase();


            if (!rowString.matches("[A-D]")) {
                System.out.println("Invalid row letter. Please enter A, B, C, or D.");
                continue;
            }

            int row = rowString.charAt(0) - 'A';

            try {

                System.out.print("Enter seat number (1-" + Seat_Plan_Array[row].length + "): ");
                int seatNumber = Integer.parseInt(scanner.nextLine());


                if (seatNumber < 1 || seatNumber > Seat_Plan_Array[row].length) {
                    System.out.println("Invalid seat selection. Seat is out of range.");
                    continue;
                }

                int column = seatNumber - 1;

                if (Seat_Plan_Array[row][column] == 'O') {
                    System.out.println("This seat is not reserved.");
                    continue;
                }

                // Confirm cancellation
                System.out.println("Are you sure you want to cancel seat " + (char) ('A' + row) + ", " + (column + 1) + "? (y/n)");
                String confirmation = scanner.nextLine().toLowerCase();

                if (confirmation.equals("y")) {

                    Seat_Plan_Array[row][column] = 'O';
                    System.out.println("Seat reservation cancelled successfully.");
                } else {
                    System.out.println("Cancellation cancelled.");
                }


                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }


    // Method to find the first available seat
    public static void findFirstAvailable(char[][] Seat_Plan_Array) {
        for (int row = 0; row < Seat_Plan_Array.length; row++) {
            for (int column = 0; column < Seat_Plan_Array[row].length; column++) {
                if (Seat_Plan_Array[row][column] == 'O') {
                    System.out.println("\nThe first available seat is in row " + (char) ('A' + row) +
                            " and seat number " + (column + 1));
                    return;
                }
            }
        }

        System.out.println("\nNo available seats found.");
    }

    // Method to print the seat plan
    public static void printSeatPlan(char[][] Seat_Plan_Array) {
        System.out.println("\nSeat Plan:");
        for (int row = 0; row < Seat_Plan_Array.length; row++) {
            System.out.print("  Row " + (char) ('A' + row) + ": ");
            for (int column = 0; column < Seat_Plan_Array[row].length; column++) {
                System.out.print(Seat_Plan_Array[row][column] + " ");
            }
            System.out.println();
        }
    }




    // Method to search for a ticket
    public static void Search_Ticket() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter row letter (A to D): ");
        char rowLetter = Character.toUpperCase(input.next().charAt(0));

        if (rowLetter < 'A' || rowLetter > 'D') {
            System.out.println("Invalid row letter. Please enter a valid row (A to D).");
            return;
        }

        int maxSeatNumber;
        if (rowLetter == 'B' || rowLetter == 'C') {
            maxSeatNumber = 12;
        } else {
            maxSeatNumber = 14;
        }

        System.out.printf("Enter seat number (1 to %d): ", maxSeatNumber);
        int seatNumber = input.nextInt();

        if (seatNumber < 1 || seatNumber > maxSeatNumber) {
            System.out.printf("Invalid seat number. Please enter a valid seat number between 1 and %d for row %c.\n", maxSeatNumber, rowLetter);
            return;
        }

        boolean found = false;

        for (int i = 0; i < ticketsCount; i++) {
            Ticket ticket = ticketsSold[i];
            if (ticket != null && ticket.getRow() == rowLetter - 'A' && ticket.getSeat() == seatNumber) {
                ticket.printTicketInfo();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\nThis seat is available.");
        }
    }

// Method to print ticket information

    public static void print_tickets_info(char[][] Seat_Plan_Array) {
        int totalTicketsSold = 0;
        double totalPrice = 0.0;

        System.out.println("\nTicket Information:");


        for (int row = 0; row < Seat_Plan_Array.length; row++) {
            for (int column = 0; column < Seat_Plan_Array[row].length; column++) {
                if (Seat_Plan_Array[row][column] == 'X') {
                    totalTicketsSold++;

                    double ticketPrice = calculatePrice(column + 1);
                    totalPrice += ticketPrice;
                    System.out.println("  - Seat: Row " + (char) ('A' + row) + ", Seat " + (column + 1));
                }
            }
        }

        if (totalTicketsSold == 0) {
            System.out.println("  - No tickets sold during this session.");
        } else {
            System.out.println("\n  - Total Tickets Sold: " + totalTicketsSold);
            System.out.printf("  - Total Price: $%.2f\n", totalPrice); // Corrected formatted output for price
        }
    }



    // Method to calculate ticket price based on seat selection
    public static int calculatePrice(int seatNumber) {
        int price;
        if (seatNumber >= 1 && seatNumber <= 5) {
            price = 200;
        } else if (seatNumber >= 6 && seatNumber <= 9) {
            price = 150;
        } else {
            price = 180;
        }
        return price;
    }
}