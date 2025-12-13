package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

public class Sex extends TagTemplate{
	private String sexe;

	public Sex() {
		super(1, "SEX");
		this.sexe = "UNKNOWN";
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));
		if (splited.size() > 2) {
			if (splited.get(2).equals("M") || splited.get(2).equals("F")) {
				this.sexe = splited.get(2);
			} else {
				//TODO Renvoyer une erreur ?
			}
		}
	}
	
	@Override
	public String toString() {
		return this.sexe;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
		

}
