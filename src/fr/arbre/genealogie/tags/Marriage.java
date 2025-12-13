package fr.arbre.genealogie.tags;

public class Marriage extends Event{
		public Marriage() {
		super(1, "MARR");
	}

	@Override
	public String toString() {
		return "Marié(e) depuis le " + this.getDate().getEventValue() + " - Lieu : " + this.getLieu( ).getEventValue();
	}

}
