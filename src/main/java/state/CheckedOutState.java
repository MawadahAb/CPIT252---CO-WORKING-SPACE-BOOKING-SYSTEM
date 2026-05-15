package state;

import paymentStrategy.PaymentContext;
import workspace.Workspace;

public class CheckedOutState implements BookingState {

    @Override
    public void book(BookingContext context) {
        System.out.println("Booking is already completed.");
    }

    @Override
    public void addService(BookingContext context, Workspace newWorkspace, String serviceName) {
        System.out.println("Cannot add " + serviceName + " after check out.");
    }

    @Override
    public void checkIn(BookingContext context) {
        System.out.println("Cannot check in after check out.");
    }

    @Override
    public void checkOut(BookingContext context, PaymentContext paymentContext) {
        System.out.println("Customer already checked out.");
    }

    @Override
    public void cancel(BookingContext context) {
        System.out.println("Cannot cancel completed booking.");
    }
}