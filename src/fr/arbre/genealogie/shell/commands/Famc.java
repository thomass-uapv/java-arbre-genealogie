package fr.arbre.genealogie.shell.commands;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

/**
 * Classe de la commande famc. Hérite de Command.
 */
public class Famc extends Command{

	/**
	 * Constructeur de la classe Famc.
	 */
	public Famc() {
		super(null, "famc <Prénoms+Nom/ID> - Afficher toutes les informations de la famille de l'individu");
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
		Famille fam = indi.getFamille();
		if (fam != null) {
			return fam.toString();
		} else {			
			throw new MissingEntreeException("Cet individu n'a pas de famille renseignée.");
		}
	}

}

