
public class PrinterDecorator extends WorkspaceDecorator {

    public PrinterDecorator(Workspace workspace) {
        super(workspace);
    }

    @Override
    public double getCost() {
        return workspace.getCost() + 15;
    }

    @Override
    public String getDescription() {
        return workspace.getDescription() + " + Printer";
    }
}