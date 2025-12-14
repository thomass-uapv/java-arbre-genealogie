package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception CommandException héritant de ArbreGenealogieException.<br>
 * Sers à hiérarchisé les Exceptions.
 */
public class CommandException extends ArbreGenealogieException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8231491921489472450L;

	/**
	 * Constructeur de la classe CommandException.
	 * @param msg
	 */
	public CommandException(String msg) {
		super("CommandException - " + msg);
	}

}
