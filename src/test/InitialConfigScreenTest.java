
import com.example.judy.TowerDefenseApplication;
import com.example.judy.controllers.InitialConfigScreenController;
import com.example.judy.controllers.InitialGameScreenController;
import com.example.judy.controllers.WelcomeScreenController;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;


import static org.testfx.api.FxAssert.verifyThat;

/**
 * Tests cases for the initial configuration screen in which players are supposed to select a name and difficulty.
 */
public class InitialConfigScreenTest extends ApplicationTest {
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
     * Traverses to the initial configuration screen for testing.
     */
    @Before
    public void traverseToInitialConfigScreen() {
        clickOn("Next");
    }

    /**
     * Tests to make sure that the player can move onto the initial
     * game screen when they enter a valid name and difficulty.
     */
    @Test
    public void testNameAndDifficulty() {
        type(KeyCode.A);
        clickOn("Enter");
        clickOn("Easy");
        clickOn("Start");

        verifyThat("#initialGameText", NodeMatchers.isNotNull());
    }
}