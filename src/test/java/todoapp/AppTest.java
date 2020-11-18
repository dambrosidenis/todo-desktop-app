package todoapp;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
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

    private static Button btn;

    /**
     * Method to create the application UI that will be shared among all the 
     * test cases.
     */
    @Start
    private static void start (Stage stage) throws Exception {
        btn = new Button("Bottone di prova");
        btn.setOnAction(actionEvent -> btn.setText("Funziona!"));

        StackPane root = new StackPane();
        root.getChildren().addAll(btn);
        Scene scene = new Scene(root,600,400);

        stage.setScene(scene);
        stage.setTitle("App di Prova");
        stage.show();
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

        // Release mouse
        robot.release(new MouseButton[]{});
    }

    @Test
    void existButtonWithText(FxRobot robot) {
        FxAssert.verifyThat(btn, LabeledMatchers.hasText("Bottone di prova"));
    }

    @Test
    void textChangesWhenClicked(FxRobot robot) {
        robot.clickOn(btn);
        FxAssert.verifyThat(btn, LabeledMatchers.hasText("Funziona!"));
    }
}