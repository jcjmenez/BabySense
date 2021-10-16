package controller;

// JavaFX imports
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

// Custom imports
import model.User;
import util.Crypto;
import util.FieldValidator;
import util.JsonHelper;
import java.util.HashMap;



public class RegisterController{

    @FXML
    private AnchorPane anchorLogin;

    @FXML
    private Label errorTF;

    @FXML
    private JFXTextField nameTF;

    @FXML
    private JFXTextField surnameTF;

    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXTextField emailTF;

    @FXML
    private JFXTextField passwordTF;

    @FXML
    private JFXButton signBtn;
    
    private HashMap<Integer, User> users = new HashMap<Integer, User>();
	private JsonHelper jsonHelper = new JsonHelper();
	/**
	 * Funcion que carga los usuarios y los guarda en el hashmap de usuarios
	 */
	public void loadUsers() {
		users = jsonHelper.deserializeJsonUsers("src/database/users/users.json");
	}
    
	@FXML
	void signUp(ActionEvent event) {
		FieldValidator validator = new FieldValidator();
		Crypto hasher = new Crypto();
		loadUsers();
		if (validator.isEmailValid(emailTF.getText()) && usernameTF.getText().length() > 3 
				&& validator.isUsernameValid(usernameTF.getText(), users)
				&& validator.isPasswordValid(passwordTF.getText()) ) {
			User signedUser = new User("Parent", nameTF.getText(), surnameTF.getText(), emailTF.getText(), 
					usernameTF.getText(), hasher.hashStringSha256(passwordTF.getText()));
			users.put(signedUser.getId(), signedUser);
			jsonHelper.serializeToJsonUsers("src/database/users/users.json", users);
		}
		
		
	}

}


