package controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.sql.*;

import controller.utilitaires.ChangerPage;
import controller.utilitaires.ReadInfos;

/**
 * The controller for compte view
 */
public class Exemple_Compte_controller {

    @FXML
    /**
     * The account button in the fxml file
     */
    private Button user;

    @FXML
    /**
     * The button to delete the account the account
     */
    private Button delete;

    @FXML
    /**
     * The retour button in the fxml file
     */
    private Button back;

    @FXML
    /**
     * The modifier button in the fxml file
     */
    private Button modifier;

    @FXML
    /**
     * The button to display the history of the account
     */
    private Button history;

    @FXML
    private Label userName;

    @FXML
    private Label description;

    @FXML
    private Label droit;


    @FXML
    /**
     * Initialize elements when the fxml file is displayed
     */
    private void initialize(){
        userName.setText("Compte numéro : " + Integer.toString(Consulte_Compte_controller.getId()));
       
        description.setText("");
        int admin = -1;
        try{
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "base_donnee", "sC32DnE3ae7Y");
            String sql = "SELECT administration FROM registration WHERE id = " + Consulte_Compte_controller.getId();
            PreparedStatement stat = c.prepareStatement(sql);
            ResultSet rs = stat.executeQuery();
            rs.next();
            admin = rs.getInt("administration");
            c.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (admin == 1){
            droit.setText("Droits Administrateur");
        }
        else{
            droit.setText("Droits Utilisateur");
        }
    }

    @FXML
    /**
     * Event to do when the button delete is pressed.
     * Delete the account from the database
     */
    public void deleteUser(){
        try {

            //Création de la requête SQL
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "base_donnee", "sC32DnE3ae7Y");
            Statement s = c.createStatement();

            String query = "DELETE FROM registration WHERE id = " + Consulte_Compte_controller.getId() + ";";
            s.executeUpdate(query);

            s.close();
            c.close();
            System.out.println(Consulte_Compte_controller.getId() +","+ ReadInfos.getId());

            if(Consulte_Compte_controller.getId() == ReadInfos.getId()){
                Stage actuel = (Stage)back.getScene().getWindow();
                ChangerPage change = new ChangerPage(actuel);
                change.go_to("../../view/Page_Login.fxml");
            }
            else{
                Stage actuel = (Stage)back.getScene().getWindow();
                ChangerPage change = new ChangerPage(actuel);
                change.go_to("../../view/consulteCompte.fxml");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Event to do when the button retour is pressed.    
     * Switch to the page Accueil_Utilisateur.fxml
    */
    public void retour(){
        Stage actuel = (Stage)back.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/consulteCompte.fxml");

    }

    /**
     * Event to do when the button history is pressed.
     * Switch to the page Affichage_historique.fxml
     */
    public void historique(){
        Stage actuel = (Stage)back.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/affichage/Affichage_historique.fxml");
    }

    /**
     * Event to do when the button history is pressed.
     * Switch to the page Formulaire_modifier_compte.fxml
     */
    public void toModifier(){

        Stage actuel = (Stage)back.getScene().getWindow();
        ChangerPage change = new ChangerPage(actuel);
        change.go_to("../../view/formulaires/Formulaire_modifier_compte.fxml");        
    }
}
