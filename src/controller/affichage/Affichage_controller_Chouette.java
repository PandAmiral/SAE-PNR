package controller.affichage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

import controller.utilitaires.ChangerPage;
import controller.utilitaires.ReadInfos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import modele.donnee.OChouette;

import java.sql.DriverManager;

public class Affichage_controller_Chouette {
    /**
     * The textField
     */
    @FXML private TextField delete;


    @FXML
    /**
     * Combo box to select the number of rows to display
     */
    private ComboBox<Integer> limite;

    @FXML
    /**
     * The retour button in the fxml file
     */
    private Button retour;

    /**
     * The table view in the fxml file
     */
    @FXML 
    private TableView<OChouette> table;

    @FXML
    /**
     * The table column in the fxml file for the id
     */
    private TableColumn<OChouette,Integer> numobs ;

    @FXML
    /**
     * The table column in the fxml file for id of the animal
     */
    private TableColumn<OChouette,String> lenumindividu ;

    @FXML
    /**
     * The table column in the fxml file for the name of type of observation
     */
    private TableColumn<OChouette,String> typeobs;

    @FXML
    /**
     * The table column in the fxml file for the genre
     */
    private TableColumn<OChouette,String> sexe;

    @FXML
    /**
     * The table column in the fxml file for the species
     */
    private TableColumn<OChouette,String> espece;

    @FXML
    /**
     * The table column in the fxml file for the date of the observation
     */
    private TableColumn<OChouette,Date> date;

    @FXML
    /**
     * The table column in the fxml file for the time of the observation
     */
    private TableColumn<OChouette,Time> heure;

    @FXML
    /**
     * The table column in the fxml file for the x coordinate
     */
    private TableColumn<OChouette,Double> x;

    @FXML
    /**
     * The table column in the fxml file for the type of protocole used
     */
    private TableColumn<OChouette,Integer> protocole ;

    @FXML
    /**
     * The table column in the fxml file for the y coordinate
     */
    private TableColumn<OChouette,Double> y;

    /**
     * Observable list for the owl observations
     */
    public ObservableList<OChouette> data = FXCollections.observableArrayList();
    

    @FXML
    /**
     * Fill the table with the data from the database
     */
    public void viewObservation(int limite){
        try{
            table.getItems().clear();
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "base_donnee", "sC32DnE3ae7Y");
            String sql = "SELECT numObs,protocole,typeObs,leNumIndividu,espece,sexe FROM Obs_Chouette,Chouette LIMIT "+limite;
            String sql2 = "SELECT dateObs,heureObs,lieu_lambert_X, lieu_Lambert_Y FROM Observation,Obs_Chouette WHERE idObs=numObs LIMIT "+limite;
            PreparedStatement stat = c.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            PreparedStatement stat2 = c.prepareStatement(sql2);
            ResultSet rs2 = stat2.executeQuery();
            while(rs.next() && rs2.next()){
                //int id, Date date, Time heure,  Double coordx ,Double coordy,int protocole, String typeObs,String lenumIndividu,String espece,String sexe
                    data.add(new OChouette(rs.getInt(1), rs2.getDate(1), rs2.getTime(2),rs2.getDouble(3), rs2.getDouble(4), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6)));
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        numobs.setCellValueFactory(new PropertyValueFactory<OChouette,Integer>("id"));
        date.setCellValueFactory(new PropertyValueFactory<OChouette,Date>("date"));
        heure.setCellValueFactory(new PropertyValueFactory<OChouette,Time>("heure"));
        x.setCellValueFactory(new PropertyValueFactory<OChouette,Double>("coordx"));
        y.setCellValueFactory(new PropertyValueFactory<OChouette,Double>("coordy"));
        lenumindividu.setCellValueFactory(new PropertyValueFactory<OChouette,String>("LenumIndividu"));
        typeobs.setCellValueFactory(new PropertyValueFactory<OChouette,String>("TypeObs"));
        sexe.setCellValueFactory(new PropertyValueFactory<OChouette,String>("sexe"));
        espece.setCellValueFactory(new PropertyValueFactory<OChouette,String>("espece"));
        protocole.setCellValueFactory(new PropertyValueFactory<OChouette,Integer>("Protocole"));
        table.setItems(data);
    }
    /**
     * If the correct key is typed
     * @param e event
     */
    public void keyDelete(KeyEvent e){

        if(e.getCode() == KeyCode.ENTER){
            delete_obs();
        }


    }
    /**
     * Take text in textfield and delete the row with the id typed
     */
    @FXML
    public void delete_obs(){
        if (delete.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur!",
                "Entr?? un nombre");
            
        }
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "base_donnee", "sC32DnE3ae7Y");
            PreparedStatement stat = c.prepareStatement("DELETE FROM Obs_Chouette WHERE numObs = ?");
            stat.setString(1,delete.getText());
            int row = stat.executeUpdate();
            //ResultSet rs = stat.executeQuery();
            c.close();
            viewObservation(25);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Method who create the message and show it in the screen
     * @param alertType Type of the Alert (CONFIRMATION OR ERROR)
     * @param owner Window of the Alert
     * @param title Title of the message screen
     * @param message Message who appear in screen
     */
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }


    @FXML
    /**
     * Initialize elements when the fxml file is displayed
     */
    private void initialize()  {

        ObservableList<Integer> liste = FXCollections.observableArrayList(1, 25, 50, 100, ReadInfos.getMax("Obs_Chouette"));
        limite.setItems(liste);

        viewObservation(25);
    }@FXML
     /**
     * Sets the value of the limite combobox
     */
    private void changeLimit(){


        this.viewObservation(this.limite.getValue());
    }

    /**
    * Event to do when the button retour is pressed.    
    * Switch to the page choix_stat_liste.fxml
    */
    public void retour(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/choix_stat_liste.fxml");
    }


    /**
    * Event to do when the button retour is pressed.    
    * Switch to the page Accueil_Utilisateur.fxml
    */
    public void home(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        if(ReadInfos.estAdmin() == true){

            change.go_to("../../view/Accueil_Admin.fxml");
        }else{

            change.go_to("../../view/Accueil_Utilisateur.fxml");
        }
    }

    /**
     * When a button linked to "affichage_observateur" is pressed
     * Switch to the page affichage_observateur.fxml
     */
    public void affichage_observateur(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_observateur.fxml");

    }

    public void affichage_nid_gci(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_nid_gci.fxml"); 
    }

    
    /**
     * When a button linked to "affichage_lieu" is pressed
     * Switch to the page affichage_lieu.fxml
     */
    public void affichage_lieu(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_Lieu.fxml");

    }

    /**
     * When a button linked to "affichage_batracien" is pressed
     * Switch to the page affichage_batracien.fxml
     */
    public void affichage_batracien(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_batracien.fxml");       
    }


    /**
     * When a button linked to "affichage_loutre" is pressed
     * Switch to the page affichage_loutre.fxml
     */
    public void affichage_loutre(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_loutre.fxml");       
    }

    /**
     * When a button linked to "affichage_gci" is pressed
     * Switch to the page affichage_gci.fxml
     */
    public void affichage_gci(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_obs_gci.fxml");       
    }

    /**
     * When a button linked to "affichage_hippocampe" is pressed
     * Switch to the page affichage_hippocampe.fxml
     */
    public void affichage_hippocampe(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_hippocampe.fxml");       
    }

    /**
     * When a button linked to "affichage_chouette" is pressed
     * Switch to the page affichage_chouette.fxml
     */
    public void affichage_chouette(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_chouette.fxml");       
    }

}
