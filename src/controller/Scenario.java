package controller;
import java.util.ArrayList;

import controller.utilitaires.ReadInfos;
import modele.donnee.*;

public class Scenario {

    
    public static void main(String[] args){

        //renvoie true avec un compte administrateur, sinon false
        System.out.println(ReadInfos.estAdmin());

        //Renvoie le nom et l'id du compte utilisé
        System.out.println(ReadInfos.getStatus());

        System.out.println(ReadInfos.getMax("Obs_Batracien"));

    }
    
}
