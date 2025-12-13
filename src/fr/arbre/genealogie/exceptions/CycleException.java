package fr.arbre.genealogie.exceptions;

public class CycleException extends ArbreGenalogieException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 601425724595218849L;

	public CycleException(String msg) {
		super("CycleException - " + msg);
	}

}
