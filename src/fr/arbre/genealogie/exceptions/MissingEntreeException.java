package fr.arbre.genealogie.exceptions;

public class MissingEntreeException extends ParserException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8701269660594093909L;

	public MissingEntreeException(String msg, int ligne) {
		super("MissingEntreeException - " + msg, ligne);
	}

}
