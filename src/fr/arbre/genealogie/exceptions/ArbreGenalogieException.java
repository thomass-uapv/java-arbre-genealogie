package fr.arbre.genealogie.exceptions;

public class ArbreGenalogieException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9029866813167536376L;

	public ArbreGenalogieException(String msg) {
		super("ArbreGenalogieException - " + msg);
	}

	
}
