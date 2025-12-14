package fr.arbre.genealogie.shell.commands;

import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.ChildLinkException;
import fr.arbre.genealogie.exceptions.CycleException;
import fr.arbre.genealogie.exceptions.ESException;
import fr.arbre.genealogie.exceptions.FamillyChildLinkException;
import fr.arbre.genealogie.exceptions.FamillyParentLinkException;
import fr.arbre.genealogie.exceptions.IncorrectSexeException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.exceptions.InvalidParameterException;
import fr.arbre.genealogie.exceptions.ParentLinkException;
import fr.arbre.genealogie.io.Parsing;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Command;

/**
 * Classe de la commande import. Hérite de Command.
 */
public class Import extends Command {

	/**
	 * Contient l'instance du Parser général (classe Parsing).
	 */
	private Parsing p;

	/**
	 * Contient l'instance du Shell. 
	 */
	private Shell s;

	/**
	 * Constructeur de la classe Import. Le parser n'étant pas encore instancié est défini à null.
	 * @param s : le shell déjà instancié.
	 */
	public Import(Shell s) {
		super(null, "import <chemin> - Importer dans la base de donnée un fichier GED");
		this.p = null;
		this.s = s;
	}

	@Override
	public String getResult() throws InvalidParameterException, InvalidIdentifiantsException, ArgsException, IncorrectSexeException, CycleException, ESException, ESException {
		if (args == null || args.isBlank()) {
			throw new ArgsException("Veuillez donner un argument");
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
		try {
			this.checkReciprocalLink();
		} catch (ChildLinkException e) {
			s.writeError(e.getMessage() + "\nCréation du lien réciproque...");
			e.getEnfants().add(e.getIndi());
		} catch (ParentLinkException e) {
			s.writeError(e.getMessage() + "\nCréation du lien réciproque...");
			if (e.getSexe().equals("M")) {				
				e.getFam().setPere(e.getIndi());
			} else if (e.getSexe().equals("F")) {
				e.getFam().setMere(e.getIndi());
			}
		} catch (FamillyParentLinkException e) {
			s.writeError(e.getMessage() + "\nCréation du lien réciproque...");
			e.getListeFamilleParent().add(e.getFam());
		} catch (FamillyChildLinkException e) {
			s.writeError(e.getMessage() + "\nCréation du lien réciproque...");
			e.getIndi().setFamille(e.getFam());
		}
		this.checkSexe();
		for (Individu indi : Shell.getBddListInd()) {
			checkCycle(indi, new ArrayList<Individu>());
		}
		return "Importation réussie !";
	}

	/**
	 * Méthode récursive privée qui vérifie si il n'y a pas de cycle dans l'arbre généalogique. Elle renvoie une Exception si elle trouve un cycle.
	 * @param indi
	 * @param visited
	 * @throws CycleException
	 */
	private void checkCycle(Individu indi, ArrayList<Individu> visited) throws CycleException{
		if (indi.getFamille() != null) {
			visited.add(indi);
			for (Individu v : visited) {
				if (indi.getFamille().getMere().equals(v) || indi.getFamille().getPere().equals(v)) {
					throw new CycleException("L'individu @I" + v.getIdentificateur() + "@ est ancêtre de lui-même");
				}
			}

			checkCycle(indi.getFamille().getMere(), visited);
			checkCycle(indi.getFamille().getPere(), visited);
		} 
	}

	/**
	 * Méthode privée qui vérifie si le sexe de l'Individu étant père ou mère est bien respecté. Renvoie une exception si ce n'est pas le cas.
	 * @throws IncorrectSexeException
	 */
	private void checkSexe() throws IncorrectSexeException {
		for (Famille fam : Shell.getBddListFam()) {
			if (fam.getMere().getSexe().getValue().equals("M")) {
				throw new IncorrectSexeException("Sexe de la mère de la famille @F" + fam.getIdentificateur() + "@ incorrect.");
			} else if (fam.getPere().getSexe().getValue().equals("F")) {
				throw new IncorrectSexeException("Sexe du père de la famille @F" + fam.getIdentificateur() + "@ incorrect.");
			}
		}

	}

	/**
	 * Méthode privée qui vérifie si il existe bien un lien réciproque entre un Individu fixé et une Famille fixée. Si ce n'est pas le cas, une Exception est levée.
	 * @throws IncorrectSexeException
	 * @throws ChildLinkException
	 * @throws ParentLinkException
	 * @throws FamillyParentLinkException
	 * @throws FamillyChildLinkException
	 */
	private void checkReciprocalLink() throws IncorrectSexeException, ChildLinkException, ParentLinkException, FamillyParentLinkException, FamillyChildLinkException {
		for(Individu indi : Shell.getBddListInd()) {
			if (indi.getFamille() != null && !indi.getFamille().getEnfants().contains(indi)) {
				throw new ChildLinkException("Le lien @I" + indi.getIdentificateur() + "@ (Enfant) -> @F" + indi.getFamille().getIdentificateur() + "@ n'est pas réciproque.", indi.getFamille().getEnfants(), indi);
			}
			for (Famille fam : indi.getListeFamilleParent()) {
				String sexeIndi = indi.getSexe().getValue();
				if (sexeIndi.equals("M")) {
					if (fam.getPere() == null || !fam.getPere().equals(indi)) {
						throw new ParentLinkException("Le lien @I" + indi.getIdentificateur() + "@ (Père) -> @F" + fam.getIdentificateur() + "@ n'est pas réciproque.", fam, indi, sexeIndi);
					}
				} else if (sexeIndi.equals("F")) {
					if (fam.getMere() == null || !fam.getMere().equals(indi)) {
						throw new ParentLinkException("Le lien @I" + indi.getIdentificateur() + "@ (Mère) -> @F" + fam.getIdentificateur() + "@ n'est pas réciproque.", fam, indi, sexeIndi);
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
				throw new FamillyParentLinkException("Le lien @F" + fam.getPere().getIdentificateur() + "@ -> @I" + fam.getIdentificateur() + "@ (Père) n'est pas réciproque.", fam.getPere().getListeFamilleParent(), fam);
			}
			if (fam.getMere() != null && !(fam.getMere().getListeFamilleParent().contains(fam))) {
				throw new FamillyParentLinkException("Le lien @F" + fam.getMere().getIdentificateur() + "@-> @I" + fam.getIdentificateur() + "@ (Mère) n'est pas réciproque.", fam.getMere().getListeFamilleParent(), fam);
			}
			for (Individu indi : fam.getEnfants()) {
				if (indi.getFamille() == null || !(indi.getFamille().equals(fam))) {
					throw new FamillyChildLinkException("Le lien @F" + fam.getMere().getIdentificateur() + "@ -> @I" + fam.getIdentificateur() + "@ (Enfant) n'est pas réciproque.", fam, indi);
				}
			}
		}
	}


	/**
	 * Renvoie le Parser général.
	 * @return le Parser général.
	 */
	public Parsing getP() {
		return p;
	}
}

