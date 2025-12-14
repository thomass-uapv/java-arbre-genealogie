package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

/**
 * Classe pour le TAG NAME du standard GEDOM. Hérite de TagTemplate.
 */
public class Name extends TagTemplate{

	/**
	 * Contient la valeur du nom
	 */
	private String nom;

	/**
	 * Contient la liste des prénoms
	 */
	private ArrayList<String> prenoms;

	/**
	 * Constructeur de la classe Name. Défini le nom à "UNKNOWN" et instancie la liste des prénoms.
	 */
	public Name() {
		super(1, "NAME");
		this.nom = "UNKNOWN";
		this.prenoms = new ArrayList<String>();
	}

	@Override
	public void parser(String texte, int cptLigne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));

		if (splited.size() > 2) {
			for (int i = 2; i < splited.size(); i++) {
				if (splited.get(i).substring(0, 1).equals("/")) {
					this.nom = texte.substring(texte.indexOf("/")+1,texte.length()-1);
					break;
				} else {
					this.prenoms.add(splited.get(i));
				}
			}
		}
	}

	@Override
	public String export() {
		String res = "  ".repeat(this.getNiveau()) + " " + this.getNiveau() + " " + this.getTag() + " ";
		if (this.prenoms.size() > 0) {			
			for (String p : this.prenoms) {
				res += p + " ";
			}
		}
		res += "/" + this.nom + "/";
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

	/**
	 * Renvoie la valeur du nom
	 * @return String de la valeur du nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Défini la valeur du nom
	 * @param nom : le nom à définir
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie la liste des prénoms
	 * @return collection de string de la liste des prénoms
	 */
	public ArrayList<String> getPrenoms() {
		return prenoms;
	}

	/**
	 * Ajoute le prénom à la liste des prénoms
	 * @param prenom : ajoute le prénom à la collection
	 */
	public void addPrenoms(String prenom) {
		this.prenoms.add(prenom);
	}

}
