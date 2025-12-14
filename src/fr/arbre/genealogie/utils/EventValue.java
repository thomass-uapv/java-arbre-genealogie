package fr.arbre.genealogie.utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe abstraite correspond à des TAGs particuliers du standard GEDOM.<br>
 * Cette classe est spécifique aux attributs d'un objet Event (voir classe Event)
 */
public abstract class EventValue extends TagTemplate {

	/**
	 * Valeur du Tag.
	 */
	private String eventValue;

	/**
	 * Constructeur de la classe EventValue. Les attributs sont définis à null ou "UNKNOWN".
	 * @param niveau
	 * @param tag
	 */
	public EventValue(int niveau, String tag) {
		super(niveau, tag);
		this.eventValue = "UNKNOWN";
	}

	@Override
	public void parser(String texte, int cptLigne) {
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

	/**
	 * Renvoie la valeur du TAG.
	 * @return la valeur du TAG.
	 */
	public String getEventValue() {
		return eventValue;
	}

	/**
	 * Définit la valeur du TAG.
	 * @param value
	 */
	public void setEventValue(String value) {
		this.eventValue = value;
	}

}

