package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsNullException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

public class Siblings implements Command {

	private String args;
	private String description;

	public Siblings() {
		super();
		this.args = null;
		this.description = "siblings <Prénoms+Nom/ID> - Afficher des informations sur les frères et soeurs de l'individu";
	}

	@Override
	public String getResult() throws MissingEntreeException, ArgsNullException{
		if (args == null || args.isBlank()) {
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
		if (indi.getFamille() == null) {
			throw new MissingEntreeException("Cet individu n'a pas de famille renseignée.", -1);
		}
		ArrayList<Individu> listeFrereSoeur = indi.getFamille().getEnfants();
		String res = "";
		if (listeFrereSoeur.size() > 1) {
			for (Individu sibling : listeFrereSoeur) {
				if (!sibling.equals(indi))
				res += sibling.toString() + "\n ---- \n";
			}
		} else {
			res += "Aucun frère et/ou soeur";
		}
		return res;
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
