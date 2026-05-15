public class WorkspaceInventory {

    private int smallMeetingRooms = 3;
    private int mediumMeetingRooms = 2;
    private int largeMeetingRooms = 1;
    private int privateOffices = 5;
    private int openSpaceSeats = 20;

    public boolean reserve(Workspace workspace) {
        String description = workspace.getDescription();

        if (description.contains("Small") && smallMeetingRooms > 0) {
            smallMeetingRooms--;
            return true;
        }

        if (description.contains("Medium") && mediumMeetingRooms > 0) {
            mediumMeetingRooms--;
            return true;
        }

        if (description.contains("Large") && largeMeetingRooms > 0) {
            largeMeetingRooms--;
            return true;
        }

        if (description.contains("Private Office") && privateOffices > 0) {
            privateOffices--;
            return true;
        }

        if (description.contains("Open Space") && openSpaceSeats > 0) {
            openSpaceSeats--;
            return true;
        }

        return false;
    }

    public void release(Workspace workspace) {
        String description = workspace.getDescription();

        if (description.contains("Small")) {
            smallMeetingRooms++;
        } else if (description.contains("Medium")) {
            mediumMeetingRooms++;
        } else if (description.contains("Large")) {
            largeMeetingRooms++;
        } else if (description.contains("Private Office")) {
            privateOffices++;
        } else if (description.contains("Open Space")) {
            openSpaceSeats++;
        }
    }
}
