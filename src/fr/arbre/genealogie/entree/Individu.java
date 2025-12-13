package fr.arbre.genealogie.entree;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.tags.Birth;
import fr.arbre.genealogie.tags.Death;
import fr.arbre.genealogie.tags.Name;
import fr.arbre.genealogie.tags.Objet;
import fr.arbre.genealogie.tags.Sex;
import fr.arbre.genealogie.utils.Entree;
import fr.arbre.genealogie.utils.TagTemplate;

public class Individu extends Entree{

	private Name nom;
	private Sex sexe;
	private Birth naissance;
	private Death deces;
	private Famille famille;
	private ArrayList<Famille> liste_famille_p;
	private ArrayList<Objet> liste_objet;

	public Individu(int id) {
		super(0, "INDI", id);
		this.nom = new Name();
		this.sexe = new Sex();
		this.naissance = new Birth();
		this.deces = new Death();
		this.famille = null;
		this.liste_famille_p = new ArrayList<Famille>();
		this.liste_objet = new ArrayList<Objet>();
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
		TagTemplate current = null;
		ArrayList<String> lines = new ArrayList<String>(Arrays.asList(texte.split("\n")));
		int i = 0;
		while (i < lines.size()) {
			String[] splited = lines.get(i).split(" ");
			if (splited[0].equals(Integer.toString(this.getNiveau() + 1))) {
				current = null;
				if (splited[1].equals(this.nom.getTag())) {
					current = this.nom;
				} else if(splited[1].equals(this.sexe.getTag())) {
					current = this.sexe;
				} else if (splited[1].equals(this.famille.getTag()) || splited[1].equals("FAMS")){
					int id = Integer.parseInt(splited[2].substring(2,splited[2].length()-1));
					current = Shell.getBddFam(id);
					if (current == null) {
						MissingEntreeException e = new MissingEntreeException("Famille manquante, création de la famille...", cpt_ligne);
						e.getMessage(); //TODO A verifier
						current = new Famille(id);
						Shell.addBddFam((Famille) current);
					}
					if (splited[1].equals(this.famille.getTag())) {
						if (this.famille == null) {
							this.famille = (Famille) current;
						}
					} else if (splited[1].equals("FAMS")) {
						this.liste_famille_p.add((Famille) current);
					}
				} else if (splited[1].equals(this.naissance.getTag())) {
					current = this.naissance;
				} else if (splited[1].equals(this.deces.getTag())) {
					current = this.deces;
				} else if (splited[1].equals("OBJE")) {
					current = new Objet();
					this.liste_objet.add((Objet) current);
				}
				if (!(splited[1].equals(this.famille.getTag()) || splited[1].equals("FAMS"))) {
					current.setLigne(cpt_ligne);
				}
				current.parser(lines.get(i).trim(), cpt_ligne+i);
				i++;
			} else {
				if (current != null) {					
					String bloc = lines.get(i++).trim() + "\n";
					int cpt_ligne_debut_bloc = cpt_ligne+i-1;
					while (i < lines.size() && !lines.get(i).trim().split(" ")[0].equals(Integer.toString(this.getNiveau() + 1))) {
						bloc += lines.get(i++).trim() + "\n";
					}
					current.parser(bloc, cpt_ligne_debut_bloc);
				} else {
					i++;
				}
			}
		}

	}

	public boolean equals(Individu obj) {
		return (this.getIdentificateur() == obj.getIdentificateur());
	}

	public ArrayList<Individu> marriedWith() {
		ArrayList<Individu> marriedWith = new ArrayList<Individu>();
		for (Famille fam : this.liste_famille_p) {
			ArrayList<Individu> marriedFamille = fam.whoMarried();
			if (marriedFamille.get(0).equals(this)) {
				marriedWith.add(marriedFamille.get(1));
			} else {
				marriedWith.add(marriedFamille.get(0));
			}
		}
		return marriedWith;

	}

	@Override
	public String toString() {
		String res;
		if (famille == null) {
			res = "Prénoms & Nom : " + this.nom.toString() + "\n" + 
					"Sexe : " + sexe.toString() + "\n" +
					"Naissance : \n  " + this.naissance.toString() + "\n" +
					"Décès : \n  " + this.deces.toString() + "\n" +
					"Famille : UNKNOWN \n" + 
					"Familles où l'individu est parent :\n";
		} else {
			res = "Prénoms & Nom : " + nom.toString() + "\n" + 
					"Sexe : " + sexe.toString() + "\n" +
					"Naissance : \n  " + this.naissance.toString() + "\n" +
					"Décès : \n  " + this.deces.toString() + "\n" +
					"Famille : " + Integer.toString(famille.getIdentificateur()) + "\n" + 
					"Familles où l'individu est parent :\n";
		}
		if (liste_famille_p.size() == 0) {
			res += "   AUCUNE \n";
		} else {			
			for(Famille fam : liste_famille_p) {
				res += " - @F" + Integer.toString(fam.getIdentificateur()) + "@\n";
			}
		}
		
		res += "Liste des documents associé à cette personne :\n";
		if (liste_objet.size() == 0) {
			res += "   AUCUN \n";
		} else {
			for (int i = 0; i < liste_objet.size(); i++) {
				res += " " + i+1 + ". " + liste_objet.get(i) +"\n";
			}
		}
		return res;
	}
	
	@Override
	public String export() {
		String res = this.getNiveau() + " @I" + this.getIdentificateur() + "@ " + this.getTag() + "\n" +
					this.nom.export() + "\n" +
					this.sexe.export() + "\n" +
					this.naissance.export() + "\n" +
					this.deces.export() + "\n";
		
		if (this.famille != null) {
			res += "  ".repeat(this.getNiveau() + 1) + Integer.toString(this.getNiveau() + 1) + " " + famille.getTag() + " @F" + famille.getIdentificateur() + "@\n";
		}
		for (Famille fam : this.liste_famille_p) {
			res += "  ".repeat(this.getNiveau() + 1) + Integer.toString(this.getNiveau() + 1) + " " + fam.getTag() + " @F" + fam.getIdentificateur() + "@\n";
		}
		for (Objet obj : liste_objet) {
			res += obj.export() + "\n";
		}
		return res;
	}

	public Name getNom() {
		return nom;
	}

	public void setNom(Name nom) {
		this.nom = nom;
	}

	public Sex getSexe() {
		return sexe;
	}

	public void setSexe(Sex sexe) {
		this.sexe = sexe;
	}

	public Birth getNaissance() {
		return naissance;
	}

	public void setNaissance(Birth naissance) {
		this.naissance = naissance;
	}

	public Death getDeces() {
		return deces;
	}

	public void setDeces(Death deces) {
		this.deces = deces;
	}

	public Famille getFamille() {
		return famille;
	}

	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	public ArrayList<Famille> getListe_famille_p() {
		return liste_famille_p;
	}

	public void setListe_famille_p(ArrayList<Famille> liste_famille_p) {
		this.liste_famille_p = liste_famille_p;
	}

	public ArrayList<Objet> getListe_objet() {
		return liste_objet;
	}

	public void setListe_objet(ArrayList<Objet> liste_objet) {
		this.liste_objet = liste_objet;
	}

}
