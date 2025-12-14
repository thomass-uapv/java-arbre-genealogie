package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

/**
 * Classe pour le TAG FILE du standard GEDOM. Hérite de TagTemplate.
 */
public class Fichier extends TagTemplate{

	/**
	 * Le lien du fichier
	 */
	private String url;

	/**
	 * Constructeur de la classe Fichier. Définit la valeur de l'url à UNKNOWN.
	 */
	public Fichier( ) {
		super(2, "FILE");
		this.url = "UNKNOWN";
	}

	@Override
	public void parser(String texte, int cptLigne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));

		if (splited.size() > 2) {
			this.url = texte.substring(texte.indexOf(this.getTag()) + this.getTag().length() + 1, texte.length());
		}
	}

	/**
	 * Renvoie le lien du fichier
	 * @return String qui renvoie le lien du fichier
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Définit le lien du fichier
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String export() {
		return "  ".repeat(this.getNiveau()) + this.getNiveau() + this.getTag() + this.url;
	}

	@Override
	public String toString() {
		return this.url;
	}

}

