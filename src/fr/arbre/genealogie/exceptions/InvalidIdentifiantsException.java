package fr.arbre.genealogie.exceptions;

public class InvalidIdentifiantsException extends ArbreGenalogieException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -167193833897981393L;

	public InvalidIdentifiantsException(String msg) {
		super("InvalidIdentifiantsException - " + msg);
	}

}
