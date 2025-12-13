package fr.arbre.genealogie.exceptions;

public class CommandException extends ArbreGenalogieException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8231491921489472450L;

	public CommandException(String msg) {
		super("CommandException - " + msg);
	}

}
