package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception IncorrectSexeException héritant de ImportException.<br>
 * Survient lorsque le sexe de la personne est incorrect pour une situation fixée.
 */
public class IncorrectSexeException extends ImportException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2459470327264046737L;

	/**
	 * Constructeur de l'Exception IncorrectSexeException
	 * @param msg
	 */
	public IncorrectSexeException(String msg) {
		super("IncorrectSexeException - " + msg);
	}

}
