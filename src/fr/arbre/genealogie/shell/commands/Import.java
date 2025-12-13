package fr.arbre.genealogie.shell.commands;

import java.io.FileNotFoundException;

import fr.arbre.genealogie.exceptions.ArgsNullException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.exceptions.InvalidParameterException;
import fr.arbre.genealogie.io.Parsing;
import fr.arbre.genealogie.shell.Shell;

public class Import{
	private String args;
	private String description;
	private Parsing p;

	public Import() {
		super();
		this.description = "import <chemin> - Importer dans la base de donnée un fichier GED";
		this.args = null;
		this.p = null;
	}

	public String getResult() throws InvalidParameterException, InvalidIdentifiantsException, ArgsNullException, FileNotFoundException {
		if (args == null) {
			throw new ArgsNullException("Veuillez donner un argument");
		}
		System.out.println("Importation du fichier GED...");
		if (Shell.getBddListFam().size() > 0) {
			Shell.clearBddListFam();
		}
		if (Shell.getBddListInd().size() > 0) {
			Shell.clearBddListInd();
		}
		p = new Parsing();
		p.parser(args);
		return "Importation réussie !";
	}

	public String getDescription() {
		return this.description;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public Parsing getP() {
		return p;
	}
}
