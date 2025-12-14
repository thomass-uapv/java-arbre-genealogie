package fr.arbre.genealogie.exceptions;

public class ArgsException extends CommandException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7426302124012393908L;

	public ArgsException(String msg) {
		super("ArgsNullException - " + msg);
	}

}
