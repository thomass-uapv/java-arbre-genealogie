package fr.arbre.genealogie.tags;

import fr.arbre.genealogie.utils.Event;

/**
 * Classe pour le TAG BIRT du standard GEDOM. Hérite de Event.
 */
public class Birth extends Event{

	/**
	 * Constructeur de la classe Birth.
	 */
	public Birth() {
		super(1, "BIRT");
	}

	@Override
	public String toString() {
		return "Né(e) le " + this.getDate() + " - Lieu de naissance : " + this.getLieu();
	}
}
