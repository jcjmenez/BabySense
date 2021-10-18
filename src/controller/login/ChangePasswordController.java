package controller.login;

import java.io.IOException;
import java.util.HashMap;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import util.Crypto;
import util.JsonHelper;

public class ChangePasswordController {

	private Stage mainWindow;

    @FXML
    private AnchorPane anchorLogin;
    
    @FXML
    private Label errorTF;

    @FXML
    private JFXPasswordField newTF;

    @FXML
    private JFXPasswordField confirmNewTF;

    @FXML
    private JFXButton changeBtn;

    @FXML
    private AnchorPane logoAnchor;
    
    private String userEmail = "";
    private HashMap<Integer, User> users = new HashMap<Integer, User>();
    private JsonHelper jsonHelper = new JsonHelper();
    
    
    public void setUserEmail(String userEmail) {
    	this.userEmail = userEmail;
    }
    /**
	 * Funcion que carga los usuarios y los guarda en el hashmap de usuarios
	 */
	public void loadUsers() {
		users = jsonHelper.deserializeJsonUsers("src/database/users/users.json");
	}
	
    
    
    
    public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}

    @FXML
    void changePassword(ActionEvent event) {
    	
    	 
    	
    	if (newTF.getText().length() > 0 && confirmNewTF.getText().length() > 0
    			&& newTF.getText().equals(confirmNewTF.getText())) {
    		loadUsers();
    		for (User user : users.values()) {
        		if (user.getMail().toLowerCase().equals(userEmail.toLowerCase())) {
        			Crypto c = new Crypto();
        			user.setPassword(c.hashStringSha256(newTF.getText()));
        		}
        	}
    		jsonHelper.serializeToJsonUsers("src/database/users/users.json", users);
    		try {
    			FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/login/LoginView.fxml"));
    			LoginController controller =  new LoginController();
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
    		errorTF.setText("Passwords don't match");
			newTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			new animatefx.animation.Shake(newTF).setSpeed(1).play();
			confirmNewTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			new animatefx.animation.Shake(confirmNewTF).setSpeed(1).play();
    	}
		
    }

}
