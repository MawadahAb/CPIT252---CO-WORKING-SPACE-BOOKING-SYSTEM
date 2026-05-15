package state;

import paymentStrategy.PaymentContext;
import workspace.Workspace;

public class Cancelled implements BookingState {

    @Override
    public void book(BookingContext context) {
        System.out.println("Cannot book because this booking is cancelled.");
    }

    @Override
    public void addService(BookingContext context, Workspace newWorkspace, String serviceName) {
        System.out.println("Cannot add " + serviceName + " to cancelled booking.");
    }

    @Override
    public void checkIn(BookingContext context) {
        System.out.println("Cannot check in to cancelled booking.");
    }

    @Override
    public void checkOut(BookingContext context, PaymentContext paymentContext) {
        System.out.println("Cannot check out cancelled booking.");
    }

    @Override
    public void cancel(BookingContext context) {
        System.out.println("Booking is already cancelled.");
    }
}