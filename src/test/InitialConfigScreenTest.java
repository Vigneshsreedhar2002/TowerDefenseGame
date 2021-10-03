
import com.example.judy.TowerDefenseApplication;
import com.example.judy.controllers.InitialConfigScreenController;
import com.example.judy.controllers.InitialGameScreenController;
import com.example.judy.controllers.WelcomeScreenController;

import javafx.stage.Stage;
import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;


import static org.testfx.api.FxAssert.verifyThat;

public class InitialConfigScreenTest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        TowerDefenseApplication game = new TowerDefenseApplication();
        game.start(stage);
    }

}