package state;

import paymentStrategy.PaymentContext;
import paymentStrategy.PaymentReceipt;
import workspace.Workspace;

public class CheckedInState implements BookingState {

    // Prevent booking after check in
    @Override
    public void book(BookingContext context) {
        System.out.println("Customer is already checked in.");
    }

    // Add extra service and fee during active session
    @Override
    public void addService(BookingContext context, Workspace newWorkspace, String serviceName) {
        context.setWorkspace(newWorkspace);
        context.addExtraFee(5);
        System.out.println(serviceName + " added during session with extra fee.");
    }

    // Prevent duplicate check in
    @Override
    public void checkIn(BookingContext context) {
        System.out.println("Customer is already checked in.");
    }

    // Process payment and complete check out
    @Override
    public void checkOut(BookingContext context, PaymentContext paymentContext) {

        PaymentReceipt receipt = paymentContext.executePayment(context.getTotalCost());

        if (!receipt.isSuccessful()) {
            System.out.println("Payment failed: " + receipt.getMessage());
            System.out.println("Customer cannot check out until payment is completed.");
            return;
        }

        receipt.printReceipt(context.getCustomer(), context.getWorkspace(), context.getBookingDurationHours());

        context.getInventory().release(context.getWorkspace());

        System.out.println("Customer checked out. Workspace is available again.");
        context.changeState(new CheckedOutState());
    }

    // Prevent cancellation after check in
    @Override
    public void cancel(BookingContext context) {
        System.out.println("Cannot cancel after check in. Please complete payment and check out.");
    }

}