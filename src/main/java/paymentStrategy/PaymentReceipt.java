package paymentStrategy;

import workspace.Workspace;
import customer.Customer;

public class PaymentReceipt {

    private boolean successful;
    private String transactionId;
    private double amount;
    private String paymentMethod;
    private String message;

    public PaymentReceipt (boolean successful, String transactionId,double amount,String paymentMethod,String message ) {
        this.successful = successful;
        this.transactionId = transactionId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.message = message;
    }
    public boolean isSuccessful() {
        return successful;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getMessage() {
        return message;
    }

    public void printReceipt(Customer customer, Workspace workspace,int bookingDurationHours ) {

        if (!successful) {
            System.out.println("Payment failed. No receipt generated.");
        }

        System.out.println("\n---------- PAYMENT RECEIPT ----------");
        System.out.println("Dear " + customer.getFullName() + ",");
        System.out.println("Your booking has been confirmed successfully.\n");

        System.out.println("Booking Details:");
        System.out.println("Workspace: " + workspace.getDescription());
        System.out.println("Capacity: " + workspace.getCapacity());
        System.out.println("Booking Duration Hours: " + bookingDurationHours);

        System.out.println("\nPayment Details:");
        System.out.println("Amount Paid: " + amount + " SAR");
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Status: " + message);

        System.out.println("\nThank you for choosing our co-working space.");
        System.out.println("-------------------------------------\n");


    }


}
