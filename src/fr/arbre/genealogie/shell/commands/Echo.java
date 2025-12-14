package fr.arbre.genealogie.shell.commands;

import fr.arbre.genealogie.utils.Command;

/**
 * Classe de la commande echo. Hérite de Command.
 */
public class Echo extends Command{

	/**
	 * Constructeur de la classe Echo.
	 */
	public Echo() {
		super(null, "echo <Message> - Afficher un message");
	}

	@Override
	public String getResult(){
		return args;
	}	
}
