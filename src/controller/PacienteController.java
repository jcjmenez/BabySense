package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PacienteController {

    @FXML
    private Label label;
    
    public void setText(String text) {
    	label.setText(text);
    }

}
