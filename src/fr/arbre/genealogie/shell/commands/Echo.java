package fr.arbre.genealogie.shell.commands;

import fr.arbre.genealogie.utils.Command;

public class Echo implements Command{
	private String args;
	private String description;

	public Echo() {
		super();
		this.args = null;
		this.description = "echo <Message> - Afficher un message";
	}
		
	@Override
	public String getResult(){
		return args;
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
