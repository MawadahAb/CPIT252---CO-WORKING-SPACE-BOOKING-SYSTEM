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

    // Store booking phone numbers to link each booking with a user
    static ArrayList<String> bookingPhones = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("------------------------------------");
        System.out.println(" CO-WORKING SPACE BOOKING SYSTEM ");
        System.out.println("------------------------------------");

        while (true) {

            System.out.println("\nSelect Your role:");
            System.out.println("1. User");
            System.out.println("2. Manager");
            System.out.println("3. Exit");

            int choice = readIntInRange("Enter your choice: ", 1, 3);

            if (choice == 1) {

                userMenu();

            } else if (choice == 2) {

                managerMenu();

            } else {

                System.out.println("Thank you for using the system.");
                break;
            }
        }
    }

    // Handle user booking workflow
    public static void userMenu() {

        System.out.println("\n--- USER MENU ---");

        System.out.println("1. New booking");
        System.out.println("2. Continue existing booking");

        int userChoice = readIntInRange("Enter your choice: ", 1, 2);

        if (userChoice == 2) {

            continueExistingBooking();
            return;
        }

        String name = readNonEmptyText("Enter your full name: ");
        String phone = readNonEmptyText("Enter your phone number: ");

        int hours = readIntInRange(
                "Enter booking duration in hours, minimum 1 hour: ",
                1,
                24
        );

        Customer customer = new Customer(name, phone);

        Workspace workspace = chooseWorkspace();

        BookingContext booking =
                new BookingContext(customer, workspace, inventory, hours);

        System.out.println("\n--- WORKSPACE DETAILS ---");
        System.out.println("Workspace: " + workspace.getDescription());
        System.out.println("Capacity: " + workspace.getCapacity());
        System.out.println("Price per hour: " + workspace.getCost() + " SAR");
        System.out.println("Booking duration: " + hours + " hours");
        System.out.println("Initial total price: " + booking.getTotalCost() + " SAR");

        System.out.println("\nDo you want to confirm the reservation?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        int confirm = readIntInRange("Enter your choice: ", 1, 2);

        if (confirm == 1) {

            booking.book();

            if (booking.getState() instanceof ReservedState) {

                bookings.add(booking);
                bookingPhones.add(phone);

                System.out.println("Your booking number is: " + bookings.size());

                bookingProcessMenu(booking);
            }

        } else {

            System.out.println("Booking cancelled.");
        }
    }

    // Continue booking using phone number
    public static void continueExistingBooking() {

        String phone = readNonEmptyText("Enter your phone number: ");

        int bookingIndex = findActiveBookingByPhone(phone);

        if (bookingIndex == -1) {

            System.out.println("No active booking found for this phone number.");
            return;
        }

        System.out.println("Booking found.");
        System.out.println("Booking number: " + (bookingIndex + 1));

        bookingProcessMenu(bookings.get(bookingIndex));
    }

    // Find active booking by phone number
    public static int findActiveBookingByPhone(String phone) {

        for (int i = 0; i < bookings.size(); i++) {

            BookingContext booking = bookings.get(i);

            if (bookingPhones.get(i).equals(phone)
                    && isActiveBooking(booking)) {

                return i;
            }
        }

        return -1;
    }

    // Check if booking is still active
    public static boolean isActiveBooking(BookingContext booking) {

        return booking.getState() instanceof ReservedState
                || booking.getState() instanceof CheckedInState;
    }

    // Allow user to choose workspace type
    public static Workspace chooseWorkspace() {

        Workspace privateOffice =
                workSpaceFactory.createWorkspace("PrivateOffice");

        Workspace smallMeetingRoom =
                workSpaceFactory.createWorkspace("SmallMeetingRoom");

        Workspace mediumMeetingRoom =
                workSpaceFactory.createWorkspace("MediumMeetingRoom");

        Workspace largeMeetingRoom =
                workSpaceFactory.createWorkspace("LargeMeetingRoom");

        Workspace openSpace =
                workSpaceFactory.createWorkspace("OpenSpace");

        System.out.println("\nChoose Workspace Type:");

        System.out.println("1. " + privateOffice.getDescription()
                + " | Capacity: " + privateOffice.getCapacity()
                + " | Price: " + privateOffice.getCost() + " SAR/hour");

        System.out.println("2. " + smallMeetingRoom.getDescription()
                + " | Capacity: " + smallMeetingRoom.getCapacity()
                + " | Price: " + smallMeetingRoom.getCost() + " SAR/hour");

        System.out.println("3. " + mediumMeetingRoom.getDescription()
                + " | Capacity: " + mediumMeetingRoom.getCapacity()
                + " | Price: " + mediumMeetingRoom.getCost() + " SAR/hour");

        System.out.println("4. " + largeMeetingRoom.getDescription()
                + " | Capacity: " + largeMeetingRoom.getCapacity()
                + " | Price: " + largeMeetingRoom.getCost() + " SAR/hour");

        System.out.println("5. " + openSpace.getDescription()
                + " | Capacity: " + openSpace.getCapacity()
                + " | Price: " + openSpace.getCost() + " SAR/hour");

        int workspaceChoice =
                readIntInRange("Enter your choice: ", 1, 5);

        if (workspaceChoice == 1) {

            return privateOffice;

        } else if (workspaceChoice == 2) {

            return smallMeetingRoom;

        } else if (workspaceChoice == 3) {

            return mediumMeetingRoom;

        } else if (workspaceChoice == 4) {

            return largeMeetingRoom;

        } else {

            return openSpace;
        }
    }

    // Handle booking state workflow
    public static void bookingProcessMenu(BookingContext booking) {

        boolean running = true;

        while (running) {

            System.out.println("\n--- BOOKING MENU ---");

            System.out.println("Customer: "
                    + booking.getCustomer().getContactInfo());

            System.out.println("Workspace: "
                    + booking.getWorkspace().getDescription());

            System.out.println("Total Price: "
                    + booking.getTotalCost() + " SAR");

            if (booking.getState() instanceof ReservedState) {

                System.out.println("\n1. Add extra services");
                System.out.println("2. Check-in");
                System.out.println("3. Cancel booking");
                System.out.println("4. Return to main menu");

                int choice = readIntInRange("Enter your choice: ", 1, 4);

                if (choice == 1) {

                    addServices(booking);

                } else if (choice == 2) {

                    booking.checkIn();

                } else if (choice == 3) {

                    booking.cancel();
                    running = false;

                } else {

                    running = false;
                }

            } else if (booking.getState() instanceof CheckedInState) {

                System.out.println("\n1. Add extra services");
                System.out.println("2. Check-out and pay");
                System.out.println("3. Return to main menu");

                int choice = readIntInRange("Enter your choice: ", 1, 3);

                if (choice == 1) {

                    addServices(booking);

                } else if (choice == 2) {

                    PaymentContext paymentContext = choosePaymentMethod();

                    booking.checkOut(paymentContext);

                    running = false;

                } else {

                    running = false;
                }

            } else {

                running = false;
            }
        }
    }

    // Add extra services to the booking
    public static void addServices(BookingContext booking) {

        boolean adding = true;

        while (adding) {

            Workspace currentWorkspace = booking.getWorkspace();

            Workspace coffee =
                    new CoffeeDecorator(currentWorkspace);

            Workspace printer =
                    new PrinterDecorator(currentWorkspace);

            Workspace projector =
                    new ProjectorDecorator(currentWorkspace);

            Workspace whiteboard =
                    new WhiteboardDecorator(currentWorkspace);

            System.out.println("\nChoose Extra Service:");

            System.out.println("1. Coffee | Price: "
                    + (coffee.getAddOnsCost()
                    - currentWorkspace.getAddOnsCost())
                    + " SAR");

            System.out.println("2. Printer | Price: "
                    + (printer.getAddOnsCost()
                    - currentWorkspace.getAddOnsCost())
                    + " SAR");

            System.out.println("3. Projector | Price: "
                    + (projector.getAddOnsCost()
                    - currentWorkspace.getAddOnsCost())
                    + " SAR");

            System.out.println("4. Whiteboard | Price: "
                    + (whiteboard.getAddOnsCost()
                    - currentWorkspace.getAddOnsCost())
                    + " SAR");

            System.out.println("5. Done");

            int serviceChoice =
                    readIntInRange("Enter your choice: ", 1, 5);

            if (serviceChoice == 1) {

                booking.addService(coffee, "Coffee");

            } else if (serviceChoice == 2) {

                booking.addService(printer, "Printer");

            } else if (serviceChoice == 3) {

                booking.addService(projector, "Projector");

            } else if (serviceChoice == 4) {

                booking.addService(whiteboard, "Whiteboard");

            } else {

                adding = false;
            }
        }
    }

    // Choose payment method using Strategy Pattern
    public static PaymentContext choosePaymentMethod() {

        while (true) {

            System.out.println("\nChoose Payment Method:");
            System.out.println("1. Credit Card");
            System.out.println("2. PayPal");

            int paymentChoice =
                    readIntInRange("Enter your choice: ", 1, 2);

            if (paymentChoice == 1) {

                System.out.println("Card number must be at least 8 digits.");
                System.out.println("CVV must be exactly 3 digits.");

                String cardHolderName =
                        readNonEmptyText("Card holder name: ");

                String cardNumber =
                        readCardNumber();

                String cvv =
                        readCvv();

                String expirationDate =
                        readNonEmptyText("Expiration date: ");

                return new PaymentContext(
                        new CreditCard(
                                cardHolderName,
                                cardNumber,
                                cvv,
                                expirationDate
                        )
                );

            } else {

                String paypalEmail =
                        readNonEmptyText("PayPal email: ");

                String paypalPassword =
                        readNonEmptyText("PayPal password: ");

                return new PaymentContext(
                        new PayPal(
                                paypalEmail,
                                paypalPassword
                        )
                );
            }
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

            int managerChoice =
                    readIntInRange("Enter your choice: ", 1, 3);

            if (managerChoice == 1) {

                printAvailableRooms();

            } else if (managerChoice == 2) {

                printBookedRooms();

            } else {

                managerRunning = false;
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

        Workspace workspace =
                workSpaceFactory.createWorkspace(type);

        int count = 0;

        while (inventory.reserve(workspace)) {

            count++;
        }

        for (int i = 0; i < count; i++) {

            inventory.release(workspace);
        }

        System.out.println(
                workspace.getDescription()
                        + ": "
                        + count
                        + " available"
        );
    }

    // Display all active bookings
    public static void printBookedRooms() {

        System.out.println("\n--- BOOKED ROOMS ---");

        boolean found = false;

        for (int i = 0; i < bookings.size(); i++) {

            BookingContext booking = bookings.get(i);

            if (isActiveBooking(booking)) {

                found = true;

                System.out.println("\nBooking Number "
                        + (i + 1));

                System.out.println("Customer: "
                        + booking.getCustomer().getContactInfo());

                System.out.println("Workspace: "
                        + booking.getWorkspace().getDescription());

                System.out.println("Duration: "
                        + booking.getBookingDurationHours()
                        + " hours");

                System.out.println("Total Price: "
                        + booking.getTotalCost()
                        + " SAR");

                System.out.println("State: "
                        + booking.getState()
                        .getClass()
                        .getSimpleName());
            }
        }

        if (!found) {

            System.out.println("No booked rooms.");
        }
    }

    // Read integer within a specific range
    public static int readIntInRange(
            String message,
            int min,
            int max
    ) {

        while (true) {

            System.out.print(message);

            if (input.hasNextInt()) {

                int value = input.nextInt();
                input.nextLine();

                if (value >= min && value <= max) {

                    return value;
                }
            } else {

                input.nextLine();
            }

            System.out.println(
                    "Invalid input. Please enter a number from "
                            + min
                            + " to "
                            + max
                            + "."
            );
        }
    }

    // Read non-empty text
    public static String readNonEmptyText(String message) {

        while (true) {

            System.out.print(message);

            String value = input.nextLine();

            if (!value.trim().isEmpty()) {

                return value;
            }

            System.out.println("Invalid input. This field cannot be empty.");
        }
    }

    // Read valid card number
    public static String readCardNumber() {

        while (true) {

            System.out.print("Card number: ");

            String cardNumber = input.nextLine();

            if (cardNumber.length() >= 8
                    && cardNumber.matches("\\d+")) {

                return cardNumber;
            }

            System.out.println(
                    "Invalid card number. It must be at least 8 digits."
            );
        }
    }

    // Read valid CVV
    public static String readCvv() {

        while (true) {

            System.out.print("CVV: ");

            String cvv = input.nextLine();

            if (cvv.length() == 3
                    && cvv.matches("\\d+")) {

                return cvv;
            }

            System.out.println(
                    "Invalid CVV. It must be exactly 3 digits."
            );
        }
    }
}
