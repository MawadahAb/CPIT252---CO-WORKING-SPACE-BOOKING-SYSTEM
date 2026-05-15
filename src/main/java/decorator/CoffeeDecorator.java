package decorator;

import workspace.Workspace;

public class CoffeeDecorator extends WorkspaceDecorator {

    public CoffeeDecorator(Workspace decoratedWorkspace) {
        super(decoratedWorkspace);
    }

    @Override
    public double getAddOnsCost() {
        return decoratedWorkspace.getAddOnsCost() + 10;
    }

    @Override
    public String getDescription() {
        return decoratedWorkspace.getDescription() + " + Coffee";
    }
}