
import com.example.judy.TowerDefenseApplication;
import com.example.judy.controllers.InitialConfigScreenController;
import com.example.judy.controllers.InitialGameScreenController;
import com.example.judy.controllers.WelcomeScreenController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;


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
     * Traverses to the initial game screen for testing.
     */
    @Before
    public void traverseToGameScreen() {
        clickOn("#start");
        Node input = lookup("#nameInput").query();
        clickOn(input);
        write("Test");
        clickOn("Easy");
        clickOn("Start");
    }


}
