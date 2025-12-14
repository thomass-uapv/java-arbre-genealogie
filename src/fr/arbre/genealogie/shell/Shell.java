package fr.arbre.genealogie.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArbreGenealogieException;
import fr.arbre.genealogie.exceptions.ESException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.shell.commands.*;


/**
 * Un objet qui va s'occuper d'afficher dans la console les résultats des commandes, permettre à l'utilisateur de rentrer des commandes, etc.
 */
public class Shell {

	/**
	 * Collection qui correspond à la base de données qui contient la référence vers tous les objets Familles qui ont été créés.<br>
	 * Variable statique car on veut pouvoir y accéder dans tout le projet.
	 */
	private static ArrayList<Famille> bddFam = new ArrayList<Famille>();

	/**
	 * Collection qui correspond à la base de données qui contient la référence vers tous les objets Individu qui ont été créé.<br>
	 * Variable statique car on veut pouvoir y accéder dans tout le projet.
	 */
	private static ArrayList<Individu> bddInd = new ArrayList<Individu>();


	/**
	 * Statut du shell : si quit est à Faux alors le shell continue à fonctionner. Permet d'indiquer au shell d'arrêter l'application correctement.
	 */
	private boolean quit;

	/**
	 * Objet qui va récupérer l'entrée de l'utilisateur. C'est un objet de type Reader, on peut lire caractère par caractère avec .read().
	 */
	private InputStreamReader isr;

	/**
	 * Objet qui prend en entrée un Reader qu'on va utiliser pour lire ligne à ligne (.readLine()).
	 */
	private BufferedReader r;

	/**
	 * Constructeur qui va initialiser les attributs de la classe Shell.
	 */
	public Shell() {
		super();
		this.quit = false;
		this.isr = new InputStreamReader(System.in);
		this.r = new BufferedReader(isr);
	}

	/**
	 * Méthode qui contient la boucle pour faire fonctionner l'application. Elle contient également une instance de chaque commande.<br>
	 * Elle va afficher le terminal, vérifier quelles instructions (commandes) sont demandées et afficher les résultats de ces commandes.<br>
	 * Elle affichera également les retours de certaines Exceptions si il y en a.
	 * Elle contient également le fonctionnement de la commande quit.
	 */
	public void start() {
		Echo e = new Echo();
		Help h = new Help();
		Graph g = new Graph();
		Import importation = new Import(this);
		Info info = new Info();
		Child child = new Child();
		Siblings siblings = new Siblings();
		Married married = new Married();
		Famc famc = new Famc();
		Save save = new Save();
		Graph graph = new Graph();

		ArrayList<String> listeCmd = new ArrayList<String>();
		listeCmd.add(h.getDescription());
		listeCmd.add(e.getDescription());
		listeCmd.add(importation.getDescription());
		listeCmd.add(g.getDescription());
		listeCmd.add(info.getDescription());
		listeCmd.add(child.getDescription());
		listeCmd.add(siblings.getDescription());
		listeCmd.add(married.getDescription());
		listeCmd.add(famc.getDescription());
		listeCmd.add(save.getDescription());

		h.setListeCommands(listeCmd);

		while (!this.quit) {
			System.out.print("> ");
			try {
				String in = this.input().trim();

				int marriedCmdIndex = in.toLowerCase().indexOf(" married ");
				if(marriedCmdIndex != -1) {
					String concatArgs = null;
					if (marriedCmdIndex+8 < in.length()) {
						concatArgs = in.substring(0,marriedCmdIndex) + "|" + in.substring(marriedCmdIndex+9);
					}
					married.setArgs(concatArgs);
					System.out.println(married.getResult());
					continue;
				}


				int firstSpace = in.indexOf(" ");
				String command;
				String args;
				if (firstSpace != -1) {
					command = in.substring(0, firstSpace);
					args = in.substring(firstSpace+1);
				} else {
					command = in;
					args = null;
				}

				if (command.equalsIgnoreCase("echo")) {
					e.setArgs(args);
					String res = e.getResult();
					if (res != null) {							
						System.out.println(res);
					}
				} else if (command.equalsIgnoreCase("import")){
					importation.setArgs(args);
					System.out.println(importation.getResult());
				} else if (command.equalsIgnoreCase("info")) {
					info.setArgs(args);
					System.out.println(info.getResult());
				} else if(command.equalsIgnoreCase("child")) {
					child.setArgs(args);
					System.out.println(child.getResult());
				} else if(command.equalsIgnoreCase("siblings")) {
					siblings.setArgs(args);
					System.out.println(siblings.getResult());
				} else if (command.equalsIgnoreCase("famc")){
					famc.setArgs(args);
					System.out.println(famc.getResult());
				} else if (command.equalsIgnoreCase("graph")) {
					graph.setArgs(args);
					System.out.println(graph.getResult());
				} else if (command.equals("export") || command.equalsIgnoreCase("save")) {
					save.setArgs(args);
					System.out.println(save.getResult());
				} else if (command.equals("?") || command.equalsIgnoreCase("help")) {
					System.out.println(h.getResult());
				} else if (command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("stop") || command.equalsIgnoreCase("exit")) {
					System.out.println("Arrêt de l'application");
					this.quit = true;
					try {
						r.close();
						isr.close();
					} catch (IOException e1) {
						throw new ESException("Erreur sur la fermeture du Shell.");
					}
				} else {
					System.err.println("Commande incorrecte ou inexistante...");
				}
			} catch (InvalidIdentifiantsException e1) {
				System.err.println(e1.getMessage());
				System.err.println("Réinitialisation de la base.");
				Shell.clearBddListFam();
				Shell.clearBddListInd();
			} catch (ArbreGenealogieException e1) {
				System.err.println(e1.getMessage());
			}
		}
	}


	/**
	 * Récupère l'entrée écrite par l'utilisateur.
	 * @return Renvoie l'entrée écrite par l'utilisateur
	 * @throws ESException
	 */
	private String input() throws ESException {
		String s;
		try {
			s = this.r.readLine();
			return s;
		} catch (IOException e) {
			throw new ESException("Erreur sur la lecture de l'entrée de l'utilisateur !");
		}
	}

	/**
	 * Méthode statique qui renvoie la base de données des Familles.
	 * @return Renvoie une collection qui contient la liste des Familles
	 */
	public static ArrayList<Famille> getBddListFam() {
		return bddFam;
	}

	/**
	 * Méthode statique qui vide la base de données des Familles.
	 */
	public static void clearBddListFam() {
		bddFam.clear();
	}

	/**
	 * Ajoute une Famille à la base de données.
	 * @param f : Ajoute la Famille f à la collection qui contient la liste des Familles.
	 */
	public static void addBddFam(Famille f) {
		Shell.bddFam.add(f);
	}

	/**
	 * Récupère une Famille dans la base de données.
	 * @param id : identifiant de la famille qu'on souhaite récupérer.
	 * @return Renvoie la Famille avec l'identifiant id ou null si elle n'existe pas.
	 */
	public static Famille getBddFam(int id) {
		for(Famille fam : Shell.getBddListFam()) {
			if (fam.getIdentificateur() == id) {
				return fam;
			}
		}
		return null;
	}

	/**
	 * Méthode statique qui renvoie la base de données des Individus.
	 * @return Renvoie une collection qui contient la liste des Individus.
	 */
	public static ArrayList<Individu> getBddListInd() {
		return bddInd;
	}

	/**
	 * Méthode statique qui vide la base de données des Individus.
	 */
	public static void clearBddListInd() {
		bddInd.clear();
	}

	/**
	 * Ajoute un Individu à la base de données.
	 * @param i : Ajoute l'Individu i à la collection qui contient la liste des Individus.
	 */
	public static void addBddInd(Individu i) {
		Shell.bddInd.add(i);
	}

	/**
	 * Récupère un Individu dans la base de données.
	 * @param id : identifiant de l'Individu qu'on souhaite récupérer.
	 * @return Renvoie l'Individu avec l'identifiant id ou null si il n'existe pas.
	 */
	public static Individu getBddInd(int id) {
		for(Individu indi : Shell.getBddListInd()) {
			if (indi.getIdentificateur() == id) {
				return indi;
			}
		}
		return null;
	}

	/**
	 * Récupère un Individu dans la base de données.
	 * @param nom : Les prénoms et le nom de l'Individu qu'on souhaite récupérer.
	 * @return Renvoie l'Individu avec les prénoms et le nom ou null si il n'existe pas.
	 */
	public static Individu getBddInd(String nom) {
		for(Individu indi : Shell.getBddListInd()) {
			if (indi.getNom().toString().toLowerCase().equals(nom.toLowerCase())) {
				return indi;
			}
		}
		return null;

	}

	/**
	 * Affiche un message d'erreur dans la console.
	 * @param string : Le texte personnalisé de l'erreur.
	 */
	public void writeError(String string) {
		System.err.println(string);
	}

}

