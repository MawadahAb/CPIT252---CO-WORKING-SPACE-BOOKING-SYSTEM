package state;

import paymentStrategy.PaymentContext;
import workspace.Workspace;

public class AvailableState implements BookingState {

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

    @Override
    public void addService(BookingContext context, Workspace newWorkspace, String serviceName) {
        System.out.println("Cannot add " + serviceName + " before booking.");
    }

    @Override
    public void checkIn(BookingContext context) {
        System.out.println("Cannot check in before booking.");
    }

    @Override
    public void checkOut(BookingContext context, PaymentContext paymentContext) {
        System.out.println("Cannot check out before booking.");
    }

    @Override
    public void cancel(BookingContext context) {
        System.out.println("No booking to cancel.");
    }

}