package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

/**
 * Classe de la commande siblings. Hérite de Command.
 */
public class Siblings extends Command {

	/**
	 * Constructeur de la classe Siblings.
	 */
	public Siblings() {
		super(null, "siblings <Prénoms+Nom/ID> - Afficher des informations sur les frères et soeurs de l'individu");
	}

	@Override
	public String getResult() throws MissingEntreeException, ArgsException{
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
			// Cette exception a été volontairement utilisé pour indiquer à l'utilisateur qu'aucun individu a été trouvé.
			throw new MissingEntreeException("Aucun individu n'a été trouvé !");
		}
		if (indi.getFamille() == null) {
			// Cette exception a été volontairement utilisé pour indiquer à l'utilisateur qu'aucun individu a été trouvé.
			throw new MissingEntreeException("Cet individu n'a pas de famille renseignée.");
		}
		ArrayList<Individu> listeFrereSoeur = indi.getFamille().getEnfants();
		String res = "";
		if (listeFrereSoeur.size() > 1) {
			for (Individu sibling : listeFrereSoeur) {
				if (!sibling.equals(indi))
					res += sibling.toString() + "\n ---- \n";
			}
		} else {
			res += "Aucun frère et/ou soeur";
		}
		return res;
	}

}
