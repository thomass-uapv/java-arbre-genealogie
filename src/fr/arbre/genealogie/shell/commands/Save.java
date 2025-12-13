package fr.arbre.genealogie.shell.commands;

import java.io.IOException;

import fr.arbre.genealogie.exceptions.ArgsNullException;
import fr.arbre.genealogie.io.Export;
import fr.arbre.genealogie.utils.Command;

public class Save implements Command{
	private String args;
	private String description;

	public Save () {
		super();
		this.args = null;
		this.description = "export <path> - Exporter la base de donnée dans un fichier GED (Alias : save)";
	}
		
	@Override
	public String getResult() throws IOException, ArgsNullException{
		if (args == null || args.isBlank()) {
			this.args = "output.ged";
		}
		System.out.println(args);
		Export exp = new Export(args);
		String path = exp.export();
		return "Sauvegarde effectuée à l'adresse : " + path;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	public void setArgs(String args) {
		this.args = args;
	}
	
	public String getArgs() {
		return args;
	}
	
}
