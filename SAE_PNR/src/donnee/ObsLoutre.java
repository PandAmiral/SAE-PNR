package donnee;
import java.sql.Date;
import java.sql.Time;
import java.util.*;
/**Classe pour cree les observations des loutres */
public class ObsLoutre extends Observation {
	/**Parametre pour les indices des loutres */
	private IndiceLoutre indice;

	/**
	 * Constructeur pour crée les observations des batraciens
	 * @param id l'id de observation
	 * @param date date de l'observation
	 * @param heure heure de l'observation
	 * @param lieu lieu de l'observation
	 * @param observateurs nom des observateurs
	 */
	public ObsLoutre(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, IndiceLoutre indice) {
		super(id, date, heure, lieu, observateurs);
		if(indice != null){
			this.indice = indice;
		}
		else{
			System.err.println("ObsLoutre : paramètre érroné");
		}
		throw new UnsupportedOperationException();
	}
	/**
	 * Methode pour avoir l'indice
	 * @return l'inice
	 */
	public IndiceLoutre getIndice() {
		return indice;
	}

}