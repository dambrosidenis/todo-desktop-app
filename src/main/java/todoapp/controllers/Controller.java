package todoapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    TextField inputField;

    @FXML
    Label label;

    @FXML
    Button applyButton;

    /**
     * Method to handle the click on the button "applyButton". It will set the 
     * label text with the input text entered.
     */
    public void applyButtonClicked () {
        label.setText(inputField.getText());
    }
}