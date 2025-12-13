package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

public class Format extends TagTemplate{
	private String f;

	public Format() {
		super(2, "FORM");
		this.setF("UNKNOWN");
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));
		
		if (splited.size() > 2) {
			this.setF(splited.get(2)); 
		}
		
	}

	public String getF() {
		return f;
	}

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
