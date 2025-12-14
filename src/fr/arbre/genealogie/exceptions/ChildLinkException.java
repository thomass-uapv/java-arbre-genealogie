package fr.arbre.genealogie.exceptions;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Individu;

/**
 * Classe correspondant à l'Exception ChildLinkException héritant de MissingReciprocalLinkException.<br>
 * Survient lorsqu'il n'y a pas un lien réciproque de Famille à Individu (enfant).<br>
 * Cette exception stocke la liste des enfants d'une famille fixée et l'individu lors du lancement de celle-ci.
 */
public class ChildLinkException extends MissingReciprocalLinkException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6443488852772448526L;

	/**
	 * La liste des enfants d'une famille fixée au moment du lancement de l'exception.
	 */
	private ArrayList<Individu> enfants;

	/**
	 * L'individu au moment du lancement de l'exception.
	 */
	private Individu indi;

	/**
	 * Constructeur de la classe ChildLinkException.
	 * @param msg
	 * @param enfants
	 * @param indi
	 */
	public ChildLinkException(String msg, ArrayList<Individu> enfants, Individu indi) {
		super("ChildLinkException - " + msg);
		this.enfants = enfants;
		this.indi = indi;
	}

	/** Retourne la liste des enfants d'une famille fixée au moment du lancement de l'exception.
	 * @return la liste des enfants d'une famille fixée au moment du lancement de l'exception.
	 */
	public ArrayList<Individu> getEnfants() {
		return enfants;
	}

	/** 
	 * Retourne l'individu au moment du lancement de l'exception.
	 * @return l'individu au moment du lancement de l'exception.
	 */
	public Individu getIndi() {
		return indi;
	}

}
