package decorator;

import workspace.Workspace;

public class ProjectorDecorator extends WorkspaceDecorator {

    public ProjectorDecorator(Workspace decoratedWorkspace) {
        super(decoratedWorkspace);
    }

    @Override
    public double getCost() {
        return decoratedWorkspace.getCost() + 20;
    }

    @Override
    public String getDescription() {
        return decoratedWorkspace.getDescription() + " + Projector";
    }
}