package fr.arbre.genealogie.tags;

import fr.arbre.genealogie.utils.Event;

public class Birth extends Event{
	public Birth() {
		super(1, "BIRT");
	}
	
	@Override
	public String toString() {
		return "Né(e) le " + this.getDate() + " - Lieu de naissance : " + this.getLieu();
	}
}
