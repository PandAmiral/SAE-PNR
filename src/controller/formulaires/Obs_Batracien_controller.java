package controller.formulaires;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.SQLException;

import controller.utilitaires.ChangerPage;
import controller.utilitaires.ReadInfos;
import javafx.scene.control.Alert;
import javafx.stage.Window;

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
    /**
     * DatePicker from the fxml file to select the date of the observation
     */
    private DatePicker date;

    @FXML
    /**
     * TextField from the fxml file to input the time of the observation
     */
    private TextField heureObservation;

    @FXML
    /**
     * TextField from the fxml file to input the x coordinate
     */
    private TextField lambertX;

    @FXML
    /**
     * TextField from the fxml file to input the y coordinate
     */
    private TextField lambertY;

    @FXML
    /**
     * Button from the fxml file to display the current account
     */
    private Button user;


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
     * text field for id of the wetland
     */
    private TextField numZoneHumide;

    @FXML
    /**
     * text field for the id of the vegetation
     */
    private TextField numVegetation;

    @FXML
    /**
     * text field for the temperature
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


        liste = FXCollections.observableArrayList("d??gag??", "semi-d??gag??", "nuageux");
        meteo_ciel.setItems(liste);

        liste = FXCollections.observableArrayList("non", "l??ger", "moyen", "fort");
        meteo_vent.setItems(liste);

        liste = FXCollections.observableArrayList("non", "l??g??re", "moyenne", "forte");
        meteo_pluie.setItems(liste);

        liste = FXCollections.observableArrayList("froid", "moyen", "chaud");
        meteo_temps.setItems(liste);
        user.setText(ReadInfos.getStatus());

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
    * Event to do when the button retour is pressed.
    * Switch to the page Accueil_Utilisateur.fxml
    */
    public void retour(){

        Stage actuel = (Stage)meteo_ciel.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        if(ReadInfos.estAdmin()){

            change.go_to("../../view/Accueil_Admin.fxml");
        }else{

            change.go_to("../../view/Accueil_Utilisateur.fxml");
        }
    }

    /**
     * Check if the data is correct and insert it in the database
     * @throws SQLException if the data is not correct
     */
    public void toEspece() throws SQLException{ 

        Stage actuel = (Stage)meteo_ciel.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/formulaires/Formulaire_obs_batracien_espece.fxml");   

        Window owner = meteo_temps.getScene().getWindow();
        //test : textfield vide
        if (meteo_ciel.getValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur",
                "Veuillez remplir tous les champs");

        }
        //test : textfield vide
        else if (meteo_pluie.getValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur",
                "Veuillez remplir tous les champs");

        }
        //test : textfield vide
        else if (meteo_temps.getValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur",
                "Veuillez remplir tous les champs");

        }
        //test : textfield vide
        else if (meteo_vent.getValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur",
                "Veuillez remplir tous les champs");

        }
        else if (numZoneHumide.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur",
            "Veuillez remplir tous les champs");

        }
        else if (numVegetation.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur",
                "Veuillez remplir tous les champs");

        }
        else if (temperature.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Erreur",
                "Veuillez remplir tous les champs");

        }
        
        else{

        
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

                try{                
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "base_donnee", "sC32DnE3ae7Y");
                    PreparedStatement testZH = c.prepareStatement("SELECT zh_id FROM zonehumide WHERE zh_id = ?");
                    testZH.setString(1, numZoneHumide.getText());
                    ResultSet resultatZH = testZH.executeQuery();

                    if(resultatZH.next()){
                        out.println(this.numZoneHumide.getText());
                    }
                    else{
                        showAlert(Alert.AlertType.ERROR, owner, "zoneHumide", "Zone Huminde non r??f??renc??!");
                    }
                    
                    PreparedStatement testV = c.prepareStatement("SELECT idVege FROM vegetation WHERE idVege = ?");
                    testV.setString(1, numVegetation.getText());
                    ResultSet resultatV = testV.executeQuery();

                    if(resultatV.next()){
                        out.println(this.numVegetation.getText());
                    }
                    else{
                        showAlert(Alert.AlertType.ERROR, owner, "vegetation", "Vegetation non r??f??renc??!");
                    }

                }catch (Exception e) {
                    e.printStackTrace();
                }
                
                out.close();

            } catch (IOException e) {

                System.out.println(e.getMessage());
            }
        }
    }   
}