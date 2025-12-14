package fr.arbre.genealogie.exceptions;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;

/**
 * Classe correspondant à l'Exception FamillyChildLinkException héritant de MissingReciprocalLinkException.<br>
 * Survient lorsqu'il n'y a pas de lien réciproque de Individu (Enfant) à Famille.<br>
 * Cette exception stocke la famille et l'individu lors du lancement de celle-ci.
 */
public class FamillyChildLinkException extends MissingReciprocalLinkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522907859906097856L;

	/**
	 * La famille au moment du lancement de l'exception.
	 */
	private Famille fam;

	/**
	 * L'individu au moment du lancement de l'exception.
	 */
	private Individu indi;

	/**
	 * Constructeur de la classe FamillyChildLinkException
	 * @param msg
	 * @param fam
	 * @param indi
	 */
	public FamillyChildLinkException(String msg, Famille fam, Individu indi) {
		super("FamillyChildLinkException - " + msg);
		this.fam = fam;
		this.indi = indi;		
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
