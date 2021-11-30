import com.example.judy.TowerDefenseApplication;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import static org.testfx.api.FxAssert.verifyThat;

public class TowerCombatTest extends ApplicationTest {

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
     * Verifies that when an enemy passes the appropriate
     * location, the enemy health decreases
     * @throws InterruptedException exception
     */
    @Test
    public void testTowerCombat() throws InterruptedException {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#33");
        clickOn("Start");
        Thread.sleep(2000);
        verifyThat("#strongEnemyHealthLabel", LabeledMatchers.hasText("LT. HP: 100"));
    }

    /**
     * Verifies that when an enemy passes some faraway
     * location, the enemy health remains the same
     * @throws InterruptedException exception
     */
    @Test
    public void testTowerNotCombat() throws InterruptedException {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#47");
        clickOn("Start");
        Thread.sleep(2500);
        verifyThat("#basicEnemyHealthLabel", LabeledMatchers.hasText("TROOP HP: 100"));
    }

    /**
     * Verifies that correct money is gained after killing enemies on easy
     * @throws InterruptedException exception
     */
    @Test
    public void testMoneyGainOnEasy() throws InterruptedException {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#11");
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#17");
        clickOn("#startCombat");
        Thread.sleep(19000);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $155.00"));
    }

    /**
     * Verifies that correct money is gained after killing enemies on medium
     * @throws InterruptedException exception
     */
    @Test
    public void testMoneyGainOnMedium() throws InterruptedException {
        clickOn("Medium");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#11");
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#17");
        clickOn("#startCombat");
        Thread.sleep(19000);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $95.00"));
    }

    /**
     * Verifies that correct money is gained after killing enemies on hard
     * @throws InterruptedException exception
     */
    @Test
    public void testMoneyGainOnHard() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#11");
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#17");
        clickOn("#startCombat");
        Thread.sleep(19000);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $80.00"));
    }

    /**
     * Verifies that score is gained after killing enemies
     * @throws InterruptedException exception
     */
    @Test
    public void testScoreGain() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#11");
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#17");
        clickOn("#startCombat");
        Thread.sleep(19000);
        verifyThat("#scoreLabel", LabeledMatchers.hasText("SCORE: 175"));
    }


    /**
     * Verifies that the player can buy and place tower during combat
     * and that the tower will immediately start attacking nearby enemies
     * @throws InterruptedException exception
     */
    @Test
    public void testPlaceTowerDuringWave() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#startCombat");
        Thread.sleep(1000);
        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#crossbow");
        clickOn("#24");
        Thread.sleep(6000);
        verifyThat("#basicEnemyHealthLabel", LabeledMatchers.hasText("TROOP HP: 126"));
    }

    /**
     * Verifies that the tower does the correct damage on easy
     * @throws InterruptedException exception
     */
    @Test
    public void testTowerDamageOnEasy() throws InterruptedException {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#24");
        clickOn("#startCombat");
        Thread.sleep(5000);
        verifyThat("#basicEnemyHealthLabel", LabeledMatchers.hasText("TROOP HP: 75"));
    }

    /**
     * Verifies that the tower does the correct damage on medium
     * @throws InterruptedException exception
     */
    @Test
    public void testTowerDamageOnMedium() throws InterruptedException {
        clickOn("Medium");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#24");
        clickOn("#startCombat");
        Thread.sleep(5000);
        verifyThat("#basicEnemyHealthLabel", LabeledMatchers.hasText("TROOP HP: 105"));
    }

    /**
     * Verifies that the tower does the correct damage on hard
     * @throws InterruptedException exception
     */
    @Test
    public void testTowerDamageOnHard() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#cannon");
        clickOn("#24");
        clickOn("#startCombat");
        Thread.sleep(5000);
        verifyThat("#basicEnemyHealthLabel", LabeledMatchers.hasText("TROOP HP: 135"));
    }

}
