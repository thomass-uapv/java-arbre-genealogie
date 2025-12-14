package fr.arbre.genealogie.shell.commands;

import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;


/**
 * Classe de la commande info. Hérite de Command.
 */
public class Info extends Command{

	/**
	 * Constructeur de la classe Info.
	 */
	public Info() {
		super(null, "info <Prénoms+Nom/ID> - Afficher des informations sur l'individu");
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
			// Cette exception a été volontairement utilisé pour indiquer à l'utilisateur qu'aucun individu a été trouvé.
			throw new MissingEntreeException("Aucun individu n'a été trouvé !");
		}
		return indi.toString();
	}

}
