public static Workspace createWorkspace(String type) {

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
        return new OpenSpace(); 
    }

    throw new IllegalArgumentException("Invalid workspace type");
}