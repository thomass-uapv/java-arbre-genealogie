package fr.arbre.genealogie.exceptions;

public class InvalidParameterException extends ArbreGenalogieException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3466728625864942809L;

	public InvalidParameterException(String msg) {
		super("InvalidParameterException - " + msg);
	}

}
