package workspace;

public class MeetingRoom implements  Workspace {


    private String size;
    private int capacity;


    public MeetingRoom(String size) {
        this.size = size;

        if (size.equalsIgnoreCase("Large")) {
            capacity = 12;
        } else if (size.equalsIgnoreCase("Medium")) {
            capacity = 6;
        } else if (size.equalsIgnoreCase("Small")) {
            capacity = 3;
        }
    }
    @Override
    public int getCapacity() {
        return capacity;
    }

}
