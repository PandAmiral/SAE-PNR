package donnee;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ObsHippocampe extends Observation{

	private Peche typePeche;
	private EspeceHippocampe espece;
	private Sexe sexe;
	private double taille;
	private boolean estGestant;
	private int attribute;

	/**
	 * 
	 * @param id
	 * @param date
	 * @param heure
	 * @param lieu
	 * @param observateur
	 * @param laTaille
	 * @param leTypePeche
	 * @param lEspece
	 * @param leSexe
	 */
	public ObsHippocampe(int id, Date date, Time heure, Lieu lieu, ArrayList<Observateur> observateurs, double laTaille, Peche leTypePeche, EspeceHippocampe lEspece, Sexe leSexe) {
		super(id, date, heure, lieu, observateurs);

		if (laTaille > 0 && leTypePeche != null && lEspece != null && leSexe != null){
			this.taille = laTaille;
			this.typePeche = leTypePeche;
			this.espece = lEspece;
			this.sexe = leSexe;
		}
		else{
			System.err.println("ObsHippocampe : paramètre erroné.");
		}		
        throw new UnsupportedOperationException();
	

	}
	/**
	 * Methode pour avoir l'espece de l'hippocampes
	 * @return l'espece de l'hippocampes
	 */
	public EspeceHippocampe getEspece() {
		return espece;
	}
	/**
	 * Methode pour avoir le sexe de l'hihppocampes
	 * @return le sexe de l'hippocampes
	 */
	public Sexe getSexe() {
		return sexe;
	}
	/**
	 * Methode pour avoir la taille de l'ihppocampes
	 * @return la taille de l'hippocampes
	 */
	public double getTaille() {
		return taille;
	}
	/**
	 * Methode pour avoir le type de peche de l'hippocampes
	 * @return le type de peche
	 */
	public Peche getTypePeche() {
		return typePeche;
	}
	/**
	 * Methode pour savoir si l'hippocampes est gestant
	 * @return true si gestant, false sinon
	 */
	public boolean getEstGestant(){
		return estGestant;
	}
	/**
	 * Methode pour changer l'espece de l'hippocampe
	 * @param espece la nouvelle espece
	 */
	public void setEspece(EspeceHippocampe espece) {
		if(espece != null){
			this.espece = espece;
		}
		else{
			System.err.println("setEspece : paramètre erroné.");
		}
	}
	/**
	 * Methode pour changer le sexe de l'hippocampes
	 * @param sexe le nouveau sexe
	 */
	public void setSexe(Sexe sexe) {
		if(sexe != null){
			this.sexe = sexe;
		}
		else{
			System.err.println("setSexe : paramètre erroné.");
		}
	}
	/**
	 * Methode pour changer la taille de l'hippocampes
	 * @param taille la nouvelle taille
	 */
	public void setTaille(double taille) {
		if(taille > 0){
			this.taille = taille;
		}
		else{
			System.err.println("setTaille : paramètre erroné.");
		}
	}
	/**
	 * Methode pour changer le type de peche
	 * @param typePeche le nouveau type de peche
	 */
	public void setTypePeche(Peche typePeche) {
		if(typePeche != null){
			this.typePeche = typePeche;
		}
		else{
			System.err.println("setTypePeche : paramètre erroné");
		}
	}
	/**
	 * Methode pour changer l'etat de gestation de l'hippocampes
	 * @param gestant le nouvelle etat
	 */
	public void setEstGestant(boolean gestant){
		if(sexe == Sexe.MALE){
			if(gestant){
				this.estGestant = true;
			}
			else{
				this.estGestant = false;
			}
		}
		else if(sexe==Sexe.FEMELLE){
			if(gestant){
				System.out.println("Erreur : un hippocampe femelle ne peut être gestant");
			}
			else{
				this.estGestant = false;

			}
		}
		else{
			if(gestant){
				this.estGestant = true;
				this.sexe = Sexe.MALE;
            }

		}
	}
	
	@Override
	public EspeceObservee especeObs() {

		return EspeceObservee.HIPPOCAMPE;
	}
}


