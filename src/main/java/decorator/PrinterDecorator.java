package decorator;

import workspace.Workspace;

public class PrinterDecorator extends WorkspaceDecorator {

    public PrinterDecorator(Workspace decoratedWorkspace) {
        super(decoratedWorkspace);
    }

    // Add printer service price to additional services cost
    @Override
    public double getAddOnsCost() {
        return decoratedWorkspace.getAddOnsCost() + 15;
    }


    @Override
    public String getDescription() {
        return decoratedWorkspace.getDescription() + " + Printer";
    }
}