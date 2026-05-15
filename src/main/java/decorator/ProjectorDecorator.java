
public class ProjectorDecorator extends WorkspaceDecorator {

    public ProjectorDecorator(Workspace workspace) {
        super(workspace);
    }

    @Override
    public double getCost() {
        return workspace.getCost() + 20;
    }

    @Override
    public String getDescription() {
        return workspace.getDescription() + " + Projector";
    }
}