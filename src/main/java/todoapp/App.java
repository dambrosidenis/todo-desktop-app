package todoapp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class App extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Contains all the instructions that have to be executed only at the 
     * launch of the application.
     */
    @Override
    public void init() throws Exception {
        super.init();
    }
    
    /**
     * Contains the 
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("appStructure.fxml"));
        root.getStylesheets().add(getClass().getResource("appStyle.css").toString());

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}