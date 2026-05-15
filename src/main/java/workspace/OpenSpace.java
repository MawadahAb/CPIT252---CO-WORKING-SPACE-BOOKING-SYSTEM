package workspace;

public class OpenSpace implements Workspace {
    private final int totalSeats;
    private int availableSeats;
    private double costPerHour;

    public OpenSpace(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.costPerHour = 30;
    }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    public void cancelSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public int getCapacity() {
        return totalSeats;
    }

    @Override
    public double getCost() {
        return costPerHour;
    }

    @Override
    public double getAddOnsCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Open Space Seat";
    }
}
