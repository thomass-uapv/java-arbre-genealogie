package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

public class Fichier extends TagTemplate{

	private String url;

	public Fichier( ) {
		super(2, "FILE");
		this.url = "UNKNOWN";
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));

		if (splited.size() > 2) {
			this.url = texte.substring(texte.indexOf(this.getTag()) + this.getTag().length() + 1, texte.length());
		}
	}

	public String getUrl() {
		return url;
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
