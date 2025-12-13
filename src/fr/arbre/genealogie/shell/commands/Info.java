package fr.arbre.genealogie.shell.commands;

import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsNullException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

public class Info implements Command{

	private String args;
	private String description;

	public Info() {
		super();
		this.args = null;
		this.description = "info <Prénoms+Nom/ID> - Afficher des informations sur l'individu";
	}
	
	public void setArgs(String args) {
		this.args = args;
	}
	
	@Override
	public String getResult() throws ArgsNullException, MissingEntreeException{
		if (args == null) {
			throw new ArgsNullException("Veuillez donner un argument");
		}
		Individu indi;
		try {
			int id = Integer.parseInt(args);
			indi = Shell.getBddInd(id);
		} catch (NumberFormatException  e) {
			indi = Shell.getBddInd(args);
		}
		if (indi == null) {
			throw new MissingEntreeException("Aucun individu n'a été trouvé !", -1);
		}
		return indi.toString();
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
