package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

public class Married implements Command{

	private ArrayList<String> args;
	private String description;

	public Married() {
		super();
		this.args = new ArrayList<String>();
		this.description = "<Prénoms+Nom/ID> married <Prénoms+Nom/ID> - Renvoie vrai si les deux individus existent et sont mariés";
	}

	public void setArgs(String args) {
		this.args.clear();
		if (args != null) {
			if (args.indexOf("|") > 0) {				
				this.args.add(args.substring(0,args.indexOf("|")));
			}
			this.args.add(args.substring(args.indexOf("|")+1,args.length()));
		}
	}

	@Override
	public String getResult(){
		if (args.size() < 2) {
			return "Il manque au moins 1 argument";
		}
		Individu indi1;
		Individu indi2;
		try {
			int id = Integer.parseInt(this.args.get(0));
			indi1 = Shell.getBddInd(id);
		} catch (NumberFormatException  e) {
			indi1 = Shell.getBddInd(this.args.get(0));
		}
		try {
			int id = Integer.parseInt(this.args.get(1));
			indi2 = Shell.getBddInd(id);
		} catch (NumberFormatException  e) {
			indi2 = Shell.getBddInd(this.args.get(1));
		}
		if (indi1 == null) { //TODO Modifier ça en exceptions
			return "L'individu " + args.get(0) + " n'a pas été trouvé !";
		} else if (indi2 == null) {
			return "L'individu " + args.get(1) + " n'a pas été trouvé !";
		}
		for(Individu marWith : indi1.marriedWith()) {			
			if (marWith.equals(indi2)) {
				return "Vrai";
			}
		}
		return "Faux";
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
