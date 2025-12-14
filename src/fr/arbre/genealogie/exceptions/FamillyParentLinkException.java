package fr.arbre.genealogie.exceptions;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;

/**
 * Classe correspondant à l'Exception FamillyParentLinkException héritant de MissingReciprocalLinkException.<br>
 * Survient lorsqu'il n'y a pas un lien réciproque de Famille à Individu (père/mère).<br>
 * Cette exception stocke la liste des familles où un individu fixé est parent et la famille lors du lancement de celle-ci.
 */
public class FamillyParentLinkException extends MissingReciprocalLinkException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5664020021817930294L;

	/**
	 * La liste des familles où un individu fixé est parent au moment du lancement de l'exception.
	 */
	private ArrayList<Famille> listeFamilleParent;

	/**
	 * La famille au moment du lancement de l'exception.
	 */
	private Famille fam;

	/**
	 * Constructeur de la classe FamillyParentLinkException.
	 * @param msg
	 * @param p
	 * @param fam
	 */
	public FamillyParentLinkException(String msg, ArrayList<Famille> p, Famille fam) {
		super("FamillyParentLinkException - " + msg);
		this.listeFamilleParent = p;
		this.fam = fam;
	}

	/**
	 * Renvoie la liste des familles où un individu fixé est parent au moment du lancement de l'exception.
	 * @return la liste des familles où un individu fixé est parent au moment du lancement de l'exception.
	 */
	public ArrayList<Famille> getListeFamilleParent() {
		return listeFamilleParent;
	}

	/**
	 * Renvoie la famille stocké au moment du lancement de l'exception.
	 * @return la famille stocké au moment du lancement de l'exception.
	 */
	public Famille getFam() {
		return fam;
	}

}
