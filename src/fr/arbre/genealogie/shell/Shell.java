package fr.arbre.genealogie.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ArbreGenalogieException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.shell.commands.*;


public class Shell {
	private static ArrayList<Famille> bddFam = new ArrayList<Famille>();
	private static ArrayList<Individu> bddInd = new ArrayList<Individu>();
	private boolean quit;
	private InputStreamReader isr;
	private BufferedReader r;

	public Shell() {
		super();
		this.quit = false;
		this.isr = new InputStreamReader(System.in);
		this.r = new BufferedReader(isr);
	}

	public void start() {
		Echo e = new Echo();
		Help h = new Help();
		Graph g = new Graph();
		Import importation = new Import();
		Info info = new Info();
		Child child = new Child();
		Siblings siblings = new Siblings();
		Married married = new Married();
		Famc famc = new Famc();
		Save save = new Save();
		Graph graph = new Graph();

		ArrayList<String> liste_cmd = new ArrayList<String>();
		liste_cmd.add(h.getDescription());
		liste_cmd.add(e.getDescription());
		liste_cmd.add(importation.getDescription());
		liste_cmd.add(g.getDescription());
		liste_cmd.add(info.getDescription());
		liste_cmd.add(child.getDescription());
		liste_cmd.add(siblings.getDescription());
		liste_cmd.add(married.getDescription());
		liste_cmd.add(famc.getDescription());
		liste_cmd.add(save.getDescription());
		liste_cmd.add(graph.getDescription());

		h.setListe_command(liste_cmd);

		while (!this.quit) {
			System.out.print("> ");
			try {
				String in = this.input();

				int married_cmd_index = in.toLowerCase().indexOf(" married ");
				if(married_cmd_index != -1) {
					String concat_args = null;
					if (married_cmd_index+8 < in.length()) {
						concat_args = in.substring(0,married_cmd_index) + "|" + in.substring(married_cmd_index+9,in.length());
					}
					married.setArgs(concat_args);
					System.out.println(married.getResult());
					continue;
				}


				int first_space = in.indexOf(" ");
				String command;
				String args;
				if (first_space != -1) {
					command = in.substring(0, first_space).trim();
					args = in.substring(first_space+1);
				} else {
					command = in.trim();
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
					r.close();
					isr.close();
				} else {
					System.err.println("Commande incorrecte ou inexistante...");
				}
			} catch (IOException err) {
				System.err.println(err.getMessage());
			} catch (InvalidIdentifiantsException e1) {
				System.err.println(e1.getMessage());
				System.err.println("Réinitialisation de la base.");
				Shell.clearBddListFam();
				Shell.clearBddListInd();
			} catch (ArbreGenalogieException e1) {
				System.err.println(e1.getMessage());
			}
		}
	}

	public String input() throws IOException {
		String s = this.r.readLine();
		return s;
	}

	public static ArrayList<Famille> getBddListFam() {
		return bddFam;
	}

	public static void clearBddListFam() {
		bddFam.clear();
	}

	public static void addBddFam(Famille e) {
		Shell.bddFam.add(e);
	}

	public static Famille getBddFam(int id) {
		for(Famille fam : Shell.getBddListFam()) {
			if (fam.getIdentificateur() == id) {
				return fam;
			}
		}
		return null;
	}

	public static ArrayList<Individu> getBddListInd() {
		return bddInd;
	}

	public static void clearBddListInd() {
		bddInd.clear();
	}

	public static void addBddInd(Individu e) {
		Shell.bddInd.add(e);
	}

	public static Individu getBddInd(int id) {
		for(Individu indi : Shell.getBddListInd()) {
			if (indi.getIdentificateur() == id) {
				return indi;
			}
		}
		return null;
	}

	public static Individu getBddInd(String nom) {
		for(Individu indi : Shell.getBddListInd()) {
			if (indi.getNom().toString().equals(nom)) {
				return indi;
			}
		}
		return null;

	}

}
