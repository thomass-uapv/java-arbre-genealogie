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

	public void setArgs(String args) {
		this.args = args;
	}

	@Override
	public String getResult() throws MissingEntreeException, ArgsNullException{
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
		ArrayList<Individu> liste_frere_soeur = indi.getFamille().getEnfants();
		String res = "";
		if (liste_frere_soeur.size() > 1) {
			for (Individu sibling : liste_frere_soeur) {
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

}
