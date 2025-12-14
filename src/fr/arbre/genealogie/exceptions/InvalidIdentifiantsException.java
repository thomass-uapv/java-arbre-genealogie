package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception InvalidIdentifiantsException héritant de ImportException.<br>
 * Survient lorsqu'il y a un problème dans les identifiants des Entree (voir classe Entree), notamment un identifiant manquant.
 */
public class InvalidIdentifiantsException extends ImportException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -167193833897981393L;

	/**
	 * Constructeur de la classe InvalidIdentifiantsException.
	 * @param msg
	 */
	public InvalidIdentifiantsException(String msg) {
		super("InvalidIdentifiantsException - " + msg);
	}

}
