package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

public class Objet extends TagTemplate{

	private Format format;
	private Title titre;
	private Fichier fichier;

	public Objet() {
		super(1, "OBJE");
		this.format = new Format();
		this.titre = new Title();
		this.fichier = new Fichier();
	}

	@Override
	public void parser(String texte, int cpt_ligne) {
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

}
