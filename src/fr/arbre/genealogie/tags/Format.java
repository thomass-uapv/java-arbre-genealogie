package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

/**
 * Classe pour le TAG FORM du standard GEDOM. Hérite de TagTemplate.
 */
public class Format extends TagTemplate{

	/**
	 * Contient la valeur du format.
	 */
	private String f;

	/**
	 * Constructeur de Format.
	 */
	public Format() {
		super(2, "FORM");
		this.f = "UNKNOWN";
	}

	@Override
	public void parser(String texte, int cptLigne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));

		if (splited.size() > 2) {
			this.setF(splited.get(2)); 
		}

	}

	/**
	 * Renvoie la valeur du format.
	 * @return la valeur du format
	 */
	public String getF() {
		return f;
	}

	/**
	 * Défini la valeur du format.
	 * @param f
	 */
	public void setF(String f) {
		this.f = f;
	}

	@Override
	public String export() {
		return "  ".repeat(this.getNiveau()) + this.getNiveau() + this.getTag() + this.f;
	}

	@Override
	public String toString() {
		return this.f;
	}

}
