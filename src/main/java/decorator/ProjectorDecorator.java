package decorator;

import workspace.Workspace;

public class ProjectorDecorator extends WorkspaceDecorator {

    public ProjectorDecorator(Workspace decoratedWorkspace) {
        super(decoratedWorkspace);
    }

    // Add projector service price to additional services cost
    @Override
    public double getAddOnsCost() {
        return decoratedWorkspace.getAddOnsCost() + 15;
    }

    @Override
    public String getDescription() {
        return decoratedWorkspace.getDescription() + " + Projector";
    }
}