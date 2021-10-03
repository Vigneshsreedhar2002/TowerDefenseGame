import static org.testfx.api.FxAssert.verifyThat;

import com.example.judy.TowerDefenseApplication;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;


public class WelcomeScreenTest extends ApplicationTest {

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
     * Test to make sure start button functions
     */
    @Test
    public void testStartButton() {
        clickOn("#start");
    }

    /**
     * Test to make sure welcome text is there
     */
    @Test
    public void testWelcomeText() {
        verifyThat("#welcomeText", NodeMatchers.isNotNull());
    }
}
