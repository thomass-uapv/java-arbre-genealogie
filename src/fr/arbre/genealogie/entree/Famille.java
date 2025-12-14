package fr.arbre.genealogie.entree;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.tags.Marriage;
import fr.arbre.genealogie.utils.Entree;
import fr.arbre.genealogie.utils.TagTemplate;

/**
 * Un objet qui crée un lien entre différents individus. Une Famille hérite de Entree. Une Famille peut également avoir une date de marriage.
 * @see Entree
 */
public class Famille extends Entree{

	/**
	 * Individu correspondant au père de la Famille.
	 */
	private Individu pere;

	/**
	 * Individu correspondant à la mère de la Famille.
	 */
	private Individu mere;

	/**
	 * Collection d'Individu contenant tous les enfants de la Famille.
	 */
	private ArrayList<Individu> enfants;

	/**
	 * Objet marriage correspondant au marriage des parents de la Famille.
	 */
	private Marriage mariage;


	/**
	 * Constructeur de la classe Famille. Tous les attributs sont définis à null ou "UNKNOWN".
	 * @param id : l'identificateur de la Famille.
	 */
	public Famille(int id) {
		super(0, "FAM", id);
		this.pere = null;
		this.mere = null;
		this.mariage = new Marriage();
		this.enfants = new ArrayList<Individu>();
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
				if (splited[1].equals(this.mariage.getTag())) {
					current = this.mariage;
					current.setLigne(cptLigne+i);
				} else if (splited[1].equals("HUSB") || splited[1].equals("WIFE") || splited[1].equals("CHIL")){
					int id = Integer.parseInt(splited[2].substring(2,splited[2].length()-1));
					current = Shell.getBddInd(id);
					if (current == null) {
						MissingEntreeException e = new MissingEntreeException("Individu manquant, création de l'individu...", cptLigne+i);
						System.err.println(e.getMessage());
						current = new Individu(id);
						Shell.addBddInd((Individu) current);
					}
					if (splited[1].equals("HUSB")) {
						this.pere = (Individu) current;
					} else if (splited[1].equals("WIFE")) {
						this.mere = (Individu) current;
					} else if (splited[1].equals("CHIL")) {
						this.enfants.add((Individu) current);
					}
				}
				i++;
			} else {
				if (current != null) {					
					String bloc = lines.get(i++).trim() + "\n";
					int cptLigneDebutBloc = cptLigne+i-1;
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
	 * Renvoie le couple marié de la Famille (Père et mère).
	 * @return collection qui contient le père et la mère.
	 */
	public ArrayList<Individu> whoMarried(){
		ArrayList<Individu> res = new ArrayList<Individu>();
		res.add(this.pere);
		res.add(this.mere);
		return res;

	}

	/**
	 * Compare l'identificateur de la Famille avec celui de la famille donnée en paramètre.
	 * @param obj : Famille donnée en paramètre.
	 * @return Vrai si l'identificateur de la Famille est égale celui de la famille donnée en paramètre, sinon Faux.
	 */
	public boolean equals(Famille obj) {
		return (this.getIdentificateur() == obj.getIdentificateur());
	}

	@Override
	public String toString() {
		String res = "";
		if (this.mere != null) {
			res += "Mère : " + this.mere.getNom() + "\n";
		} else {
			res += "Mère : UNKOWN \n";
		}
		if (this.pere != null) {
			res += "Père : " + this.pere.getNom() + "\n";
		} else {
			res += "Père : UNKOWN \n";
		}
		res += "Liste des enfants :\n";
		if (this.enfants.size() > 0) {
			for (Individu enfant : this.enfants) {
				res += " - " + enfant.getNom() + "\n";
			}
		} else {
			res += "  Aucun";
		}
		res += this.mariage.toString();
		return res;
	}


	@Override
	public String export() {
		String res = this.getNiveau() + " @F" + this.getIdentificateur() + "@ " + this.getTag() + "\n" +
				this.mariage.export() + "\n";
		if (this.pere != null) {
			res += "  " + Integer.toString(this.getNiveau() + 1) + " HUSB @I" + this.pere.getIdentificateur() + "@\n";
		}
		if (this.mere != null) {
			res += "  " + Integer.toString(this.getNiveau() + 1) + " WIFE @I" + this.mere.getIdentificateur() + "@\n";
		}
		for (Individu indi : this.enfants) {
			res += "  " + Integer.toString(this.getNiveau() + 1) + " CHIL @I" + indi.getIdentificateur() + "@\n";
		}
		return res + "\n";

	}

	/**
	 * Retourne l'Individu correspondant au père.
	 * @return Individu correspondant au père.
	 */
	public Individu getPere() {
		return pere;
	}

	/**
	 * Définit l'Individu père de la Famille.
	 * @param pere
	 */
	public void setPere(Individu pere) {
		this.pere = pere;
	}

	/**
	 * Retourne l'Individu correspondant à la mère.
	 * @return Individu correspondant à la mère.
	 */
	public Individu getMere() {
		return mere;
	}

	/**
	 * Définit l'individu mère de la Famille.
	 * @param mere
	 */
	public void setMere(Individu mere) {
		this.mere = mere;
	}

	/**
	 * Retourne la liste des enfants de la Famille.
	 * @return une collection d'enfants de la Famille.
	 */
	public ArrayList<Individu> getEnfants() {
		return enfants;
	}

	/**
	 * Ajoute un enfant à la Famille
	 * @param enfant : ajoute enfant à la collection d'enfants de la Famille.
	 */
	public void addEnfant(Individu enfant) {
		this.enfants.add(enfant);
	}

	/**
	 * Renvoie le mariage de la famille.
	 * @return le mariage de la famille.
	 */
	public Marriage getMariage() {
		return mariage;
	}

	/**
	 * Définit le mariage de la famille.
	 * @param mariage
	 */
	public void setMariage(Marriage mariage) {
		this.mariage = mariage;
	}



}

