package traitement;
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

        if(this.sommetsVoisins.size() > 0){

            for(Sommet i : this.sommetsVoisins.keySet()){

                if(i.getId() == idSom1){

                    for(Sommet j : this.sommetsVoisins.get(i)){

                        if(j.getId() == idSom2){

                            ret = true;
                        }
                    }
                }
            }
        }

        return ret;       
    }


}
