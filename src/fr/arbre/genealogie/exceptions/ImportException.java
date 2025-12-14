package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception ImportException héritant de ArbreGenealogieException.<br>
 * Sers à hiérarchisé les Exceptions.
 */
public class ImportException extends ArbreGenealogieException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8939514757850717373L;

	/**
	 * Constructeur de la classe ImportException
	 * @param msg
	 */
	public ImportException(String msg) {
		super("ImportException - " + msg);
	}

}
