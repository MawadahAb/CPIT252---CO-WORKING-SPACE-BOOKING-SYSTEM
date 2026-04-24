package workspace;

public class PrivateOffice implements  Workspace {
    private final int capacity = 1;
    public PrivateOffice() {

    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
