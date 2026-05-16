import customer.Customer;
import decorator.CoffeeDecorator;
import factory.workSpaceFactory;
import org.junit.jupiter.api.Test;
import paymentStrategy.PayPal;
import paymentStrategy.PaymentContext;
import state.*;
import workspace.Workspace;
import workspace.WorkspaceInventory;

import static org.junit.jupiter.api.Assertions.*;

public class StatePatternTest {

    @Test
    void bookingShouldMoveToReservedState() {
        BookingContext booking = createBooking();

        booking.book();

        assertTrue(booking.getState() instanceof ReservedState);
    }

    @Test
    void bookingShouldMoveToCheckedInState() {
        BookingContext booking = createBooking();

        booking.book();
        booking.checkIn();

        assertTrue(booking.getState() instanceof CheckedInState);
    }

    @Test
    void bookingShouldMoveToCheckedOutState() {
        BookingContext booking = createBooking();

        booking.book();
        booking.checkIn();

        PaymentContext paymentContext = new PaymentContext(new PayPal("salwa@gmail.com", "12345"));

        booking.checkOut(paymentContext);

        assertTrue(booking.getState() instanceof CheckedOutState);
    }

    @Test
    void bookingShouldMoveToCancelledState() {
        BookingContext booking = createBooking();

        booking.book();
        booking.cancel();

        assertTrue(booking.getState() instanceof CancelledState);
    }

    @Test
    void shouldNotAddServiceBeforeBooking() {
        BookingContext booking = createBooking();

        Workspace decoratedWorkspace = new CoffeeDecorator(booking.getWorkspace());

        booking.addService(decoratedWorkspace, "Coffee");

        assertTrue(booking.getState() instanceof AvailableState);
        assertEquals(0, booking.getWorkspace().getAddOnsCost());
    }

    @Test
    void shouldApplyExtraFeeToServiceAfterCheckIn() {

        BookingContext bookingBeforeCheckIn = createBooking();

        bookingBeforeCheckIn.book();

        Workspace coffeeBeforeCheckIn = new CoffeeDecorator(bookingBeforeCheckIn.getWorkspace());

        bookingBeforeCheckIn.addService(coffeeBeforeCheckIn, "Coffee");

        double coffeePriceBeforeCheckIn = bookingBeforeCheckIn.getWorkspace().getAddOnsCost() + bookingBeforeCheckIn.getExtraFee();

        BookingContext bookingAfterCheckIn = createBooking();

        bookingAfterCheckIn.book();
        bookingAfterCheckIn.checkIn();

        Workspace coffeeAfterCheckIn = new CoffeeDecorator(bookingAfterCheckIn.getWorkspace());

        bookingAfterCheckIn.addService(coffeeAfterCheckIn, "Coffee");

        double coffeePriceAfterCheckIn = bookingAfterCheckIn.getWorkspace().getAddOnsCost() + bookingAfterCheckIn.getExtraFee();

        assertTrue(coffeePriceAfterCheckIn > coffeePriceBeforeCheckIn);
    }

    private BookingContext createBooking() {
        Customer customer = new Customer("Salwa", "0555555555");

        Workspace workspace = workSpaceFactory.createWorkspace("PrivateOffice");

        WorkspaceInventory inventory = new WorkspaceInventory();

        return new BookingContext(customer, workspace, inventory, 2);
    }
}