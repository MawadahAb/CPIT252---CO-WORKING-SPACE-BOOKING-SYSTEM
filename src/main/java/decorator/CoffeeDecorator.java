
public class CoffeeDecorator extends WorkspaceDecorator {

    public CoffeeDecorator(Workspace workspace) {
        super(workspace);
    }

    @Override
    public double getCost() {
        return workspace.getCost() + 10;
    }

    @Override
    public String getDescription() {
        return workspace.getDescription() + " + Coffee";
    }
}