package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.utils.Command;

public class Help implements Command{
	private ArrayList<String> liste_command;
	private String description;


	public Help() {
		super();
		this.liste_command = null;
		this.description = "help - Afficher le menu d'aide (Alias : ?)";
	}
	
	public void setListe_command(ArrayList<String> liste_command) {
		this.liste_command = liste_command;
	}

	@Override
	public String getResult() {
		String res = "quit - Quitter l'application (Alias : stop, exit)\n";
		for (String des : this.liste_command) {
			res += des + "\n";
		}
		return res;
	}

	public String getDescription() {
		return description;
	}
	
}
