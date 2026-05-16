import decorator.CoffeeDecorator;
import factory.workSpaceFactory;
import org.junit.jupiter.api.Test;
import workspace.Workspace;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorPatternTest {

    @Test
    void shouldIncreaseFinalPriceAfterAddingService() {

        Workspace workspace = workSpaceFactory.createWorkspace("PrivateOffice");

        double priceBeforeService = workspace.getCost() + workspace.getAddOnsCost();

        workspace = new CoffeeDecorator(workspace);

        double priceAfterService = workspace.getCost() + workspace.getAddOnsCost();

        assertTrue(priceAfterService > priceBeforeService);
    }
}