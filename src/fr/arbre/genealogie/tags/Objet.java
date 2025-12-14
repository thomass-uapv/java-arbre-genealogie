package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

/**
 * Classe pour le TAG OBJE du standard GEDOM. Hérite de TagTemplate.
 */
public class Objet extends TagTemplate{

	/**
	 * Contient le format de l'objet.
	 */
	private Format format;

	/**
	 * Contient le titre de l'objet.
	 */
	private Title titre;

	/**
	 * Contient le lien du fichier de l'objet.
	 */
	private Fichier fichier;

	/**
	 * Constructeur de l'objet.
	 */
	public Objet() {
		super(1, "OBJE");
		this.format = new Format();
		this.titre = new Title();
		this.fichier = new Fichier();
	}

	@Override
	public void parser(String texte, int cptLigne) {
		TagTemplate current = null;
		ArrayList<String> lines = new ArrayList<String>(Arrays.asList(texte.split("\n")));
		int i = 0;
		while (i < lines.size()) {
			String[] splited = lines.get(i).split(" ");
			if (splited[0].equals(Integer.toString(this.getNiveau() + 1))) {
				if (splited[1].equals(this.format.getTag())) {
					current = this.format;
				} else if(splited[1].equals(this.titre.getTag())) {
					current = this.titre;
				} else if(splited[1].equals(this.fichier.getTag())) {
					current = this.fichier;
				}
				if (current != null) {
					current.parser(lines.get(i).trim(), cptLigne+i);
				}					
				i++;
			} else {
				if (current != null) {
					String bloc = lines.get(i++).trim() + "\n";
					int cpt_ligne_debut_bloc = cptLigne+i-1;
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

	@Override
	public String export() {
		return "  ".repeat(this.getNiveau()) + this.getNiveau() + this.getTag() + "\n" +
				this.titre.export() + "\n" +
				this.format.export() + "\n" +
				this.fichier.export() + "\n";
	}

	@Override
	public String toString() {
		return "Document :\n" +
				"  - Titre : " + this.titre + "\n" +
				"  - Format : " + this.format + "\n" + 
				"  - Lien : " + this.fichier + "\n";
	}

	/**
	 * Renvoie le format de l'objet
	 * @return le format de l'objet
	 */
	public Format getFormat() {
		return format;
	}

	/**
	 * Défini le format de l'objet.
	 * @param format : le format à définir
	 */
	public void setFormat(Format format) {
		this.format = format;
	}

	/**
	 * Renvoie le titre de l'objet.
	 * @return le titre de l'objet.
	 */
	public Title getTitre() {
		return titre;
	}

	/**
	 * Défini le titre de l'objet.
	 * @param titre : défini le titre de l'objet.
	 */
	public void setTitre(Title titre) {
		this.titre = titre;
	}

	/**
	 * Renvoie le lien du fichier de l'objet.
	 * @return le lien du fichier de l'objet.
	 */
	public Fichier getFichier() {
		return fichier;
	}

	/**
	 * Défini le lien du fichier de l'objet.
	 * @param fichier : le lien du fichier à définir.
	 */
	public void setFichier(Fichier fichier) {
		this.fichier = fichier;
	}





}
