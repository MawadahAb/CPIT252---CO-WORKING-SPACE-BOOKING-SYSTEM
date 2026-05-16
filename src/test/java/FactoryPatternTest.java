import factory.workSpaceFactory;

import org.junit.jupiter.api.Test;

import workspace.MeetingRoom;
import workspace.OpenSpace;
import workspace.PrivateOffice;
import workspace.Workspace;

import static org.junit.jupiter.api.Assertions.*;

public class FactoryPatternTest {

    @Test
    void shouldCreateSmallMeetingRoom() {

        Workspace workspace = workSpaceFactory.createWorkspace("SmallMeetingRoom");

        assertNotNull(workspace);

        assertTrue(workspace instanceof MeetingRoom);

        assertEquals(3, workspace.getCapacity());
        assertEquals(50, workspace.getCost());
    }

    @Test
    void shouldCreateMediumMeetingRoom() {

        Workspace workspace = workSpaceFactory.createWorkspace("MediumMeetingRoom");

        assertNotNull(workspace);

        assertTrue(workspace instanceof MeetingRoom);

        assertEquals(6, workspace.getCapacity());
        assertEquals(80, workspace.getCost());
    }

    @Test
    void shouldCreateLargeMeetingRoom() {

        Workspace workspace = workSpaceFactory.createWorkspace("LargeMeetingRoom");

        assertNotNull(workspace);

        assertTrue(workspace instanceof MeetingRoom);

        assertEquals(12, workspace.getCapacity());
        assertEquals(120, workspace.getCost());
    }

    @Test
    void shouldCreateOpenSpace() {

        Workspace workspace = workSpaceFactory.createWorkspace("OpenSpace");

        assertNotNull(workspace);

        assertTrue(workspace instanceof OpenSpace);

        assertEquals(20, workspace.getCapacity());
        assertEquals(30, workspace.getCost());
    }

    @Test
    void shouldCreatePrivateOffice() {

        Workspace workspace = workSpaceFactory.createWorkspace("PrivateOffice");

        assertNotNull(workspace);

        assertTrue(workspace instanceof PrivateOffice);

        assertEquals(1, workspace.getCapacity());
        assertEquals(100, workspace.getCost());
    }
}