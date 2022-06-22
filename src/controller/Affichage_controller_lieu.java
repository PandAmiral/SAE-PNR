package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modele.donnee.Lieu;

import java.sql.DriverManager;

public class Affichage_controller_lieu {
    

    @FXML
    /**
     * The retour button in the fxml file
     */
    private Button retour;

    @FXML
    private ComboBox<Integer> limite;


    @FXML 
    private TableView<Lieu> table;

    @FXML 
    private TableColumn<Lieu,Double> coordx;

    @FXML 
    private TableColumn<Lieu,Double> coordy;
    
    public ObservableList<Lieu> data1 = FXCollections.observableArrayList();

    public void viewLieu(int limite){

        table.getItems().clear();
        try{
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "base_donnee", "sC32DnE3ae7Y");
            String sql = "SELECT * FROM Lieu LIMIT " + limite;
            PreparedStatement stat = c.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            while(rs.next()){
                //data.add(new Observation(rs.getInt(1),rs.getDate(2),rs.getTime(3),rs.getDouble(4)));

                if((rs.getDouble(1) != 0.0) && (rs.getDouble(2) != 0.0)){

                    data1.add(new Lieu(rs.getDouble(1),rs.getDouble(2)));

                }
            }
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        coordx.setCellValueFactory(new PropertyValueFactory<Lieu,Double>("coordX"));
        coordy.setCellValueFactory(new PropertyValueFactory<Lieu,Double>("coordY"));
        table.setItems(data1);
    }


    @FXML
    /**
     * Initialize elements when the fxml file is dilpayed
     */
    private void initialize()  {

        ObservableList<Integer> liste = FXCollections.observableArrayList(1, 25, 50, 100, 250, 500, 750, 1000, 1500, 2000);
        limite.setItems(liste);
        viewLieu(25);

    }


    /**
    * Event to do when the button retour is pressed.    
    * Switch to the page choix_stat_liste.fxml
    */
    public void retour(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../view/choix_stat_liste.fxml");
    }


    /**
    * Event to do when the button retour is pressed.    
    * Switch to the page Accueil_Utilisateur.fxml
    */
    public void home(){

        Stage actuel = (Stage)retour.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        if(ReadInfos.estAdmin() == true){

            change.go_to("../view/Accueil_Admin.fxml");
        }else{

            change.go_to("../view/Accueil_Utilisateur.fxml");
        }
    }


    @FXML
    private void changeLimit(){


        this.viewLieu(this.limite.getValue());
    }


    
}