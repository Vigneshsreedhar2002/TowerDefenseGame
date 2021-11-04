
import com.example.judy.TowerDefenseApplication;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.LabeledMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class StartWaveTest extends ApplicationTest {

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
     * Tests if the start combat button exists and starts the next wave on click
     */
    @Test
    public void testStartNextWave() {
        clickOn("Easy");
        clickOn("Start");

    }

    /**
     * Verifies that any enemy released is moving
     */
    @Test
    public void testEnemyMoving() {
        clickOn("Medium");
        clickOn("Start");
    }

    /**
     * Verifies that enemies appear at the start of the map when clicking the start combat button
     */
    @Test
    public void testEnemyAppear() {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#startCombat");
        verifyThat("#30", Node::isFocusTraversable);
    }

    /**
     * Verifies that enemies are always on the right path
     * @throws InterruptedException exception
     */
    @Test
    public void testEnemyOnPath() throws InterruptedException {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#startCombat");
        verifyThat("#30", Node::isFocusTraversable);
        Thread.sleep(2000);
        verifyThat("#31", Node::isFocusTraversable);
        Thread.sleep(2000);
        verifyThat("#32", Node::isFocusTraversable);
        Thread.sleep(2000);
        verifyThat("#22", Node::isFocusTraversable);
        Thread.sleep(2000);
        verifyThat("#12", Node::isFocusTraversable);
    }

    /**
     * Verifies that the game over shows up when health is 0
     * @throws InterruptedException exception
     */
    @Test
    public void testMoveToGameOver() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#startCombat");
        Thread.sleep(16000);


    }

    /**
     * Verifies that the restart button resets the game to the welcome screen
     * @throws InterruptedException exception
     */
    @Test
    public void testRestartGame() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#startCombat");
        Thread.sleep(21000);
        clickOn("#restart");
        verifyThat("#welcomeText", NodeMatchers.isNotNull());

    }

    /**
     * Verifies if the enemy does the correct damage number to the monument
     * @throws InterruptedException exception
     */
    @Test
    public void testEnemyDamage() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#startCombat");
        Thread.sleep(15000);
        verifyThat("#healthLabel", LabeledMatchers.hasText("HP: 30"));
    }

    /**
     * Verifies that the enemy is moving at the correct speed for easy level
     * @throws InterruptedException exception
     */
    @Test
    public void testEnemyEasySpeed() throws InterruptedException {
        clickOn("Easy");
        clickOn("Start");
        clickOn("#startCombat");
        Thread.sleep(3500);
        verifyThat("#31", Node::isFocusTraversable);
    }

    /**
     * Verifies that the enemy is moving at the correct speed for hard level
     * @throws InterruptedException exception
     */
    @Test
    public void testEnemyMediumSpeed() throws InterruptedException {
        clickOn("Medium");
        clickOn("Start");
        clickOn("#startCombat");
        Thread.sleep(2500);
        verifyThat("#31", Node::isFocusTraversable);
    }

    /**
     * Verifies that the enemy is moving at the correct speed for hard level
     * @throws InterruptedException exception
     */
    @Test
    public void testEnemyHardSpeed() throws InterruptedException {
        clickOn("Hard");
        clickOn("Start");
        clickOn("#startCombat");
        Thread.sleep(1500);
        verifyThat("#31", Node::isFocusTraversable);
    }

}
