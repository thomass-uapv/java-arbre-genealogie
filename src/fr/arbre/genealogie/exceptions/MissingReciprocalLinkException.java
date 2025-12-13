package fr.arbre.genealogie.exceptions;

public class MissingReciprocalLinkException extends ArbreGenalogieException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8257759442108429101L;

	public MissingReciprocalLinkException(String msg) {
		super("MissingReciprocalLinkException - " + msg);
	}

}
