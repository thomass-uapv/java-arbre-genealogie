package fr.arbre.genealogie.utils;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class EventValue extends TagTemplate {

	private String eventValue;

	public EventValue(int niveau, String tag) {
		super(niveau, tag);
		this.eventValue = "UNKNOWN";
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));

		if (splited.size() > 2) {
			this.setEventValue(texte.substring(texte.indexOf(this.getTag()) + this.getTag().length() + 1,texte.length()));
		}
	}

	@Override
	public String toString() {
		return this.eventValue;
	}

	@Override
	public String export() {
		return "  ".repeat(this.getNiveau()) + " " + this.getNiveau() + " " + this.getTag() + " " + this.eventValue;
	}

	public String getEventValue() {
		return eventValue;
	}

	public void setEventValue(String value) {
		this.eventValue = value;
	}

}
