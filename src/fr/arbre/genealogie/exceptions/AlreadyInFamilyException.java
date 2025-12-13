package fr.arbre.genealogie.exceptions;

public class AlreadyInFamilyException extends ArbreGenalogieException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -366622048761448690L;

	public AlreadyInFamilyException(String msg) {
		super("AlreadyInFamilyException - " + msg);
	}

	
	
}
