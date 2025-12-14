package fr.arbre.genealogie.shell.commands;

import java.io.IOException;

import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.ESException;
import fr.arbre.genealogie.io.Export;
import fr.arbre.genealogie.utils.Command;


/**
 * Classe de la commande save. Hérite de Command.
 */
public class Save extends Command{

	/**
	 * Constructeur de la classe Save.
	 */
	public Save () {
		super(null, "export <path> - Exporter la base de donnée dans un fichier GED (Alias : save)");
	}

	@Override
	public String getResult() throws ArgsException, ESException{
		if (args == null || args.isBlank()) {
			this.args = "output.ged";
		}
		System.out.println(args);
		Export exp;
		try {
			exp = new Export(args);
			String path = exp.export();
			return "Sauvegarde effectuée à l'adresse : " + path;
		} catch (IOException e) {
			throw new ESException("L'exportation a échouée");
		}
	}

}
