package fr.arbre.genealogie.exceptions;


import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;

/**
 * Classe correspondant à l'Exception ParentLinkException héritant de MissingReciprocalLinkException.<br>
 * Survient lorsqu'il n'y a pas de lien réciproque de Famille à Individu (père/mère).<br>
 * Cette exception stocke la famille, l'individu et le sexe de l'individu lors du lancement de celle-ci.
 */
public class ParentLinkException extends MissingReciprocalLinkException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6443488852772448526L;

	/**
	 * La famille au moment du lancement de l'exception.
	 */
	private Famille fam;

	/**
	 * L'individu au moment du lancement de l'exception.
	 */
	private Individu indi;

	/**
	 * Le sexe de l'individu (M ou F)
	 */
	private String sexe;

	/**
	 * Constructeur de la classe ParentLinkException.
	 * @param msg
	 * @param fam
	 * @param indi
	 * @param sexe
	 */
	public ParentLinkException(String msg, Famille fam, Individu indi, String sexe) {
		super("ParentLinkException - " + msg);
		this.fam = fam;
		this.indi = indi;
		this.sexe = sexe;
	}

	/** Retourne le sexe de l'individu (M ou F)
	 * @return le sexe de l'individu (M ou F)
	 */
	public String getSexe() {
		return sexe;
	}

	/**
	 * Renvoie la famille stocké au moment du lancement de l'exception.
	 * @return la famille stocké au moment du lancement de l'exception.
	 */
	public Famille getFam() {
		return fam;
	}

	/**
	 * Renvoie l'individu stocké au moment du lancement de l'exception.
	 * @return l'individu stocké au moment du lancement de l'exception.
	 */
	public Individu getIndi() {
		return indi;
	}

}
