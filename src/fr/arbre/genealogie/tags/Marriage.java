package fr.arbre.genealogie.tags;

import fr.arbre.genealogie.utils.Event;

/**
 * Classe pour le TAG MARR du standard GEDOM. Hérite de Event.
 */
public class Marriage extends Event{

	/**
	 * Constructeur de la classe Marriage.
	 */
	public Marriage() {
		super(1, "MARR");
	}

	@Override
	public String toString() {
		return "Marié(e) depuis le " + this.getDate() + " - Lieu : " + this.getLieu( );
	}

}
