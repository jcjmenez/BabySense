package controller;

// JavaFX imports
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

// Custom imports
import java.io.IOException;
import java.util.HashMap;
import model.User;
import util.Crypto;
import util.JsonHelper;


public class LoginController {
	
	@FXML
	private AnchorPane logoAnchor;

    @FXML
    private AnchorPane anchorLogin;
	
    @FXML
    private Label errorTF;

    @FXML
    private JFXTextField userTF;

    @FXML
    private JFXPasswordField passTF;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private Hyperlink forgotTF;

    @FXML
    private JFXButton newBtn;
    
    
    private Stage mainWindow;
    
    private HashMap<Integer, User> users = new HashMap<Integer, User>();
    private JsonHelper jsonHelper = new JsonHelper();
    
    public Stage getMainWindow() {
		return mainWindow;
	}

	public void setMainWindow(Stage mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * Funcion que empieza la animacion de slide cuando se inicia el programa
	 */
	public void startSlideAnim() {
		new animatefx.animation.ZoomInLeft(logoAnchor).setSpeed(0.3).play();
		FadeTransition fade = new FadeTransition();
		fade.setNode(anchorLogin);
		fade.setFromValue(0);
		fade.setToValue(1);
		fade.setDuration(Duration.seconds(1));
		fade.play();
		new animatefx.animation.SlideInLeft(anchorLogin).setSpeed(0.9).play();
	}
	
	/**
	 * Funcion que carga los usuarios y los guarda en el hashmap de usuarios
	 */
	public void loadUsers() {
		users = jsonHelper.deserializeJsonUsers("src/database/users/users.json");
	}
	
	/**
	 * Funcion login
	 * @param event
	 */
	@FXML
    void login(ActionEvent event) {
		try {
			boolean logged = false;
			User loggedUser = null;
			loadUsers();
			for (User user : users.values()) {
				Crypto hasher = new Crypto();
				/*
				 *  Si el usuario o email y la contrase�a concuerdan con los datos
				 *  almacenados en la base de datos se loguea
				 */
				if ((userTF.getText().toLowerCase().equals(user.getUsername())
						|| userTF.getText().toLowerCase().equals(user.getMail()))
						&& hasher.hashStringSha256(passTF.getText()).equals(user.getPassword())) {
					logged = true;
					loggedUser = user;
					break;
				}
			}
			// TODO: Cambiar a ventana de usuario una vez logueado
			if (logged) {
				System.out.println("Logged user type = " + loggedUser.getType());
				FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/parent/PacienteView.fxml"));
				PacienteController controller =  new PacienteController();
				loader.setController(controller);
				Parent node;
				node = loader.load();
				controller.setText(userTF.getText());
				Scene scene = new Scene(node, mainWindow.getScene().getWidth(), mainWindow.getScene().getHeight());
				mainWindow.setScene(scene);
				
			}
			else {
				// Animaciones de error
				userTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				new animatefx.animation.Shake(userTF).setSpeed(1).play();
				passTF.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				new animatefx.animation.Shake(passTF).setSpeed(1).play();
				// Cambiamos el texto oculto para mostrar que el usuario se ha equivocado
				errorTF.setText("Username or password incorrect");
			}
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
	 * TODO: Sistema de recuperacion de contraseñas con verificacion por email
	 * @param event
	 */
	@FXML
	void forgotPassword(ActionEvent event) {
		System.out.println("HELLO");
	}
	
    @FXML
    void switchRegister(ActionEvent event) {
		try {
			FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/login/RegisterView.fxml"));
			RegisterController controller =  new RegisterController();
			loader.setController(controller);
			Parent node;
			node = loader.load();
			Scene scene = new Scene(node, mainWindow.getScene().getWidth(), mainWindow.getScene().getHeight());
			mainWindow.setScene(scene);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}