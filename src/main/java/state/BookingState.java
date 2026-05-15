package state;

import paymentStrategy.PaymentContext;
import workspace.Workspace;

public interface BookingState {
    void book(BookingContext context);
    void addService(BookingContext context, Workspace newWorkspace, String serviceName);
    void checkIn(BookingContext context);
    void checkOut(BookingContext context, PaymentContext paymentContext);
    void cancel(BookingContext context);
}