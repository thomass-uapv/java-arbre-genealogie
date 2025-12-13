package fr.arbre.genealogie.exceptions;

public class IncorrectSexeException extends ArbreGenalogieException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2459470327264046737L;

	public IncorrectSexeException(String msg) {
		super("IncorrectSexeException - " + msg);
	}

}
