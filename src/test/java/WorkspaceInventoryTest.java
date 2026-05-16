import factory.workSpaceFactory;
import org.junit.jupiter.api.Test;
import workspace.Workspace;
import workspace.WorkspaceInventory;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {

    @Test
    void shouldRejectReservationWhenWorkspaceCapacityIsFull() {
        WorkspaceInventory inventory = new WorkspaceInventory();

        Workspace privateOffice = workSpaceFactory.createWorkspace("PrivateOffice");

        assertTrue(inventory.reserve(privateOffice));
        assertTrue(inventory.reserve(privateOffice));
        assertTrue(inventory.reserve(privateOffice));
        assertTrue(inventory.reserve(privateOffice));
        assertTrue(inventory.reserve(privateOffice));

        assertFalse(inventory.reserve(privateOffice));
    }
}
