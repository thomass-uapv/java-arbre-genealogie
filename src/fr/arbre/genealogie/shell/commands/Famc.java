package fr.arbre.genealogie.shell.commands;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
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
	
	public void setArgs(String args) {
		this.args = args;
	}
	
	@Override
	public String getResult(){
		if (args == null) {
			return "Veuillez donner un argument";
		}
		Individu indi;
		try {
			int id = Integer.parseInt(args);
			indi = Shell.getBddInd(id);
		} catch (NumberFormatException  e) {
			indi = Shell.getBddInd(args);
		}
		if (indi == null) {
			return "Aucun individu n'a été trouvé !";
		}
		Famille fam = indi.getFamille();
		if (fam != null) {
			return fam.toString();
		} else {			
			return "Cet individu n'a pas de famille renseignée.";
		}
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
