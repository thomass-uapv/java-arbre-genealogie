package fr.arbre.genealogie.exceptions;

/**
 * Classe correspondant à l'Exception CycleException héritant de ImportException.<br>
 * Elle survient au moment où un cycle est trouvé dans la base de donnée.
 */
public class CycleException extends ImportException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 601425724595218849L;

	/**
	 * Constructeur de la classe CycleException.
	 * @param msg
	 */
	public CycleException(String msg) {
		super("CycleException - " + msg);
	}

}
