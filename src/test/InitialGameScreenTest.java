
import com.example.judy.TowerDefenseApplication;
import com.example.judy.controllers.InitialGameScreenController;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.testfx.api.FxAssert.verifyThat;

public class InitialGameScreenTest extends ApplicationTest {

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
     * Tests whether player money and monument health are equal
     * to what is expected of easy difficulty
     */
    @Test
    public void testEasyDifficulty() {
        clickOn("Easy");
        clickOn("Start");
        verifyThat("#scoreLabel", LabeledMatchers.hasText("SCORE: 0"));
        verifyThat("#levelLabel", LabeledMatchers.hasText("LEVEL: 0"));
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $200.00"));
        verifyThat("#healthLabel", LabeledMatchers.hasText("HEALTH: 100"));
    }

    /**
     * Tests whether player money and monument health are equal
     * to what is expected of medium difficulty
     */
    @Test
    public void testMediumDifficulty() {
        clickOn("Medium");
        clickOn("Start");
        verifyThat("#scoreLabel", LabeledMatchers.hasText("SCORE: 0"));
        verifyThat("#levelLabel", LabeledMatchers.hasText("LEVEL: 0"));
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $150.00"));
        verifyThat("#healthLabel", LabeledMatchers.hasText("HEALTH: 75"));
    }

    /**
     * Tests whether player money and monument health are equal
     * to what is expected of hard difficulty
     */
    @Test
    public void testHardDifficulty() {
        clickOn("Hard");
        clickOn("Start");
        verifyThat("#scoreLabel", LabeledMatchers.hasText("SCORE: 0"));
        verifyThat("#levelLabel", LabeledMatchers.hasText("LEVEL: 0"));
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $100.00"));
        verifyThat("#healthLabel", LabeledMatchers.hasText("HEALTH: 50"));
    }
    /**
     * Tests to make sure you can't place a tower in an invalid location
     */
    @Test
    public void testInvalidTowerPlace() {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn("#towerMenu");
        assertNotNull(InitialGameScreenController.getTowerToPlace());
    }
    /**
     * Tests to make sure you can place a tower in a valid location
     */
    @Test
    public void testValidTowerPlace() {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        clickOn("#inventoryMenu");
        clickOn("#tank");
        clickOn(240, 480);
        assertNull(InitialGameScreenController.getTowerToPlace());
    }
    /**
     * Tests the functionality of buying towers on the easy difficulty setting.
     */
    @Test
    public void testEasyBuyingFunctionality() throws InterruptedException {
        clickOn("Easy");
        clickOn("Start");

        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        Thread.sleep(200);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $125.00"));

        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        Thread.sleep(200);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $25.00"));
    }

    /**
     * Tests the functionality of buying towers on the medium difficulty setting.
     */
    @Test
    public void testMediumBuyingFunctionality() throws InterruptedException {
        clickOn("Medium");
        clickOn("Start");

        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        Thread.sleep(200);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $65.00"));

        clickOn("#towerMenu");
        clickOn("#cannon");
        clickOn("YES");
        Thread.sleep(200);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $5.00"));
    }

    /**
     * Tests the functionality of buying towers on the hard difficulty setting.
     */
    @Test
    public void testHardBuyingFunctionality() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");

        clickOn("#towerMenu");
        clickOn("#crossbow");
        clickOn("YES");
        Thread.sleep(200);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $5.00"));

        clickOn("#towerMenu");
        clickOn("#tank");
        clickOn("YES");
        Thread.sleep(200);
        verifyThat("#moneyLabel", LabeledMatchers.hasText("MONEY: $5.00"));
    }

}
