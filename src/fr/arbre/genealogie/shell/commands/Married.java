package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;


/**
 * Classe de la commande married. Hérite de Command.
 */
public class Married extends Command{

	/**
	 * Liste des arguments, car cette commande prend 2 arguments.
	 */
	private ArrayList<String> arguments;

	public Married() {
		super(null, "<Prénoms+Nom/ID> married <Prénoms+Nom/ID> - Renvoie vrai si les deux individus existent et sont mariés");
		this.arguments = new ArrayList<String>();
	}

	@Override
	public String getResult() throws ArgsException, InvalidIdentifiantsException{
		if (arguments.size() < 2) {
			throw new ArgsException("Il manque au moins 1 argument");
		}
		Individu indi1;
		Individu indi2;
		try {
			int id = Integer.parseInt(this.arguments.get(0));
			indi1 = Shell.getBddInd(id);
		} catch (NumberFormatException  e) {
			indi1 = Shell.getBddInd(this.arguments.get(0));
		}
		try {
			int id = Integer.parseInt(this.arguments.get(1));
			indi2 = Shell.getBddInd(id);
		} catch (NumberFormatException  e) {
			indi2 = Shell.getBddInd(this.arguments.get(1));
		}
		if (indi1 == null) {
			throw new InvalidIdentifiantsException("L'individu " + arguments.get(0) + " n'a pas été trouvé !");
		} else if (indi2 == null) {
			throw new InvalidIdentifiantsException("L'individu " + arguments.get(1) + " n'a pas été trouvé !");
		}
		for(Individu marWith : indi1.marriedWith()) {			
			if (marWith.equals(indi2)) {
				return "Vrai";
			}
		}
		return "Faux";
	}

	@Override
	public void setArgs(String args) {
		this.arguments.clear();
		if (args != null) {
			if (args.indexOf("|") > 0) {				
				this.arguments.add(args.substring(0,args.indexOf("|")));
			}
			this.arguments.add(args.substring(args.indexOf("|")+1,args.length()));
		}
	}

	@Override
	public String getArgs(){
		String res = this.arguments.get(0) + "|"; // La taille de arguments est toujours d'au moins 1
		if (this.arguments.size() > 1) {
			res += this.arguments.get(1);
		}
		return res;
	}

}
