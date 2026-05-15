package state;

import customer.Customer;
import paymentStrategy.PaymentContext;
import workspace.Workspace;
import workspace.WorkspaceInventory;

public class BookingContext {

    private Customer customer;
    private BookingState state;
    private Workspace workspace;
    private WorkspaceInventory inventory;
    private int bookingDurationHours;
    private double extraFee;

    public BookingContext(Customer customer,
                          Workspace workspace,
                          WorkspaceInventory inventory,
                          int bookingDurationHours) {

        this.customer = customer;
        this.workspace = workspace;
        this.inventory = inventory;
        this.bookingDurationHours = bookingDurationHours;

        this.state = new Available();
        this.extraFee = 0;
    }

    public void book() {
        state.book(this);
    }

    public void addService(Workspace newWorkspace, String serviceName) {
        state.addService(this, newWorkspace, serviceName);
    }

    public void checkIn() {
        state.checkIn(this);
    }

    public void checkOut(PaymentContext paymentContext) {
        state.checkOut(this, paymentContext);
    }

    public void cancel() {
        state.cancel(this);
    }

    public void changeState(BookingState state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BookingState getState() {
        return state;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    public WorkspaceInventory getInventory() {
        return inventory;
    }

    public int getBookingDurationHours() {
        return bookingDurationHours;
    }

    public void addExtraFee(double fee) {
        extraFee += fee;
    }

    public double getExtraFee() {
        return extraFee;
    }

    public double getTotalCost() {

        return (workspace.getCost() * bookingDurationHours)
                + workspace.getAddOnsCost()
                + extraFee;
    }
}