package state;

import paymentStrategy.PaymentContext;
import workspace.Workspace;

public class CancelledState implements BookingState {

    // Prevent booking after cancellation
    @Override
    public void book(BookingContext context) {
        System.out.println("Cannot book because this booking is cancelled.");
    }

    // Prevent adding services to cancelled booking
    @Override
    public void addService(BookingContext context, Workspace newWorkspace, String serviceName) {
        System.out.println("Cannot add " + serviceName + " to cancelled booking.");
    }

    // Prevent check in after cancellation
    @Override
    public void checkIn(BookingContext context) {
        System.out.println("Cannot check in to cancelled booking.");
    }

    // Prevent check out after cancellation
    @Override
    public void checkOut(BookingContext context, PaymentContext paymentContext) {
        System.out.println("Cannot check out cancelled booking.");
    }

    // Booking already cancelled
    @Override
    public void cancel(BookingContext context) {
        System.out.println("Booking is already cancelled.");
    }
}