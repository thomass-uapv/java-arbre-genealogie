package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

/**
 * Classe de la commande graph. Hérite de Command.
 */
public class Graph extends Command{

	private int profondeur;

	public Graph() {
		super(null, "graph [profondeur] - Afficher le contenu de la base de donnée");
		this.profondeur = -1;
	}

	@Override
	public String getResult() throws ArgsException {
		if (args != null) {			
			try{
				this.profondeur = Integer.parseInt(args);
			} catch (NumberFormatException e) {
				throw new ArgsException("Argument donné incorrect.");
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

	/**
	 * Méthode privée récursive qui va dessiner petit à petit le graphe.
	 * @param current : la famille actuelle qu'on cherche à dessiner.
	 * @param lvl : le niveau de profondeur (pour dessiner une hiérarchie).
	 * @return String contenant le graphe dessiné.
	 */
	private String dessiner(Famille current, int lvl) {
		String res = "";
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
					if (enfant.getListeFamilleParent().size() > 0) {					
						for (Famille fam : enfant.getListeFamilleParent()) {					
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

}
