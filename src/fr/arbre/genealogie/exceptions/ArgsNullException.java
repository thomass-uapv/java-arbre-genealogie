package fr.arbre.genealogie.exceptions;

public class ArgsNullException extends ArbreGenalogieException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7426302124012393908L;

	public ArgsNullException(String msg) {
		super("ArgsNullException" + msg);
	}

}
