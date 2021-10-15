package application;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import util.SoundPlayer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
			LoginController controller =  new LoginController();
			loader.setController(controller);
			controller.setMainWindow(primaryStage);
			Parent node = loader.load();
			Scene scene = new Scene(node);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("BabySense");
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("../assets/images/logo.png")));
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(700);
			primaryStage.setMinHeight(600);
			primaryStage.show();
			SoundPlayer sp = new SoundPlayer();
			sp.playSound("login.wav");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}