import com.example.judy.TowerDefenseApplication;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class FinalCombatTest extends ApplicationTest {

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
     * Verifies that a cannon can be successfully upgraded
     */
    @Test
    public void testUpgradeCannonCost() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#33");
        clickOn("#33");
        clickOn("YES");
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $36.00"));
    }

    /**
     * Verifies that a crossbow can be successfully upgraded
     */
    @Test
    public void testUpgradeCrossbowCost() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#33");
        clickOn("#33");
        clickOn("YES");
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $4.00"));
    }

    /**
     * Verifies that a crossbow can be successfully upgraded
     */
    @Test
    public void testUpgradeTankCost() {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn("#33");
        clickOn("#33");
        clickOn("YES");
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $40.00"));
    }

    /**
     * Verifies that upgrade is not possible with insufficient money
     */
    @Test
    public void testUpgradeImpossible() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn("#33");
        clickOn("#33");
        clickOn("YES");
        clickOn("OK");
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $0.00"));
    }


    /**
     * Verifies that upgraded cannon increases damage correctly
     * @throws InterruptedException exception
     */
    @Test
    public void testUpgradeDamage() throws InterruptedException {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#33");
        clickOn("#33");
        clickOn("YES");
        clickOn("Start");
        Thread.sleep(2000);
        verifyThat("#basicEnemyHealthLabel", LabeledMatchers.hasText("TROOP HP: 0"));

    }



}
