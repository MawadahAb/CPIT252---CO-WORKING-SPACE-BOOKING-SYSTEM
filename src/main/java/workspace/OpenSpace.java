package workspace;

public class OpenSpace implements Workspace {
    private final int totalSeats;
    private int availableSeats;

    public OpenSpace(int totalSeats) {
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
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
}
