package factory;

import workspace.Workspace;
import workspace.MeetingRoom;
import workspace.PrivateOffice;
import workspace.OpenSpace;
public class workSpaceFactory {

public static Workspace createWorkspace(String type) {

    // Create and return different workspace objects based on the provided workspace type

    if (type.equalsIgnoreCase("LargeMeetingRoom")) {
        return new MeetingRoom("Large");
    }
    else if (type.equalsIgnoreCase("MediumMeetingRoom")) {
        return new MeetingRoom("Medium");
    }
    else if (type.equalsIgnoreCase("SmallMeetingRoom")) {
        return new MeetingRoom("Small");
    }
    else if (type.equalsIgnoreCase("PrivateOffice")) {
        return new PrivateOffice();
    }
    else if (type.equalsIgnoreCase("OpenSpace")) {
        return new OpenSpace(20);
    }

    throw new IllegalArgumentException("Invalid workspace type");
    }
}