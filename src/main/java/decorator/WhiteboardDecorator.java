
package decorator;

import workspace.Workspace;

public class WhiteboardDecorator extends WorkspaceDecorator {

    public WhiteboardDecorator(Workspace decoratedWorkspace) {
        super(decoratedWorkspace);
    }

    @Override
    public double getAddOnsCost() {
        return decoratedWorkspace.getAddOnsCost() + 5;
    }

    @Override
    public String getDescription() {
        return decoratedWorkspace.getDescription() + " + Whiteboard";
    }
}