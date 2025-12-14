package fr.arbre.genealogie.tags;

import fr.arbre.genealogie.utils.Event;

/**
 * Classe pour le TAG DEAT du standard GEDOM. Hérite de Event.
 */
public class Death extends Event{

	/**
	 * Constructeur de la classe Death.
	 */
	public Death() {
		super(1, "DEAT");
	}

	@Override
	public String toString() {
		return "Décédé(e) le " + this.getDate() + " - Lieu de décès : " + this.getLieu();
	}

}
