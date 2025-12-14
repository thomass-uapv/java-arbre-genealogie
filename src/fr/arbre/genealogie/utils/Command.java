package fr.arbre.genealogie.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.CycleException;
import fr.arbre.genealogie.exceptions.ESException;
import fr.arbre.genealogie.exceptions.IncorrectSexeException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.exceptions.InvalidParameterException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;

/**
 * Interface définissant les méthodes que chaque Commande doit implémenter.
 */
public abstract class Command {

	protected String args;
	protected String description;

	public Command(String args, String description) {
		super();
		this.args = args;
		this.description = description;
	}

	/**
	 * Renvoie le résultat de la commande une fois exécutée.
	 * @return String contenant le résultat de la commande une fois exécutée.
	 * @throws ArgsException
	 * @throws MissingEntreeException
	 * @throws InvalidParameterException
	 * @throws InvalidIdentifiantsException
	 * @throws FileNotFoundException
	 * @throws IncorrectSexeException
	 * @throws CycleException
	 * @throws IOException
	 * @throws ESException
	 */
	public abstract String getResult() throws ArgsException, MissingEntreeException, InvalidParameterException, InvalidIdentifiantsException, FileNotFoundException, IncorrectSexeException, CycleException, IOException, ESException;

	/**
	 * Renvoie ce que fait la commande, comment l'utiliser et ses alias.
	 * @return String contenant la description.
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Défini les arguments pour la commande.
	 * @param args
	 */
	public void setArgs(String args) {
		this.args = args;
	}

	/**
	 * Renvoie les arguments
	 * @return String qui contient les arguments de la commande.
	 */
	public String getArgs() {
		return args;
	}
}
