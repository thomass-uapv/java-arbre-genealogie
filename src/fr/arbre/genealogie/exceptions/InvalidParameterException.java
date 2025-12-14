package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception InvalidParameterException héritant de ImportException.<br>
 * Survient lorsque le paramètre donné au parser général est vide.
 */
public class InvalidParameterException extends ImportException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3466728625864942809L;

	/**
	 * Constructeur de la classe InvalidParameterException
	 * @param msg
	 */
	public InvalidParameterException(String msg) {
		super("InvalidParameterException - " + msg);
	}

}
