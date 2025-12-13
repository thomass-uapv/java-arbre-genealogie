package fr.arbre.genealogie.exceptions;

public class ParserException extends ArbreGenalogieException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -735553858513385010L;

	public ParserException(String msg, int ligne) {
		super("ParserException - " + msg + " - [ligne : " + ligne + "]");
	}

}
