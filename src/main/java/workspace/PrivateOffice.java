package workspace;

// PrivateOffice class implements the Workspace interface
public class PrivateOffice implements  Workspace {

    private final int capacity = 1;
    private final double costPerHour = 100
            ;

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public double getCost() {
        return costPerHour;
    }
    
    // Private office has no add-ons by default
    @Override
    public double getAddOnsCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Private Office";
    }
}
