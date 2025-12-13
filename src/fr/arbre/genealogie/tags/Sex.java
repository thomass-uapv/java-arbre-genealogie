package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

public class Sex extends TagTemplate{
	private String value;

	public Sex() {
		super(1, "SEX");
		this.value = "UNKNOWN";
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));
		if (splited.size() > 2) {
			if (splited.get(2).equals("M") || splited.get(2).equals("F")) {
				this.value = splited.get(2);
			}
		}
	}
	
	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public String export() {
		return "  ".repeat(this.getNiveau()) + this.getNiveau() + this.getTag() + this.value;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String sexe) {
		this.value = sexe;
	}

		

}
