package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception ESException héritant de ArbreGenealogieException.<br>
 * Sers à personnaliser le message d'erreur lorsqu'une IOException survient.
 */
public class ESException extends ArbreGenealogieException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6326127157634747793L;

	/**
	 * Constructeur de la classe ESException.
	 * @param msg
	 */
	public ESException(String msg) {
		super("ESException - " + msg);
	}

}
