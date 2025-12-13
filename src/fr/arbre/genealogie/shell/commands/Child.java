package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsNullException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

public class Child implements Command{

	private String args;
	private String description;

	public Child() {
		super();
		this.args = null;
		this.description = "child <Prénoms+Nom/ID> - Afficher des informations sur les enfants de l'individu";
	}
	
	@Override
	public String getResult() throws ArgsNullException, MissingEntreeException{
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
		ArrayList<Famille> liste_familles_parent = indi.getListeFamilleParent();
		String res = "";
		if (liste_familles_parent.size() > 0) {
			for (Famille f : liste_familles_parent) {
				for(Individu enfant : f.getEnfants()) {
					res += "\n ---- \n" + enfant.toString();
				}
			}
		} else {
			res += "Cet individu n'a aucun enfants";
		}
		return res.trim();
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
