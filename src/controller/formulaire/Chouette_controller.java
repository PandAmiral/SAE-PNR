package controller.formulaire;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.sql.SQLException;

import controller.utilitaires.ChangerPage;
import controller.utilitaires.ReadInfos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Window;
import java.sql.*;
/**
 * The controller of the page Formulaire_chouette.fxml. It manages it.
 * @version 1.3
 */
public class Chouette_controller {

    /**
     * An ObservableList<String> that will contain the list of elements to add to the
     * different combobx.
     */
    private ObservableList<String> liste; 
    
    @FXML
    /**
     * Button to insert the data in the database
     */
    private Button effectuer;

    @FXML
    /**
     * The account button in the fxml file
     */
    private Button nom_compte;

    @FXML
    /**
     * Text field to insert the id of the animal
     */
    private TextField numIndivid;

    @FXML
    /**
     * Combo box to select the species of observation
     */
    private ComboBox<String> espece;

    @FXML
    /**
     * The combobox that contain the gender in the fxml file.
     */
    private ComboBox<String> sexe;

    @FXML
    /**
     * The account button in the fxml file
     */
    private Button user;

    @FXML
    /**
     * Initialize elements when the fxml file is displayed
     */
    private void initialize() 
    {
        liste = FXCollections.observableArrayList("EFFRAIE","CHEVECHE","HULOTTE");
        espece.setItems(liste);

        liste = FXCollections.observableArrayList("MALE","FEMELLE","INCONNU");
        sexe.setItems(liste);
        user.setText(ReadInfos.getStatus());


    }

    @FXML
    /**
     * Method to create a insert querry to the database
     * @throws SQLException if the querry is not well written
     */
    private void insert() throws SQLException{
        Window owner = effectuer.getScene().getWindow();
        //test : textfield vide
        if (espece.getValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        //test : textfield vide
        else if (sexe.getValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }

        else if (numIndivid.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        
        else{

        
            //création de l'insert
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "base_donnee", "sC32DnE3ae7Y");
                Statement ChouetteController = c.createStatement();
                
                PreparedStatement testChouette = c.prepareStatement("SELECT * FROM Chouette WHERE numIndividu = ?");
                testChouette.setString(1, numIndivid.getText());
                ResultSet resultatChouette = testChouette.executeQuery();

                if(resultatChouette.next()){
                    showAlert(Alert.AlertType.ERROR, owner, "Chouette", "Chouette déjà rentré!");
                }
                else{
                    String querry1 = "INSERT INTO CHOUETTE VALUES('"+ numIndivid.getText() +"', '" + espece.getValue() + "', '" + sexe.getValue() + "');";
                    ChouetteController.executeUpdate(querry1);
                    showAlert(Alert.AlertType.CONFIRMATION, owner, "Observation", "rentré!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method who create the message and show it in the screen
     * @param alertType Type of the Alert (CONFIRMATION OR ERROR)
     * @param owner The owner of the window
     * @param title Title of the message screen
     * @param message Message who appear in screen
     */
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


    /**
     * Event to do when the button aObservation is pressed.
     * Switch to the page Formulaire_obs_chouette.fxml
     */
    public void to_obs(){

        Stage actuel = (Stage)sexe.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../view/formulaires/Formulaire_obs_chouette.fxml");
    }

    /**
    * Event to do when the button aObservation is pressed.
    * Switch to the page Accueil_Utilisateur.fxml
    */
   public void retour(){

       Stage actuel = (Stage)sexe.getScene().getWindow();
       ChangerPage change = new ChangerPage(actuel);
       change.go_to("../view/formulaires/Formulaire_obs_chouette.fxml");
   }
}
