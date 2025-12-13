package fr.arbre.genealogie.tags;

public class Birth extends Event{
	public Birth() {
		super(1, "BIRT");
	}
	
	@Override
	public String toString() {
		return "Né(e) le " + this.getDate().getEventValue() + " - Lieu de naissance : " + this.getLieu().getEventValue();
	}
}
