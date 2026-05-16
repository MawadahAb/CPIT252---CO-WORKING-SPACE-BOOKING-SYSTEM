import customer.Customer;

import decorator.CoffeeDecorator;
import decorator.PrinterDecorator;
import decorator.ProjectorDecorator;
import decorator.WhiteboardDecorator;

import factory.workSpaceFactory;

import paymentStrategy.CreditCard;
import paymentStrategy.PayPal;
import paymentStrategy.PaymentContext;

import state.BookingContext;
import state.CheckedInState;
import state.ReservedState;

import workspace.Workspace;
import workspace.WorkspaceInventory;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    static Scanner input = new Scanner(System.in);
    
    // Shared inventory object to manage workspace availability
    static WorkspaceInventory inventory = new WorkspaceInventory();

    // Store all bookings in the system
    static ArrayList<BookingContext> bookings = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("------------------------------------");
        System.out.println(" CO-WORKING SPACE BOOKING SYSTEM ");
        System.out.println("------------------------------------");

        while (true) {

            System.out.println("\nSelect Your role:");
            System.out.println("1. User");
            System.out.println("2. Manager");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine();  
        // User workflow
            if (choice == 1) {

                userMenu();
        // Manager workflow
            } else if (choice == 2) {

                managerMenu();
          // Exit system
            } else if (choice == 3) {

                System.out.println("Thank you for using the system.");
                break;

            } else {

                System.out.println("Invalid choice.");
            }
        }
    }
    // Handle user booking workflow
    public static void userMenu() {

        System.out.println("\n--- USER MENU ---");
    // Collect customer information
        System.out.print("Enter your full name: ");
        String name = input.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = input.nextLine();

        System.out.print("Enter booking duration in hours: ");
        int hours = input.nextInt();
        input.nextLine();

        Customer customer = new Customer(name, phone);
        // Let user select workspace
        Workspace workspace = chooseWorkspace();
        // Check invalid workspace
        if (workspace == null) {

            System.out.println("Invalid workspace.");
            return;
        }
        // Create booking context
        BookingContext booking = new BookingContext(customer, workspace, inventory, hours);

        System.out.println("\n--- WORKSPACE DETAILS ---");

        System.out.println("Workspace: " + workspace.getDescription());

        System.out.println("Capacity: " + workspace.getCapacity());

        System.out.println("Price per hour: " + workspace.getCost() + " SAR");

        System.out.println("\nDo you want to confirm the reservation?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        System.out.print("Enter your choice: ");

        int confirm = input.nextInt();
        input.nextLine();

        if (confirm == 1) {

            booking.book();

            if (booking.getState() instanceof ReservedState) {

                bookings.add(booking);

                bookingProcessMenu(booking);
            }

        } else {

            System.out.println("Booking cancelled.");
        }
    }
    // Allow user to choose workspace type
    public static Workspace chooseWorkspace() {

        System.out.println("\nChoose Workspace Type:");

        System.out.println("1. Private Office");
        System.out.println("2. Small Meeting Room");
        System.out.println("3. Medium Meeting Room");
        System.out.println("4. Large Meeting Room");
        System.out.println("5. Open Space Seat");

        System.out.print("Enter your choice: ");

        int workspaceChoice = input.nextInt();
        input.nextLine();
     // Factory Pattern used to create workspace objects
        if (workspaceChoice == 1) {

            return workSpaceFactory.createWorkspace("PrivateOffice");

        } else if (workspaceChoice == 2) {

            return workSpaceFactory.createWorkspace("SmallMeetingRoom");

        } else if (workspaceChoice == 3) {

            return workSpaceFactory.createWorkspace("MediumMeetingRoom");

        } else if (workspaceChoice == 4) {

            return workSpaceFactory.createWorkspace("LargeMeetingRoom");

        } else if (workspaceChoice == 5) {

            return workSpaceFactory.createWorkspace("OpenSpace");

        } else {

            return null;
        }
    }
    
    // Handle booking state workflow
    public static void bookingProcessMenu(BookingContext booking) {

        boolean running = true;

        while (running) {

            System.out.println("\n--- BOOKING MENU ---");

            System.out.println("Customer: " + booking.getCustomer().getContactInfo());

            System.out.println("Workspace: " + booking.getWorkspace().getDescription());

            System.out.println("Total Price: " + booking.getTotalCost() + " SAR");

            if (booking.getState() instanceof ReservedState) {

                System.out.println("\n1. Add extra services");
                System.out.println("2. Check-in");
                System.out.println("3. Cancel booking");
                System.out.println("4. Return to main menu");

                System.out.print("Enter your choice: ");

                int choice = input.nextInt();
                input.nextLine();
              // Add services using Decorator Pattern
                if (choice == 1) {

                    addServices(booking);
             // Move booking to CheckedInState
                } else if (choice == 2) {

                    booking.checkIn();
                // Cancel reservation
                } else if (choice == 3) {

                    booking.cancel();
                    running = false;
                // Exit menu
                } else if (choice == 4) {

                    running = false;

                } else {

                    System.out.println("Invalid choice.");
                }
            // Checked-in state actions
            } else if (booking.getState() instanceof CheckedInState) {

                System.out.println("\n1. Add extra services");
                System.out.println("2. Check-out and pay");
                System.out.println("3. Return to main menu");

                System.out.print("Enter your choice: ");

                int choice = input.nextInt();
                input.nextLine();

                if (choice == 1) {

                    addServices(booking);

                } else if (choice == 2) {

                    PaymentContext paymentContext = choosePaymentMethod();

                    booking.checkOut(paymentContext);

                    running = false;

                } else if (choice == 3) {

                    running = false;

                } else {

                    System.out.println("Invalid choice.");
                }

            } else {

                running = false;
            }
        }
    }
// Add extra services to the booking
    public static void addServices(BookingContext booking) {

        boolean adding = true;
    // Keep displaying services menu until user chose Done
        while (adding) {

            System.out.println("\nChoose Extra Service:");

            System.out.println("1. Coffee");
            System.out.println("2. Printer");
            System.out.println("3. Projector");
            System.out.println("4. Whiteboard");
            System.out.println("5. Done");

            System.out.print("Enter your choice: ");

            int serviceChoice = input.nextInt();
            input.nextLine();
        // Current workspace before adding services
            Workspace currentWorkspace = booking.getWorkspace();

            if (serviceChoice == 1) {

                booking.addService(new CoffeeDecorator(currentWorkspace), "Coffee");

            } else if (serviceChoice == 2) {

                booking.addService(new PrinterDecorator(currentWorkspace), "Printer");

            } else if (serviceChoice == 3) {

                booking.addService(new ProjectorDecorator(currentWorkspace), "Projector");

            } else if (serviceChoice == 4) {

                booking.addService(new WhiteboardDecorator(currentWorkspace), "Whiteboard");

            } else if (serviceChoice == 5) {

                adding = false;

            } else {

                System.out.println("Invalid choice.");
            }
        }
    }
// Choose payment method using Strategy Pattern
    public static PaymentContext choosePaymentMethod() {

        System.out.println("\nChoose Payment Method:");

        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");

        System.out.print("Enter your choice: ");

        int paymentChoice = input.nextInt();
        input.nextLine();
    // Create CreditCard payment strategy
        if (paymentChoice == 1) {

            System.out.print("Card holder name: ");
            String cardHolderName = input.nextLine();

            System.out.print("Card number: ");
            String cardNumber = input.nextLine();

            System.out.print("CVV: ");
            String cvv = input.nextLine();

            System.out.print("Expiration date: ");
            String expirationDate = input.nextLine();

            return new PaymentContext(new CreditCard(cardHolderName, cardNumber, cvv, expirationDate));
    // Create PayPal payment strategy
        } else {

            System.out.print("PayPal email: ");
            String paypalEmail = input.nextLine();

            System.out.print("PayPal password: ");
            String paypalPassword = input.nextLine();

            return new PaymentContext(new PayPal(paypalEmail, paypalPassword));
        }
    }
// Handle manager operations
    public static void managerMenu() {

        boolean managerRunning = true;

        while (managerRunning) {

            System.out.println("\n--- MANAGER MENU ---");

            System.out.println("1. View available rooms");
            System.out.println("2. View booked rooms");
            System.out.println("3. Return to main menu");

            System.out.print("Enter your choice: ");

            int managerChoice = input.nextInt();
            input.nextLine();

            if (managerChoice == 1) {

                printAvailableRooms();

            } else if (managerChoice == 2) {

                printBookedRooms();

            } else if (managerChoice == 3) {

                managerRunning = false;

            } else {

                System.out.println("Invalid choice.");
            }
        }
    }
// Display all available workspaces
    public static void printAvailableRooms() {

        System.out.println("\n--- AVAILABLE ROOMS ---");

        printAvailability("PrivateOffice");
        printAvailability("SmallMeetingRoom");
        printAvailability("MediumMeetingRoom");
        printAvailability("LargeMeetingRoom");
        printAvailability("OpenSpace");
    }
// Calculate and display available rooms count
    public static void printAvailability(String type) {
// Create workspace object using Factory Pattern
        Workspace workspace = workSpaceFactory.createWorkspace(type);

        int count = 0;
    // Count available workspaces
        while (inventory.reserve(workspace)) {

            count++;
        }
    // Restore inventory count after checking
        for (int i = 0; i < count; i++) {

            inventory.release(workspace);
        }

        System.out.println(workspace.getDescription() + ": " + count + " available");
    }
// Display all active bookings
    public static void printBookedRooms() {

        System.out.println("\n--- BOOKED ROOMS ---");

        boolean found = false;
    // Loop through all bookings
        for (int i = 0; i < bookings.size(); i++) {

            BookingContext booking = bookings.get(i);

            if (booking.getState() instanceof ReservedState || booking.getState() instanceof CheckedInState) {

                found = true;

                System.out.println("\nBooking Number" + (i + 1));

                System.out.println("Customer: " + booking.getCustomer().getContactInfo());

                System.out.println("Workspace: " + booking.getWorkspace().getDescription());

                System.out.println("Duration: " + booking.getBookingDurationHours() + " hours");

                System.out.println("Total Price: " + booking.getTotalCost() + " SAR");

                System.out.println("State: " + booking.getState().getClass().getSimpleName());
            }
        }
    // No active bookings found
        if (!found) {

            System.out.println("No booked rooms.");
        }
    }
}
