package workspace;

public class MeetingRoom implements  Workspace {


    private String size;
    private int capacity;
    private double costPerHour;


    public MeetingRoom(String size) {
        this.size = size;

        if (size.equalsIgnoreCase("Large")) {
            capacity = 12;
            costPerHour=120;
        } else if (size.equalsIgnoreCase("Medium")) {
            capacity = 6;
            costPerHour=80;
        } else if (size.equalsIgnoreCase("Small")) {
            capacity = 3;
            costPerHour=50;
        }
    }
    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public double getCost() {
        return costPerHour;
    }

    @Override
    public String getDescription() {
        return size + "Meeting Room";
    }

}
