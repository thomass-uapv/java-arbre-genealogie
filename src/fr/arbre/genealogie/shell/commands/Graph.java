package fr.arbre.genealogie.shell.commands;

import java.io.InputStream;
import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsNullException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

public class Graph implements Command{

	private String args;
	private String description;
	private int profondeur;

	public Graph() {
		super();
		this.args = null;
		this.description = "graph [profondeur] - Afficher le contenu de la base de donnée";
		this.profondeur = -1;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	@Override
	public String getResult() throws ArgsNullException {
		if (args != null) {			
			try{
				this.profondeur = Integer.parseInt(args);
			} catch (NumberFormatException e) {
				throw new ArgsNullException("Argument donné incorrect.");
			}
		}

		ArrayList<Famille> sommetFamille = new ArrayList<Famille>();

		for (Famille fam : Shell.getBddListFam()) {
			if (fam.getPere().getFamille() == null && fam.getMere().getFamille() == null) {
				sommetFamille.add(fam);
			}
		}

		String res = "";
		for (Famille fam : sommetFamille) {
			res += dessiner(fam, 0);
		}

		return res;
	}

	private String dessiner(Famille current, int lvl) {
		String res = "";
		System.out.println("lvl = " + lvl);
		if (current != null) {
			if (lvl > 0) {
				res += " | ".repeat(lvl);
			}
			res += "@F" + current.getIdentificateur() + " : ";
			if (current.getPere() != null) {
				res += current.getPere().getNom();
			} else {
				res += "UNKNOWN";
			}
			res += " & ";
			if (current.getMere() != null) {
				res += current.getMere().getNom();
			} else {
				res += "UNKNOWN";
			}
			res += "\n";
			if (profondeur == -1 || lvl <= profondeur) {					
				for(Individu enfant : current.getEnfants()) {
					if (enfant.getListe_famille_p().size() > 0) {					
						for (Famille fam : enfant.getListe_famille_p()) {					
							res += "" + dessiner(fam, lvl + 1);
						}
					} else {
						res += " | ".repeat(lvl + 1) + "- " + enfant.getNom() + "\n";
					}
				}
			}
		}
		return res;
	}



	@Override
	public String getDescription() {
		return this.description;
	}

}
