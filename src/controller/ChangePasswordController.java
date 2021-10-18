package controller;

import java.io.IOException;
import java.util.HashMap;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private JFXTextField newTF;

    @FXML
    private JFXTextField confirmNewTF;

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
    	
    	 
    	
    	if (newTF.getText().equals(confirmNewTF.getText())) {
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
		
    }

}
