package fr.arbre.genealogie.exceptions;

/**
 * Classe de l'Exception générale du projet. Hérite de Exception.<br>
 * Survient dès qu'une Exception personnalisé est lancée.
 */
public class ArbreGenealogieException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9029866813167536376L;

	/**
	 * Constructeur de l'Exception ArbreGenalogieException.
	 * @param msg
	 */
	public ArbreGenealogieException(String msg) {
		super("ArbreGenalogieException - " + msg);
	}


}
