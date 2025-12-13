package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

public class Title extends TagTemplate{
	private String t;

	public Title() {
		super(2, "TITL");
		this.setT("UNKNOWN");
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));
		
		if (splited.size() > 2) {
			this.t = texte.substring(texte.indexOf(this.getTag()) + this.getTag().length() + 1, texte.length());
		}
		
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

}
