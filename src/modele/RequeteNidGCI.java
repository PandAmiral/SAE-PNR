package modele;
import java.util.*;
import java.sql.*;

public class RequeteNidGCI {

    public static void main(String[] args) {
        try {
            //Les ArrayList = au colonne de la table
            ArrayList<String> nidGCI = new ArrayList<String>();
            ArrayList<String> nomPlage = new ArrayList<String>();
            ArrayList<String> raisonArretObservation = new ArrayList<String>();
            ArrayList<String> nbEnvol = new ArrayList<String>();
            ArrayList<String> protection = new ArrayList<String>();
            ArrayList<String> bagueMale = new ArrayList<String>();
            ArrayList<String> bagueFemelle = new ArrayList<String>();

            //Création de la requête SQL
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/pnr", "base_donnee", "sC32DnE3ae7Y");
            Statement s = c.createStatement();
            String query = "SELECT * FROM nid_gci";
            ResultSet r = s.executeQuery(query);

            //Remplissage des ArrayList
            while (r.next()) {
                nidGCI.add(r.getString("idNid"));
                nomPlage.add(r.getString("nomPlage"));
                raisonArretObservation.add(r.getString("raisonArretObservation"));
                nbEnvol.add(r.getString("nbEnvol"));
                protection.add(r.getString("protection"));
                bagueMale.add(r.getString("bagueMale"));
                bagueFemelle.add(r.getString("bagueFemelle"));
                
            }
            //Suppression des virgules
            nidGCI.toString().replaceAll(",", " ");
            nomPlage.toString().replaceAll(",", " ");
            raisonArretObservation.toString().replaceAll(",", " ");
            nbEnvol.toString().replaceAll(",", " ");
            protection.toString().replaceAll(",", " ");
            bagueMale.toString().replaceAll(",", " ");
            bagueFemelle.toString().replaceAll(",", " ");

            //Affichage de la ligne 26
            System.out.println(nidGCI.get(26));
            System.out.println(nomPlage.get(26));
            System.out.println(raisonArretObservation.get(26));
            System.out.println(nbEnvol.get(26));
            System.out.println(protection.get(26));
            System.out.println(bagueMale.get(26));
            System.out.println(bagueFemelle.get(26));
            r.close();
            s.close();
            c.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}