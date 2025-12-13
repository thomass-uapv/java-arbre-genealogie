package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.utils.Command;

public class Help implements Command{
	private ArrayList<String> listeCommands;
	private String description;


	public Help() {
		super();
		this.listeCommands = null;
		this.description = "help - Afficher le menu d'aide (Alias : ?)";
	}
	
	public void setListeCommands(ArrayList<String> listeCommands) {
		this.listeCommands = listeCommands;
	}

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

	public String getDescription() {
		return description;
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
