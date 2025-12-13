package fr.arbre.genealogie.shell.commands;

import java.io.IOException;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsNullException;
import fr.arbre.genealogie.exceptions.CycleException;
import fr.arbre.genealogie.exceptions.IncorrectSexeException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.exceptions.InvalidParameterException;
import fr.arbre.genealogie.exceptions.MissingReciprocalLinkException;
import fr.arbre.genealogie.io.Parsing;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

public class Import implements Command {
	private String args;
	private String description;
	private Parsing p;

	public Import() {
		super();
		this.description = "import <chemin> - Importer dans la base de donnée un fichier GED";
		this.args = null;
		this.p = null;
	}

	public String getResult() throws InvalidParameterException, InvalidIdentifiantsException, ArgsNullException, IncorrectSexeException, CycleException, IOException {
		if (args == null || args.isBlank()) {
			throw new ArgsNullException("Veuillez donner un argument");
		}
		System.out.println("Importation du fichier GED...");
		if (Shell.getBddListFam().size() > 0) {
			Shell.clearBddListFam();
		}
		if (Shell.getBddListInd().size() > 0) {
			Shell.clearBddListInd();
		}
		this.p = new Parsing();
		this.p.parser(args);
		this.checkReciprocalLink();
		this.checkSexe();
		for (Individu indi : Shell.getBddListInd()) {			
			checkCycle(indi);
		}
		return "Importation réussie !";
	}

	private void checkCycle(Individu indi) throws CycleException{
		if (indi.getFamille() != null) {
			if (indi.getFamille().getMere().equals(indi) || indi.getFamille().getPere().equals(indi)) {
				throw new CycleException("L'individu @I" + indi.getIdentificateur() + "@ est ancêtre de lui-même");
			} else {
				checkCycle(indi.getFamille().getMere());
				checkCycle(indi.getFamille().getPere());
			}
		} 
	}

	private void checkSexe() throws IncorrectSexeException {
		for (Famille fam : Shell.getBddListFam()) {
			if (fam.getMere().getSexe().getValue().equals("M")) {
				throw new IncorrectSexeException("Sexe de la mère de la famille @F" + fam.getIdentificateur() + "@ incorrect.");
			} else if (fam.getPere().getSexe().getValue().equals("F")) {
				throw new IncorrectSexeException("Sexe du père de la famille @F" + fam.getIdentificateur() + "@ incorrect.");
			}
		}

	}

	private void checkReciprocalLink() throws IncorrectSexeException {
		for(Individu indi : Shell.getBddListInd()) {
			if (indi.getFamille() != null && !indi.getFamille().getEnfants().contains(indi)) {
				MissingReciprocalLinkException e = new MissingReciprocalLinkException("Le lien @I" + indi.getIdentificateur() + "@ (Enfant) -> @F" + indi.getFamille().getIdentificateur() + "@ n'est pas réciproque. Création du lien réciproque...");
				System.err.println(e.getMessage());
				indi.getFamille().getEnfants().add(indi);
			}
			for (Famille fam : indi.getListeFamilleParent()) {
				String sexeIndi = indi.getSexe().getValue();
				if (sexeIndi.equals("M")) {
					if (fam.getPere() == null || !fam.getPere().equals(indi)) {
						MissingReciprocalLinkException e = new MissingReciprocalLinkException("Le lien @I" + indi.getIdentificateur() + "@ (Père) -> @F" + fam.getIdentificateur() + "@ n'est pas réciproque. Création du lien réciproque...");
						System.err.println(e.getMessage());
						fam.setPere(indi);
					}
				} else if (sexeIndi.equals("F")) {
					if (fam.getMere() == null || !fam.getMere().equals(indi)) {
						MissingReciprocalLinkException e = new MissingReciprocalLinkException("Le lien @I" + indi.getIdentificateur() + "@ (Mère) -> @F" + fam.getIdentificateur() + "@ n'est pas réciproque. Création du lien réciproque...");
						System.err.println(e.getMessage());
						fam.setMere(indi);
					}
				} else {
					if (fam.getPere() != null && fam.getMere() == null) {
						fam.setMere(indi);
					} else if (fam.getMere() != null && fam.getPere() == null) {
						fam.setPere(indi);
					} else if (fam.getMere() == null && fam.getPere() == null){						
						throw new IncorrectSexeException("Impossible de déterminer si l'individu est père ou mère.");
					}
				}				
			}
		}

	for (Famille fam : Shell.getBddListFam()) {
		if (fam.getPere() != null && !(fam.getPere().getListeFamilleParent().contains(fam))) {
			MissingReciprocalLinkException e = new MissingReciprocalLinkException("Le lien @F" + fam.getPere().getIdentificateur() + "@ -> @I" + fam.getIdentificateur() + "@ (Père) n'est pas réciproque. Création du lien réciproque...");
			System.err.println(e.getMessage());
			fam.getPere().getListeFamilleParent().add(fam);
		}
		if (fam.getMere() != null && !(fam.getMere().getListeFamilleParent().contains(fam))) {
			MissingReciprocalLinkException e = new MissingReciprocalLinkException("Le lien @F" + fam.getMere().getIdentificateur() + "@-> @I" + fam.getIdentificateur() + "@ (Mère) n'est pas réciproque. Création du lien réciproque...");
			System.err.println(e.getMessage());
			fam.getMere().getListeFamilleParent().add(fam);
		}
		for (Individu indi : fam.getEnfants()) {
			if (indi.getFamille() == null || !(indi.getFamille().equals(fam))) {
				MissingReciprocalLinkException e = new MissingReciprocalLinkException("Le lien @F" + fam.getMere().getIdentificateur() + "@ -> @I" + fam.getIdentificateur() + "@ (Enfant) n'est pas réciproque. Création du lien réciproque...");
				System.err.println(e.getMessage());
				indi.setFamille(fam);
			}
		}
	}
}


public String getDescription() {
	return this.description;
}

public void setArgs(String args) {
	this.args = args;
}

public String getArgs() {
	return args;
}

public Parsing getP() {
	return p;
}
}
