package todoapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxRobot;
import org.testfx.api.FxAssert;
import org.testfx.matcher.control.LabeledMatchers;

@ExtendWith(ApplicationExtension.class)
class AppTest {

    /**
     * Method to create the application UI that will be shared among all the 
     * test cases.
     */
    @Start
    private static void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(App.class.getResource("appStructure.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }
    
    /**
     * Contains all the code that have to be executed before a test.
     */
    @BeforeAll
    private static void setUp () throws Exception {
    
    }

    /**
     * Contains all the code that have to be executed after a test.
     */
    @AfterAll
    private static void tearDown (FxRobot robot) throws Exception {
        // Hide the app window
        FxToolkit.hideStage();

        // Release keyboard and mouse
        robot.release(new KeyCode[]{});
        robot.release(new MouseButton[]{});
    }

    /**
     * 
     * @param robot
     */
    @Test
    void testInput(FxRobot robot) {
        robot.clickOn("#inputField");
        robot.write("This is a test!");
        robot.clickOn("#applyButton");
        FxAssert.verifyThat("#label", LabeledMatchers.hasText("This is a test!"));
    }
}