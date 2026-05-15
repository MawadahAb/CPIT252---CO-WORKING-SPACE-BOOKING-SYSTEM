

public abstract class WorkspaceDecorator implements Workspace {

    protected Workspace workspace;

    public WorkspaceDecorator(Workspace workspace) {
        this.workspace = workspace;
    }

    @Override
    public int getCapacity() {
        return workspace.getCapacity();
    }

    @Override
    public double getCost() {
        return workspace.getCost();
    }

    @Override
    public String getDescription() {
        return workspace.getDescription();
    }
}