package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

public class Name extends TagTemplate{
	private String nom;
	private ArrayList<String> prenoms;

	public Name() {
		super(1, "NAME");
		this.nom = "UNKNOWN";
		this.prenoms = new ArrayList<String>();
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));
		
		if (splited.size() > 2) {
			for (int i = 2; i < splited.size(); i++) {
				if (splited.get(i).substring(0, 1).equals("/")) {
					this.nom = texte.substring(texte.indexOf("/")+1,texte.length()-1);
				} else {
					this.prenoms.add(splited.get(i));
				}
			}
		}
	}
	
	@Override
	public String export() {
		String res = "  ".repeat(this.getNiveau());
		if (this.prenoms.size() > 0) {			
			for (String p : this.prenoms) {
				res += p + " ";
			}
		}
		res += "/" + this.nom + "/" + "\n";
		return res;
	}

	@Override
	public String toString() {
		String res = "";
		if (this.prenoms.size() > 0) {			
			for (String p : this.prenoms) {
				res += p + " ";
			}
		} else {
			if (!this.nom.equals("UNKNOWN")) {				
				res += "UNKNOWN ";
			}
		}
		res += this.nom;
		return res;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<String> getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(ArrayList<String> prenoms) {
		this.prenoms = prenoms;
	}

}
