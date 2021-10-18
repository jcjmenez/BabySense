package controller;

// JavaFX imports
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    private JFXPasswordField passwordTF;

    @FXML
    private JFXButton signBtn;
    
    private Stage mainWindow;
    private HashMap<Integer, User> users = new HashMap<Integer, User>();
	private JsonHelper jsonHelper = new JsonHelper();
	private FieldValidator validator = new FieldValidator();
	
	
	public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * Funcion que carga los usuarios y los guarda en el hashmap de usuarios
	 */
	public void loadUsers() {
		users = jsonHelper.deserializeJsonUsers("src/database/users/users.json");
	}
	
    @FXML
	void signUp(ActionEvent event) {
		Crypto hasher = new Crypto();
		loadUsers();
		// Si los campos son validos, se crea el nuevo usuario y se registra en la base de datos
		if (nameTF.getText().length() > 0 && surnameTF.getText().length() > 0
				&& validator.isEmailValid(emailTF.getText())
				&& validator.isUsernameValid(usernameTF.getText(), users)
				&& validator.isPasswordValid(passwordTF.getText())) {
			// TODO: Enviar correo de verificacion con un codigo usando:
			// hasher.generateVericationCode(6)
			User signedUser = new User("Parent", nameTF.getText(), surnameTF.getText(), emailTF.getText(), 
					usernameTF.getText().toLowerCase(), hasher.hashStringSha256(passwordTF.getText()));
			users.put(signedUser.getId(), signedUser);
			jsonHelper.serializeToJsonUsers("src/database/users/users.json", users);
		}
		// Si no son validos, mostramos los errores por animaciones
		else {
			errorTF.setText("Incorrect fields");
			if (nameTF.getText().length() <= 0) {
				nameTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				new animatefx.animation.Shake(nameTF).setSpeed(1).play();
			}
			else {
				nameTF.setStyle(null);
			}
			if (surnameTF.getText().length() <= 0) {
				surnameTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				new animatefx.animation.Shake(surnameTF).setSpeed(1).play();
			}
			else {
				surnameTF.setStyle(null);
			}
			if (!validator.isUsernameValid(usernameTF.getText(), users)) {
				usernameTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				new animatefx.animation.Shake(usernameTF).setSpeed(1).play();
			}
			else {
				usernameTF.setStyle(null);
			}
			if (!validator.isEmailValid(emailTF.getText())) {
				emailTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				new animatefx.animation.Shake(emailTF).setSpeed(1).play();
			}
			else {
				emailTF.setStyle(null);
			}
			if (!validator.isPasswordValid(passwordTF.getText())) {
				passwordTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				new animatefx.animation.Shake(passwordTF).setSpeed(1).play();
			}
			else {
				passwordTF.setStyle(null);
			}
		}		
	}
}
