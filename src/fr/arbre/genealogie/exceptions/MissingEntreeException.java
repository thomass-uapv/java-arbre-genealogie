package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception MissingEntreeException héritant de ParserException.<br>
 * Surviens lorsqu'une Entree (voir classe Entree) n'existe pas encore et qu'il faut la créer.
 * Surviens également quand l'individu demandé en argument de commande n'existe pas.
 */
public class MissingEntreeException extends ParserException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8701269660594093909L;

	/**
	 * Constructeur de la classe MissingEntreeException lorsqu'une Entree n'existe pas encore et qu'il faut la créer.
	 * @param msg
	 * @param ligne
	 */
	public MissingEntreeException(String msg, int ligne) {
		super("MissingEntreeException - " + msg, ligne);
	}

	/**
	 * Constructeur de la classe MissingEntreeException lorsque l'individu demandé en argument de commande n'existe pas.
	 * @param msg
	 */
	public MissingEntreeException(String msg) {
		super("MissingEntreeException - " + msg, -1);
	}

}
