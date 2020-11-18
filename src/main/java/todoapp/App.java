package todoapp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;

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
        Button btn = new Button("Bottone di prova");
        btn.setOnAction(actionEvent -> btn.setText("Funziona!"));

        StackPane root = new StackPane();
        root.getChildren().addAll(btn);
        Scene scene = new Scene(root,600,400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("App di Prova");
        primaryStage.show();
    }
}