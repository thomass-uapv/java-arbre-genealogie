package fr.arbre.genealogie.shell.commands;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

public class Famc implements Command{

	private String args;
	private String description;

	public Famc() {
		super();
		this.args = null;
		this.description = "famc <Prénoms+Nom/ID> - Afficher toutes les informations de la famille de l'individu";
	}
	
	@Override
	public String getResult() throws ArgsException, MissingEntreeException{
		if (args == null || args.isBlank()) {
			throw new ArgsException("Veuillez donner un argument");
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
		Famille fam = indi.getFamille();
		if (fam != null) {
			return fam.toString();
		} else {			
			throw new MissingEntreeException("Cet individu n'a pas de famille renseignée.", -1);
		}
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
