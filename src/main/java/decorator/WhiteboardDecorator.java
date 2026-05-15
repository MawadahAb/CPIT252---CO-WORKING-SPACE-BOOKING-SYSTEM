
package decorator;

import workspace.Workspace;

public class WhiteboardDecorator extends WorkspaceDecorator {

    public WhiteboardDecorator(Workspace workspace) {
        super(workspace);
    }

    @Override
    public double getCost() {
        return workspace.getCost() + 5;
    }

    @Override
    public String getDescription() {
        return workspace.getDescription() + " + Whiteboard";
    }
}