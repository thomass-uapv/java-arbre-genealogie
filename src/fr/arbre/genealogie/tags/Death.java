package fr.arbre.genealogie.tags;

public class Death extends Event{

	public Death() {
		super(1, "DEAT");
	}

	@Override
	public String toString() {
		return "Décédé(e) le " + this.getDate().getEventValue() + " - Lieu de décès : " + this.getLieu().getEventValue();
	}

}
