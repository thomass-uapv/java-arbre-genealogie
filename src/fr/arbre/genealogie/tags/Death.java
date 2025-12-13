package fr.arbre.genealogie.tags;

import fr.arbre.genealogie.utils.Event;

public class Death extends Event{

	public Death() {
		super(1, "DEAT");
	}

	@Override
	public String toString() {
		return "Décédé(e) le " + this.getDate() + " - Lieu de décès : " + this.getLieu();
	}

}
