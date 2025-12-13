package fr.arbre.genealogie.shell.commands;

import fr.arbre.genealogie.utils.Command;

public class Graph implements Command{
	
	private String args;
	private String description;

	public Graph() {
		super();
		this.args = null;
		this.description = "graph [profondeur] - Afficher le contenu de la base de donnée";
	}
	
	public void setArgs(String args) {
		this.args = args;
	}
	
	@Override
	public String getResult(){
		return "A faire";
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
