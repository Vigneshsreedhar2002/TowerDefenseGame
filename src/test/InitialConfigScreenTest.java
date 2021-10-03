
import com.example.judy.TowerDefenseApplication;
import com.example.judy.controllers.WelcomeScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.testfx.framework.junit.ApplicationTest;

/**
 * Tests cases for the initial configuration screen in which players are supposed to select a name and difficulty.
 */
public class InitialConfigScreenTest extends ApplicationTest {
    private Node input;

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
        clickOn("#start");
        input = lookup("#nameInput").query();
    }

    /**
     * Tests to make sure that the player can move onto the initial
     * game screen when they enter a valid name and difficulty.
     */
    @Test
    public void testBlanks() {
        clickOn(input);
        write("  ");
        clickOn("Enter");
        clickOn(input);
        write("Test");
        clickOn("Start");
        clickOn("Easy");
        clickOn("Start");

    }
}