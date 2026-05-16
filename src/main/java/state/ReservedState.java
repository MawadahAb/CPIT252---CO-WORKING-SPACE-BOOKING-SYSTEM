package state;

import paymentStrategy.PaymentContext;
import workspace.Workspace;

public class ReservedState implements BookingState {

    // Prevent duplicate reservation
    @Override
    public void book(BookingContext context) {
        System.out.println("Workspace is already reserved.");
    }

    // Add services after reservation
    @Override
    public void addService(BookingContext context, Workspace newWorkspace, String serviceName) {
        context.setWorkspace(newWorkspace);
        System.out.println(serviceName + " added with normal price.");
    }

    // Move booking to checked in state
    @Override
    public void checkIn(BookingContext context) {
        System.out.println("Customer checked in.");
        context.changeState(new CheckedInState());
    }

    // Prevent check out before check in
    @Override
    public void checkOut(BookingContext context, PaymentContext paymentContext) {
        System.out.println("Cannot check out before check in.");
    }

    // Cancel booking and release workspace
    @Override
    public void cancel(BookingContext context) {
        context.getInventory().release(context.getWorkspace());
        System.out.println("Booking cancelled. Workspace is available again.");
        context.changeState(new CancelledState());
    }
}