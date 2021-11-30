import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.GameAdmin;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import static org.testfx.api.FxAssert.verifyThat;
import static org.junit.Assert.assertEquals;

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

    /**
     * Checks to ensure that the win screen appears after killing the boss.
     * @throws InterruptedException Exception
     */
    @Test
    public void testWinScreen() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn("#33");
        clickOn("Start");
        Thread.sleep(12000);
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#23");
        clickOn("Start");
        Thread.sleep(18000);
        verifyThat("#statsText", NodeMatchers.isNotNull());
    }

    /**
     * Tests that, upon completing the game, the player is sent back to the welcome screen.
     * @throws InterruptedException Exception
     */
    @Test
    public void testWinReplay() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn("#33");
        clickOn("Start");
        Thread.sleep(12000);
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#23");
        clickOn("Start");
        Thread.sleep(18000);
        clickOn("RESTART");
        verifyThat("#welcomeText", NodeMatchers.isNotNull());
    }

    /**
     * Tests that the game displays the correct stats upon winning.
     * @throws InterruptedException Exception
     */
    @Test
    public void testWinStats() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn("#33");
        clickOn("Start");
        Thread.sleep(12000);
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#23");
        clickOn("Start");
        Thread.sleep(19000);
        verifyThat("#statsText", NodeMatchers.isNotNull());
        assertEquals(GameAdmin.getGame().getPlayer().getScore(), 425);
        assertEquals(GameAdmin.getGame().getMonument().getMaxHealth()
                - GameAdmin.getGame().getMonument().getHealth(), 0);
        assertEquals(GameAdmin.getGame().getDmgDealt(), 660);

    }

    /**
     * Tests that the game displays the correct name upon winning.
     * @throws InterruptedException Exception
     */
    @Test
    public void testCorrectName() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn("#33");
        clickOn("Start");
        Thread.sleep(12000);
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#23");
        clickOn("Start");
        Thread.sleep(19000);
        assertEquals(GameAdmin.getGame().getPlayer().getName(), "Test");

    }

    /**
     * Tests that replaying without closing the game resets all values and nothing is
     * carried over from the previous game.
     * @throws InterruptedException Exception
     */
    @Test
    public void testMultipleGames() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn("#33");
        clickOn("Start");
        Thread.sleep(12000);
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#23");
        clickOn("Start");
        Thread.sleep(18000);
        clickOn("RESTART");
        traverseToInitialConfigScreen();
        testWinStats();

    }
}
