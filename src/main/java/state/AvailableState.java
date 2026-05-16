package state;

import paymentStrategy.PaymentContext;
import workspace.Workspace;

public class AvailableState implements BookingState {

    // Reserve workspace if available
    @Override
    public void book(BookingContext context) {
        boolean reserved = context.getInventory().reserve(context.getWorkspace());

        if (reserved) {
            System.out.println("Workspace has been reserved.");
            context.changeState(new ReservedState());
        } else {
            System.out.println("Workspace is not available.");
        }
    }

    // Prevent adding services before booking
    @Override
    public void addService(BookingContext context, Workspace newWorkspace, String serviceName) {
        System.out.println("Cannot add " + serviceName + " before booking.");
    }

    // Prevent check in before booking
    @Override
    public void checkIn(BookingContext context) {
        System.out.println("Cannot check in before booking.");
    }

    // Prevent check out before booking
    @Override
    public void checkOut(BookingContext context, PaymentContext paymentContext) {
        System.out.println("Cannot check out before booking.");
    }

    // No booking exists to cancel
    @Override
    public void cancel(BookingContext context) {
        System.out.println("No booking to cancel.");
    }

}