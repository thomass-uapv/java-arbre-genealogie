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

/**
 * Un objet qui associe différentes informations d'un même Individu. Un Individu hérite de Entree.
 * @see Entree
 */
public class Individu extends Entree {

	/**
	 * Le nom de l'Individu.
	 */
	private Name nom;

	/**
	 * Le sexe de l'Individu.
	 */
	private Sex sexe;

	/**
	 * La naissance de l'Individu.
	 */
	private Birth naissance;

	/**
	 * Le décès de l'Individu.
	 */
	private Death deces;

	/**
	 * La Famille où l'Individu est enfant.
	 */
	private Famille famille;

	/**
	 * La liste des Familles où l'Individu est parent.
	 */
	private ArrayList<Famille> listeFamilleParent;

	/**
	 * La liste des documents associés à l'Individu.
	 */
	private ArrayList<Objet> listeObjet;

	/**
	 * Constructeur de la classe Individu. On instancie tous les attributs de l'individu.
	 * @param id
	 */
	public Individu(int id) {
		super(0, "INDI", id);
		this.nom = new Name();
		this.sexe = new Sex();
		this.naissance = new Birth();
		this.deces = new Death();
		this.famille = null;
		this.listeFamilleParent = new ArrayList<Famille>();
		this.listeObjet = new ArrayList<Objet>();
	}

	@Override
	public void parser(String texte, int cptLigne) {
		TagTemplate current = null;
		ArrayList<String> lines = new ArrayList<String>(Arrays.asList(texte.split("\n")));
		int i = 0;
		while (i < lines.size()) {
			String[] splited = lines.get(i).split(" ");
			if (splited[0].equals(Integer.toString(this.getNiveau() + 1))) {
				current = null;
				if (splited[1].equals(this.nom.getTag())) {
					current = this.nom;
				} else if (splited[1].equals(this.sexe.getTag())) {
					current = this.sexe;
				} else if (splited[1].equals("FAMC") || splited[1].equals("FAMS")) {
					int id = Integer.parseInt(splited[2].substring(2, splited[2].length() - 1));
					current = Shell.getBddFam(id);
					if (current == null) {
						MissingEntreeException e = new MissingEntreeException("Famille manquante, création de la famille...", cptLigne + i);
						System.err.println(e.getMessage());
						current = new Famille(id);
						Shell.addBddFam((Famille) current);
					}
					if (splited[1].equals("FAMC")) {
						if (this.famille == null) {
							this.famille = (Famille) current;
						}
					} else if (splited[1].equals("FAMS")) {
						this.listeFamilleParent.add((Famille) current);
					}
				} else if (splited[1].equals(this.naissance.getTag())) {
					current = this.naissance;
				} else if (splited[1].equals(this.deces.getTag())) {
					current = this.deces;
				} else if (splited[1].equals("OBJE")) {
					current = new Objet();
					this.listeObjet.add((Objet) current);
				}
				if (current != null) {
					if (!(splited[1].equals("FAMC") || splited[1].equals("FAMS"))) {
						current.setLigne(cptLigne + i);
					}
					current.parser(lines.get(i).trim(), cptLigne + i);
				}
				i++;
			} else {
				if (current != null) {
					String bloc = lines.get(i++).trim() + "\n";
					int cptLigneDebutBloc = cptLigne + i - 1;
					while (i < lines.size() && !lines.get(i).trim().split(" ")[0].equals(Integer.toString(this.getNiveau() + 1))) {
						bloc += lines.get(i++).trim() + "\n";
					}
					current.parser(bloc, cptLigneDebutBloc);
				} else {
					i++;
				}
			}
		}

	}

	/**
	 * Compare l'identificateur de l'Individu avec celui de l'Individu donné en paramètre.
	 * @param obj : Individu donnée en paramètre.
	 * @return Vrai si l'identificateur de l'Individu est égale celui de l'Individu donné en paramètre, sinon Faux.
	 */
	public boolean equals(Individu obj) {
		return (this.getIdentificateur() == obj.getIdentificateur());
	}

	/**
	 * Renvoie la liste des individus avec qui l'Individu est marié.
	 * @return une collection des individus avec qui l'Individu est marié.
	 */
	public ArrayList<Individu> marriedWith() {
		ArrayList<Individu> marriedWith = new ArrayList<Individu>();
		for (Famille fam : this.listeFamilleParent) {
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
			res = "Prénoms & Nom : " + this.nom.toString() + "\n" + "Sexe : " + sexe.toString() + "\n"
					+ "Naissance : \n  " + this.naissance.toString() + "\n" + "Décès : \n  " + this.deces.toString()
					+ "\n" + "Famille : UNKNOWN \n" + "Familles où l'individu est parent :\n";
		} else {
			res = "Prénoms & Nom : " + nom.toString() + "\n" + "Sexe : " + sexe.toString() + "\n" + "Naissance : \n  "
					+ this.naissance.toString() + "\n" + "Décès : \n  " + this.deces.toString() + "\n" + "Famille : "
					+ Integer.toString(famille.getIdentificateur()) + "\n" + "Familles où l'individu est parent :\n";
		}
		if (listeFamilleParent.size() == 0) {
			res += "   AUCUNE \n";
		} else {
			for (Famille fam : listeFamilleParent) {
				res += " - @F" + Integer.toString(fam.getIdentificateur()) + "@\n";
			}
		}

		res += "Liste des documents associé à cette personne :\n";
		if (listeObjet.size() == 0) {
			res += "   AUCUN \n";
		} else {
			for (int i = 0; i < listeObjet.size(); i++) {
				res += " " + i + 1 + ". " + listeObjet.get(i) + "\n";
			}
		}
		return res;
	}

	@Override
	public String export() {
		String res = this.getNiveau() + " @I" + this.getIdentificateur() + "@ " + this.getTag() + "\n"
				+ this.nom.export() + "\n" + this.sexe.export() + "\n" + this.naissance.export() + "\n"
				+ this.deces.export() + "\n";

		if (this.famille != null) {
			res += "  ".repeat(this.getNiveau() + 1) + Integer.toString(this.getNiveau() + 1) + " " + famille.getTag()
			+ "C @F" + famille.getIdentificateur() + "@\n";
		}
		for (Famille fam : this.listeFamilleParent) {
			res += "  ".repeat(this.getNiveau() + 1) + Integer.toString(this.getNiveau() + 1) + " " + fam.getTag()
			+ "S @F" + fam.getIdentificateur() + "@\n";
		}
		for (Objet obj : listeObjet) {
			res += obj.export() + "\n";
		}
		return res + "\n";
	}

	/**
	 * Renvoie le nom de l'Individu.
	 * @return le nom de l'Individu.
	 */
	public Name getNom() {
		return nom;
	}

	/**
	 * Définit le nom de l'Individu.
	 * @param nom
	 */
	public void setNom(Name nom) {
		this.nom = nom;
	}

	/**
	 * Renvoie le sexe de l'Individu.
	 * @return le sexe de l'Individu.
	 */
	public Sex getSexe() {
		return sexe;
	}

	/**
	 * Définit le sexe de l'Individu.
	 * @param sexe
	 */
	public void setSexe(Sex sexe) {
		this.sexe = sexe;
	}

	/**
	 * Renvoie la naissance de l'Individu.
	 * @return la naissance de l'Individu.
	 */
	public Birth getNaissance() {
		return naissance;
	}

	/**
	 * Définit la naissance de l'Individu.
	 * @param naissance
	 */
	public void setNaissance(Birth naissance) {
		this.naissance = naissance;
	}

	/**
	 * Renvoie le décès de l'Individu.
	 * @return le décès de l'Individu.
	 */
	public Death getDeces() {
		return deces;
	}

	/** Définit le décès de l'Individu (voir classe Death).
	 * @param deces
	 */
	public void setDeces(Death deces) {
		this.deces = deces;
	}

	/**
	 * Renvoie la famille où l'Individu est enfant.
	 * @return le famille où l'Individu est enfant.
	 */
	public Famille getFamille() {
		return famille;
	}

	/**
	 * Définit la famille où l'Individu est enfant.
	 * @param famille
	 */
	public void setFamille(Famille famille) {
		this.famille = famille;
	}

	/**
	 * Renvoie la liste des familles où l'Individu est parent.
	 * @return la liste des familles où l'Individu est parent.
	 */
	public ArrayList<Famille> getListeFamilleParent() {
		return listeFamilleParent;
	}

	/**
	 * Ajoute une famille où l'Individu est parent.
	 * @param fam
	 */
	public void addListeFamilleParent(Famille fam) {
		this.listeFamilleParent.add(fam);
	}

	/**
	 * Renvoie la liste des documents associés à l'Individu.
	 * @return la liste des documents associés à l'Individu.
	 */
	public ArrayList<Objet> getListeObjet() {
		return listeObjet;
	}

	/**
	 * Ajoute un document associé à l'Individu.
	 * @param objet
	 */
	public void addListeObjet(Objet objet) {
		this.listeObjet.add(objet);
	}

}

