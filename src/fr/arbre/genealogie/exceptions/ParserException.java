package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception ParserException héritant de ImportException.<br>
 * Sers à hiérarchisé les Exceptions. Ajoute dans le messager d'erreur la ligne où s'est produit l'erreur.
 */
public class ParserException extends ImportException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -735553858513385010L;

	/**
	 * Constructeur de la classe ParserException.
	 * @param msg
	 * @param ligne
	 */
	public ParserException(String msg, int ligne) {
		super("ParserException - " + msg + " - [ligne : " + ligne + "]");
	}

}
