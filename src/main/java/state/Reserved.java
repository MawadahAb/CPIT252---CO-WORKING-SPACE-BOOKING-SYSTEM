package state;

import paymentStrategy.PaymentContext;
import workspace.Workspace;

public class Reserved implements BookingState {

    @Override
    public void book(BookingContext context) {
        System.out.println("Workspace is already reserved.");
    }

    @Override
    public void addService(BookingContext context, Workspace newWorkspace, String serviceName) {
        context.setWorkspace(newWorkspace);
        System.out.println(serviceName + " added with normal price.");
    }

    @Override
    public void checkIn(BookingContext context) {
        System.out.println("Customer checked in.");
        context.changeState(new CheckedIn());
    }

    @Override
    public void checkOut(BookingContext context, PaymentContext paymentContext) {
        System.out.println("Cannot check out before check in.");
    }

    @Override
    public void cancel(BookingContext context) {
        context.getInventory().release(context.getWorkspace());
        System.out.println("Booking cancelled. Workspace is available again.");
        context.changeState(new Cancelled());
    }
}