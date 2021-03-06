package controller;
import java.io.IOException;
import java.sql.SQLException;

import org.kordamp.ikonli.javafx.FontIcon;

import controller.utilitaires.ChangerPage;
import controller.utilitaires.ExportData;
import controller.utilitaires.ReadInfos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Choix_Stats_controller {

    @FXML
    /**
     * The account button in the fxml file
     */
    private Button user;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem Hippocampe;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem batracien;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem chouette;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem gci;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem lieu;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private Button liste;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem loutre;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem nid_gci;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem observateur;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private FontIcon retour;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private Button stat;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem tout;


    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem vegetation;

    @FXML
    /**
     * The MenuItem button in the fxml file
     */
    private MenuItem zonehumide;

    @FXML
    /**
     * Exports every table in the database to a csv file
     * @param event the event
     * @throws SQLException if the database is not accessible
     * @throws IOException if the file is not accessible
     * @throws ClassNotFoundException if the class is not found
     */
    void exportation_tout(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.exportAll();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Toutes les tables ont ??t?? export??es avec succ??s");
    }

    @FXML
    /**
     * Exports the data of the "a_observe" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_a_observe(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("aobserve");
        ExportData.writeCSV("observateur");
        ExportData.writeCSV("observation");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Tables aobserve, observateur et observation export??es avec succ??s");
    }

    @FXML
    /**
     * Exports the data of the "lieu" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_lieu(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("lieu");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Table lieu export??e avec succ??s");

    }

    @FXML
    /**
     * Exports the data of the "nid_gci" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_nid_gci(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("nid_gci");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Table nid_gci export??e avec succ??s");

    }

    @FXML
    /**
     * Exports the data of the "obs_batracien" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_obs_batracien(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("obs_batracien");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Table obs_batracien export??e avec succ??s");
    }

    @FXML
    /**
     * Exports the data of the "obs_chouette" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_obs_chouette(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("obs_chouette");
        ExportData.writeCSV("chouette");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Tables obs_chouette et chouette export??es avec succ??s");
    }

    @FXML
    /**
     * Exports the data of the "obs_gci" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_obs_gci(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("obs_gci");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Table obs_gci export??e avec succ??s");
    }

    @FXML
    /**
     * Exports the data of the "obs_hippocampe" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_obs_hippocampe(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("obs_hippocampe");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Table obs_hippocampe export??e avec succ??s");

    }

    @FXML
    /**
     * Exports the data of the "obs_loutre" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_obs_loutre(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("obs_loutre");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Table obs_loutre export??e avec succ??s");
    }

    @FXML
    /**
     * Exports the data of the "vegetation" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_vegetation(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("vegetation");
        ExportData.writeCSV("lieu_vegetation");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Tables vegetation et lieu_vegetation export??es avec succ??s");
    }


    @FXML
    /**
     * Exports the data of the "vegetation" table to a csv file
     * @param event the event
     * @throws SQLException if there is a problem with the SQL
     * @throws ClassNotFoundException if the class is not found
     * @throws IOException if there is a problem with the IO
     */
    void exportation_hippocampe(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        Window owner = stat.getScene().getWindow();
        ExportData.writeCSV("obs_hippocampe");
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Exportation", "Table obs_hippocampe export??e avec succ??s");
    }



    @FXML
    /**
     * Exports the data of the "zone_humide" table to a csv file
     * @param event the event
     */
    void exportation_zone_humide(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
        ExportData.writeCSV("zonehumide");
    }

    
    @FXML
    /**
     * The content to do when the page linked to is started
     */
    private void initialize(){

        user.setText(ReadInfos.getStatus());

    }

    @FXML
    /**
     * Event to do when the button retour is pressed.    
     * Switch to the page Accueil_Utilisateur.fxml
    */
    public void toMainPage(){

        Stage actuel = (Stage)user.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        if(ReadInfos.estAdmin()){

            change.go_to("../../view/Accueil_Admin.fxml");
        }else{

            change.go_to("../../view/Accueil_Utilisateur.fxml");
        }
    }

    /**
     * When a button linked to "toObs" is pressed
     * Switch to the page Affichage.fxml to display the table selection menu
     */
    public void toObs(){

        Stage actuel = (Stage)user.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage.fxml");
    }

    /**
     * When a button linked to "toGraphics" is pressed
     * Switch to the page Choix_espece_stats.fxml to display the choice of the animal
     */
    public void toGraphics(){

        Stage actuel = (Stage)user.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/Choix_espece_stats.fxml");
    }


    /**
    * Event to do when the button retour is pressed.
    * Switch to the page Accueil_Utilisateur.fxml
    */
    public void retour(){

        Stage actuel = (Stage)this.user.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        if(ReadInfos.estAdmin()){

            change.go_to("../../view/Accueil_Admin.fxml");
        }else{

            change.go_to("../../view/Accueil_Utilisateur.fxml");
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
}
