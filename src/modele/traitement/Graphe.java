package modele.traitement;
import java.util.HashMap;
import java.util.ArrayList;

public class Graphe {

    private HashMap<Sommet, ArrayList<Sommet>> sommetsVoisins;
    

    /**
     * Get an HashMap<Sommmet, int> wich keys are the vertices
     * of the graph. The values are the degres of each vertex.
     * @return the HashMap<Sommmet, int>
     */
    public HashMap<Sommet, Integer> calculDegres(){

        HashMap<Sommet, Integer> ret = null;

        if(this.sommetsVoisins.size() > 0){

            for(Sommet i : this.sommetsVoisins.keySet()){

                ret.put(i, this.sommetsVoisins.get(i).size());
            }

        }else{

            System.err.println("calculDegre : there must be at least one value in sommetsVoisins.");
        }

        return ret;
    }


    /**
     * Get the vertex with the maximum degre in the graph
     * @return the vertex with the maximum degre in the graph
     */
    public Sommet somMaxDegre(){

        Sommet ret = null;

        HashMap<Sommet, Integer> degres = this.calculDegres();

        if(degres != null){

            for(Sommet i : degres.keySet()){

                if(ret == null){

                    ret = i;
                }else{

                    if(degres.get(ret) < degres.get(i)){

                        ret = i;
                    }
                }
            }
        }else{

            System.err.println("somMaxDegre : there must be at least one vertex in the graph");
        }

        return ret;
    }


    /**
     * Check if two vertex are neighbours
     * @param idSom1 the first vertex
     * @param idSom2 the second vertex
     * @return true if the vertex are neighbours, false if not
     */
    public boolean sontVoisins(int idSom1, int idSom2){

        boolean ret = false;

        Sommet sommet1 = this.getSommet(idSom1);
        Sommet sommet2 = this.getSommet(idSom2);

        if((sommet1 != null) && (sommet2 != null)){

            ret = this.sommetsVoisins.get(sommet1).contains(sommet2);            
        }else{

            System.err.println("sontVoisins : the two vertex must be in  the graph");
        }

        return ret;       
    }


    /**
     * Verify if a way to go from a wanted vertex to another one exists.
     * @param idSom1 the first vertex
     * @param idSom2 the second vertex
     * @return true if a way exists, false is there isn't one
     */
    public boolean existeChemin(int idSom1, int idSom2){

        boolean ret = false;
        ArrayList<Sommet> parcouru = new ArrayList<Sommet>();
        Sommet sommet1 = this.getSommet(idSom1);
        Sommet sommet2 = this.getSommet(idSom2);

        if((sommet1 != null) && (sommet2 != null)){

            ret = DFSrec(sommet1, sommet2, parcouru, this.sommetsVoisins.get(sommet1));            
        }else{

            System.err.println("sontVoisins : the two vertex must be in  the graph");
        }

        return ret;
    }

    
    /**
     * Do a DFS of the graph
     * @param som1 the starting vertex
     * @param som2 the vertex we want to find
     * @param parcouru the vertex already travelled
     * @param stack the vertex to search-in
     * @return true if there is a way, false if not
     */
    public boolean DFSrec(Sommet som1, Sommet som2, ArrayList<Sommet> parcouru, ArrayList<Sommet> stack){

        boolean ret = false;

        if(som1 == som2){

            ret = true;

        }else if(stack.size() == 0){


        }else{

            Sommet departSuivant = stack.get(0);
            parcouru.add(departSuivant);
            stack.remove(departSuivant);

            for(Sommet i : sommetsVoisins.get(departSuivant)){

                if(!parcouru.contains(i)){

                    stack.add(i);
                    ret = DFSrec(departSuivant, som2, parcouru, stack);
                }
            }
        }
        return ret;

    }


    /**
     * Get the neighbours of a vertex
     * @param idSom1 the vertex's id
     * @return the neighbours the vertex. Return null if the vertex does not exists or has no neighbours.
     */
    public ArrayList<Sommet> voisins(int idSom1){

        ArrayList<Sommet> ret = null;

        if(estDansGraphe(idSom1)){

            ret = sommetsVoisins.get(this.getSommet(idSom1));
        }

        return ret;
    }


    /**
     * Add an edge to the graph between two vertex
     * @param idSom1 the first vertex's id
     * @param idSom2 the second vertex's id
     */
    public boolean ajouteArrete(int idSom1, int idSom2){

        boolean ret = false;

        if((this.estDansGraphe(idSom1)) && (this.estDansGraphe(idSom2))){

            ret = true;
            Sommet sommet1 = this.getSommet(idSom1);
            Sommet sommet2 = this.getSommet(idSom2);

            this.sommetsVoisins.get(sommet1).add(sommet2);
            this.sommetsVoisins.get(sommet2).add(sommet1);

            
        }else{

            System.err.println("ajouteArrete : the two vertex");
        }
    }


    /**
     * Remove an edge to the graph between two vertex
     * @param idSom1 the first vertex's id
     * @param idSom2 the second vertex's id
     */
    public void retireArete(int idSom1, int idSom2){

        if((idSom1 >= 0) && (idSom2 >= 0)){

            if(idSom1 != idSom2){

                Sommet sommet1 = null;
                Sommet sommet2 = null;

                for(Sommet i : this.sommetsVoisins.keySet()){

                    if(i.getId() == idSom1){

                        sommet1 = i;
                    }

                    if(i.getId() == idSom2){

                        sommet2 = i;
                    }
                        
                }

                if((sommet1 != null) && (sommet2 != null)){

                    this.sommetsVoisins.get(sommet1).remove(sommet2);
                    this.sommetsVoisins.get(sommet2).remove(sommet1);

                }else{

                    System.err.println("ajouteArrete : the two wanted vertex must exists.");
                }
            }


        }else{

            System.err.println("ajouteArrete : idSom1 and idSom2 must be at leats equal to 0.");
        }

    }

    /**
     * Get the vertex with the corresponding id, if it exists in the graph
     * @param idSom the vertex's id
     * @return the vertex, null if there isn't one with this id
     */
    public Sommet getSommet(int idSom){

        Sommet ret = null;

        if(sommetsVoisins.size() > 0){

            if(idSom >= 0){

                for(Sommet i : sommetsVoisins.keySet()){

                    if(i.getId() == idSom){

                        ret = i;
                    }
                }
            }else{

                System.err.println("getSommet : the vertex's id must be at least equal to 0.");
            }
        }else{

            System.err.println("getSommet : the graph must contain at least one vertex");
        }

        return ret;
    }

}
