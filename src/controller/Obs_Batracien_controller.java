package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.stage.Window;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


/**
 * The controller of the page Formulaire_obs_batracien.fxml. It manages it.
 * @version 1.2
 */
public class Obs_Batracien_controller{
    
    @FXML
    private DatePicker date;

    @FXML
    private TextField heureObservation;

    @FXML
    private TextField lambertX;

    @FXML
    private TextField lambertY;


    @FXML
    /**
     * The combobox that contains the information about the sky's weather in the fxml file.
     */
    private ComboBox<String> meteo_ciel;


    @FXML
    /**
     * The combobox that contains the information about the wind's weather in the fxml file.
     */
    private ComboBox<String> meteo_vent;


    @FXML
    /**
     * The combobox that contains the information about the rain's weather in the fxml file.
     */
    private ComboBox<String> meteo_pluie;


    @FXML
    /**
     * The combobox that contains the information about the time's weather in the fxml file.
     */
    private ComboBox<String> meteo_temps;


    /**
     * An ObservableList<String> that will contain the list of elements to add to the
     * different combobx.
     */
    private ObservableList<String> liste;

    @FXML
    /**
     * text field for the number of fly
     */
    private TextField numZoneHumide;

    @FXML
    /**
     * text field for the number of fly
     */
    private TextField numVegetation;

    @FXML
    /**
     * text field for the number of fly
     */
    private TextField temperature;

    @FXML
    /**
     * Button to insert the data in the database
     */
    private Button effectuer;


    @FXML
    /**
     * Initialize elements when the fxml file is dilpayed
     */
    private void initialize() 
    {


        liste = FXCollections.observableArrayList("dégagé", "semi-dégagé", "nuageux");
        meteo_ciel.setItems(liste);

        liste = FXCollections.observableArrayList("non", "léger", "moyen", "fort");
        meteo_vent.setItems(liste);

        liste = FXCollections.observableArrayList("non", "légère", "moyenne", "forte");
        meteo_pluie.setItems(liste);

        liste = FXCollections.observableArrayList("froid", "moyen", "chaud");
        meteo_temps.setItems(liste);
    }



    /**
     * Method who create the message and show it in the screen
     * @param alertType Type of the Alert (CONFIRMATION OR ERROR)
     * @param owner
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
    * Event to do when the button retour is pressed.
    * Switch to the page Accueil_Utilisateur.fxml
    */
    public void retour(){

        Stage actuel = (Stage)meteo_ciel.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        System.out.println(ReadInfos.readAdmin());
        if(ReadInfos.readAdmin() == true){

            change.go_to("../view/Accueil_Admin.fxml");
        }else{

            change.go_to("../view/Accueil_Utilisateur.fxml");
        }
    }


    public void toEspece(){



        Stage actuel = (Stage)meteo_ciel.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../view/formulaires/Formulaire_obs_batracien_espece.fxml");    
        

        Window owner = meteo_temps.getScene().getWindow();
        //test : textfield vide
        if (meteo_ciel.getPromptText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        //test : textfield vide
        if (meteo_pluie.getPromptText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        //test : textfield vide
        if (meteo_temps.getPromptText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        //test : textfield vide
        if (meteo_vent.getPromptText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        if (numZoneHumide.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        if (numVegetation.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        if (temperature.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "OBS Error!",
                "Please enter good coordonnée");

        }
        
        try {
            FileWriter f = new FileWriter("obsBatracien.txt");
            PrintWriter out = new PrintWriter(f);
            out.println(this.date.getValue());
            out.println(this.heureObservation.getText());
            out.println(this.lambertX.getText());
            out.println(this.lambertY.getText());
            out.println(this.temperature.getText());
            out.println(this.meteo_ciel.getValue());
            out.println(this.meteo_temps.getValue());
            out.println(this.meteo_vent.getValue());
            out.println(this.meteo_pluie.getValue());
            out.println(this.numZoneHumide.getText());
            out.println(this.numVegetation.getText());
            out.close();

        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }



    
}
