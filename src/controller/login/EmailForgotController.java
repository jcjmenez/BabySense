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
import util.Crypto;
import util.FieldValidator;

public class EmailForgotController {
	
    private Stage mainWindow;

    
    public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}

	@FXML
    private AnchorPane anchorLogin;
	
	@FXML
    private Label errorTF;
	
    @FXML
    private JFXTextField emailTF;

    @FXML
    private JFXButton sendBtn;

    @FXML
    private AnchorPane logoAnchor;


    @FXML
    void sendVerification(ActionEvent event) {
    	FieldValidator validator = new FieldValidator();
    	if (validator.isEmailValid(emailTF.getText())) {
    		Crypto c = new Crypto();
        	String verificationCode = c.generateVericationCode(6);
        	// TODO: Enviar el email
        	System.out.println(verificationCode);
    		try {
    			FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/login/VerificationView.fxml"));
    			VerificationController controller =  new VerificationController();
    			controller.setUserEmail(emailTF.getText());
    			controller.setMainWindow(mainWindow);
    			controller.setVerificationCode(verificationCode);
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
    		errorTF.setText("Enter a valid email");
			emailTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			new animatefx.animation.Shake(emailTF).setSpeed(1).play();
    	}
    	
    }

	

	
	

}

