package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.utils.Command;

/**
 * Classe de la commande help. Hérite de Command.
 */
public class Help extends Command{

	/**
	 * Contient la liste des descriptifs de chaque commande.
	 */
	private ArrayList<String> listeCommands;

	/**
	 * Constructeur de la classe Help.
	 */
	public Help() {
		super(null, "help - Afficher le menu d'aide (Alias : ?)");
		this.listeCommands = null;
	}

	/**
	 * Défini la liste des descriptions des commandes.
	 * @param listeCommands
	 */
	public void setListeCommands(ArrayList<String> listeCommands) {
		this.listeCommands = listeCommands;
	}

	/**
	 * Retourne la liste des descriptifs de chaque commande.
	 * @return la liste des descriptifs de chaque commande.
	 */
	public ArrayList<String> getListeCommands() {
		return listeCommands;
	}

	@Override
	public String getResult() {
		String res = "quit - Quitter l'application (Alias : stop, exit)\n";
		for (String des : this.listeCommands) {
			res += des + "\n";
		}
		return res;
	}

	@Override
	public void setArgs(String args) {
		System.err.println("Cette commande n'attend aucun argument!");
	}

	@Override
	public String getArgs() {
		return null;
	}

}
