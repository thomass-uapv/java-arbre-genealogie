package fr.arbre.genealogie.exceptions;


/**
 * Classe correspondant à l'Exception MissingReciprocalLinkException héritant de ImportException.<br>
 * Sers à hiérarchisé les Exception.
 */
public class MissingReciprocalLinkException extends ImportException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8257759442108429101L;

	/**
	 * Constructeur de la classe MissingReciprocalLinkException.
	 * @param msg
	 */
	public MissingReciprocalLinkException(String msg) {
		super("MissingReciprocalLinkException - " + msg);

	}



}
