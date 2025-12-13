package fr.arbre.genealogie.tags;

import fr.arbre.genealogie.utils.Event;

public class Marriage extends Event{
		public Marriage() {
		super(1, "MARR");
	}

	@Override
	public String toString() {
		return "Marié(e) depuis le " + this.getDate() + " - Lieu : " + this.getLieu( );
	}

}
