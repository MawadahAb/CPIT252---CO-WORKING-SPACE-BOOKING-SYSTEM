
package decorator;

import workspace.Workspace;

public abstract class WorkspaceDecorator implements Workspace {

    protected Workspace decoratedWorkspace;

    // Initialize decorator with the original workspace object
    public WorkspaceDecorator(Workspace decoratedWorkspace) {
        this.decoratedWorkspace = decoratedWorkspace;
    }

    // Return workspace capacity from the wrapped workspace
    @Override
    public int getCapacity() {
        return decoratedWorkspace.getCapacity();
    }

    // Return the original workspace room cost
    @Override
    public double getCost() {
        return decoratedWorkspace.getCost();
    }

    // Return the total additional services cost
    @Override
    public double getAddOnsCost() {
        return decoratedWorkspace.getAddOnsCost();
    }

    @Override
    public String getDescription() {
        return decoratedWorkspace.getDescription();
    }
}