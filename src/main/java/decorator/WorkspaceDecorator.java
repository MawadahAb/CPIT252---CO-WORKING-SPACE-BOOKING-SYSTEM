
package decorator;

import workspace.Workspace;

public abstract class WorkspaceDecorator implements Workspace {

    protected Workspace decoratedWorkspace;

    public WorkspaceDecorator(Workspace decoratedWorkspace) {
        this.decoratedWorkspace = decoratedWorkspace;
    }

    @Override
    public int getCapacity() {
        return decoratedWorkspace.getCapacity();
    }

    @Override
    public double getCost() {
        return decoratedWorkspace.getCost();
    }

    @Override
    public double getAddOnsCost() {
        return decoratedWorkspace.getAddOnsCost();
    }

    @Override
    public String getDescription() {
        return decoratedWorkspace.getDescription();
    }
}