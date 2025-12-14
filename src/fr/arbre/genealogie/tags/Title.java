package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

/**
 * Classe pour le TAG TITL du standard GEDOM. Hérite de TagTemplate.
 */
public class Title extends TagTemplate{

	/**
	 * Contient la valeur du titre
	 */
	private String t;

	/**
	 * Constructeur du titre
	 */
	public Title() {
		super(2, "TITL");
		this.setT("UNKNOWN");
	}

	@Override
	public void parser(String texte, int cptLigne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));

		if (splited.size() > 2) {
			this.t = texte.substring(texte.indexOf(this.getTag()) + this.getTag().length() + 1, texte.length());
		}

	}

	/**
	 * Retourne la valeur du titre
	 * @return la valeur du titre
	 */
	public String getT() {
		return t;
	}

	/**
	 * Défini la valeur du titre
	 * @param t
	 */
	public void setT(String t) {
		this.t = t;
	}

	@Override
	public String export() {
		return "  ".repeat(this.getNiveau()) + this.getNiveau() + this.getTag() + this.t;
	}

	@Override
	public String toString() {
		return this.t;
	}

}
