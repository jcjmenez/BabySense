package controller.login;


import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class VerificationController {

    @FXML
    private AnchorPane anchorLogin;
    
    @FXML
    private Label errorTF;

    @FXML
    private JFXTextField codeTF;

    @FXML
    private JFXButton verifyBtn;

    @FXML
    private AnchorPane logoAnchor;
    
    private Stage mainWindow;
    
    private String userEmail = "";
    private String verificationCode = "";
    
    public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}
	
    public void setUserEmail(String userEmail) {
    	this.userEmail = userEmail;
    }
	
    public void setVerificationCode(String verificationCode)  {
    	this.verificationCode = verificationCode;
    }
 
    @FXML
    void verifyCode(ActionEvent event) {
    	if (codeTF.getText().toLowerCase().equals(verificationCode.toLowerCase())) {
    		try {
    			FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/login/ChangePasswordView.fxml"));
    			ChangePasswordController controller =  new ChangePasswordController();
    			controller.setUserEmail(userEmail);
    			controller.setMainWindow(mainWindow);
    			loader.setController(controller);
    			Parent node;
    			node = loader.load();
    			Scene scene = new Scene(node, mainWindow.getScene().getWidth(), mainWindow.getScene().getHeight());
    			mainWindow.setScene(scene);
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	else {
    		// Animaciones de error
    		errorTF.setText("Invalid code");
			codeTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			new animatefx.animation.Shake(codeTF).setSpeed(1).play();
    	}

    }

}
