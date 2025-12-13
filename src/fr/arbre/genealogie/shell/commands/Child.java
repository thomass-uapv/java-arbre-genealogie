package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
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
		ArrayList<Famille> liste_familles_parent = indi.getListe_famille_p();
		String res = "";
		if (liste_familles_parent.size() > 0) {
			for (Famille f : liste_familles_parent) {
				for(Individu enfant : f.getEnfants()) {
					res += "\n ---- \n" + enfant.toString();
				}
			}
		}
		return res.trim();
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
