import com.example.judy.TowerDefenseApplication;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextMatchers;

import static org.testfx.api.FxAssert.verifyThat;


public class TowerMenuTest extends ApplicationTest {

    /**
     * Starts up TowerDefenseApplication.
     * @param stage Stage given to start
     * @throws Exception Any exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        TowerDefenseApplication game = new TowerDefenseApplication();
        game.start(stage);
    }

    /**
     * Traverses to the initial config screen before assigning difficulty for testing.
     */
    @Before
    public void traverseToInitialConfigScreen() {
        clickOn("#start");
        Node input = lookup("#nameInput").query();
        clickOn(input);
        write("Test");
        clickOn("Enter");
    }

    /**
     * Tests whether tower menu opens on click of button
     */
    @Test
    public void testShowMenu() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        verifyThat("#cannon", NodeMatchers.isNotNull());
        verifyThat("#crossbow", NodeMatchers.isNotNull());
        verifyThat("#tank", NodeMatchers.isNotNull());
    }

    /**
     * Tests whether the inventory is visible
     */
    @Test
    public void testInventoryVisible() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#inventoryMenu");
        verifyThat("#cannon", NodeMatchers.isNotNull());
        verifyThat("#crossbow", NodeMatchers.isNotNull());
        verifyThat("#tank", NodeMatchers.isNotNull());
    }

    /**
     * Tests whether the names of the towers are visible
     */
    @Test
    public void testTowerNamesVisibleInMenu() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        verifyThat("#cannonName", LabeledMatchers.hasText("CANNON"));
        verifyThat("#crossbowName", LabeledMatchers.hasText("CROSSBOW"));
        verifyThat("#tankName", LabeledMatchers.hasText("TANK"));
    }

    /**
     * Tests whether the descriptions of the towers are visible
     */
    @Test
    public void testTowerDescriptionsVisibleInMenu() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        verifyThat("#cannonDescription", NodeMatchers.isNotNull());
        verifyThat("#crossbowDescription", NodeMatchers.isNotNull());
        verifyThat("#tankDescription", NodeMatchers.isNotNull());
    }

    /**
     * Tests whether an error is shown when a tower is chosen without sufficient money.
     */
    @Test
    public void testEnoughMoneyForTower() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("OK");
        clickOn("#inventoryMenu");
        verifyThat("#tankCount", TextMatchers.hasText("You have: 0"));
    }

}
