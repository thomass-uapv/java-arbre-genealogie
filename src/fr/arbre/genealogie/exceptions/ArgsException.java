package fr.arbre.genealogie.exceptions;

/**
 * Class correspondant à l'Exception ArgsException héritant de CommandException.<br>
 * Survient lorsqu'il y a un problème avec les arguments données dans les commandes.
 */
public class ArgsException extends CommandException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7426302124012393908L;

	/**
	 * Constructeur de la classe ArgsException.
	 * @param msg
	 */
	public ArgsException(String msg) {
		super("ArgsNullException - " + msg);
	}

}
