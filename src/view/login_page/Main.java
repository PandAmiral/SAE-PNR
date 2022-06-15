package view.login_page;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {

    private Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../formulaires/Formulaire_chouette.fxml"));
        primaryStage.setTitle("PNR");
        primaryStage.setScene(new Scene(root, screenBounds.getWidth(), screenBounds.getHeight() * 0.97));
        primaryStage.show();
    }

    public static void main(String[] args) {
        
        launch(args);
    }
}
