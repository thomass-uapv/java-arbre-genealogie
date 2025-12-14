package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

/**
 * Classe de la commande child. Hérite de Command.
 */
public class Child extends Command{

	/**
	 * Constructeur de la classe Child.
	 */
	public Child() {
		super(null, "child <Prénoms+Nom/ID> - Afficher des informations sur les enfants de l'individu");
	}

	@Override
	public String getResult() throws ArgsException, MissingEntreeException{
		if (args == null || args.isBlank()) {
			throw new ArgsException("Veuillez donner un argument");
		}
		Individu indi;
		try {
			int id = Integer.parseInt(args);
			indi = Shell.getBddInd(id);
		} catch (NumberFormatException  e) {
			indi = Shell.getBddInd(args);
		}
		if (indi == null) {
			// Cette exception a été volontairement utilisée pour indiquer à l'utilisateur qu'aucun Individu a été trouvé.
			throw new MissingEntreeException("Aucun individu n'a été trouvé !");
		}
		ArrayList<Famille> liste_familles_parent = indi.getListeFamilleParent();
		String res = "";
		if (liste_familles_parent.size() > 0) {
			for (Famille f : liste_familles_parent) {
				for(Individu enfant : f.getEnfants()) {
					res += "\n ---- \n" + enfant.toString();
				}
			}
		} else {
			res += "Cet individu n'a aucun enfants";
		}
		return res.trim();
	}

}

