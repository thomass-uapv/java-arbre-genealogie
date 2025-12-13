package fr.arbre.genealogie.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.exceptions.InvalidParameterException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Entree;

public class Parsing{
	private ArrayList<Integer> individus_indice;
	private ArrayList<Integer> familles_indice;
	private int cpt_ligne;


	public Parsing() {
		this.individus_indice = new ArrayList<Integer>();
		this.familles_indice = new ArrayList<Integer>();
		this.cpt_ligne = 0;
	}



	public void parser(String filepath) throws InvalidParameterException, InvalidIdentifiantsException, FileNotFoundException {
		if (filepath.isEmpty()) {
			throw new InvalidParameterException("Paramètre filepath vide.");
		} else {
			FileReader fr = null;
			BufferedReader content_file = null;
			try {
				if (filepath.charAt(0) == '"'){
					filepath = filepath.substring(1);
				}
				if (filepath.charAt(filepath.length()-1) == '"') {
					filepath = filepath.substring(0,filepath.length()-1);
				}
				fr = new FileReader(filepath);
				content_file = new BufferedReader(fr);

				String line = content_file.readLine();
				cpt_ligne++;
				Entree current = null;
				while (!line.trim().equals("0 TRLR")) {
					String[] elems;
					if (line.trim().equals("0 HEAD")) {
						line = content_file.readLine();
						cpt_ligne++;
						elems = line.trim().split(" ");
						while (!elems[0].equals("0")) {
							line = content_file.readLine();
							cpt_ligne++;
							elems = line.trim().split(" ");
						}
					}
					elems = line.trim().split(" ");
					if (elems[0].equals("0")) {
						current = null;
						int id;
						if (elems[1].substring(0,2).equals("@I")) {
							id = Integer.parseInt(line.trim().split(" ")[1].substring(2,line.trim().split(" ")[1].length()-1));
							current = Shell.getBddInd(id);
							if (current == null) {
								current = new Individu(id);
								Shell.addBddInd((Individu) current);
							}
							current.setLigne(cpt_ligne);
							individus_indice.add(id);
						} else if (elems[1].substring(0,2).equals("@F")){
							id = Integer.parseInt(line.trim().split(" ")[1].substring(2,line.trim().split(" ")[1].length()-1));
							current = Shell.getBddFam(id);
							if (current == null) {
								current = new Famille(id);
								Shell.addBddFam((Famille) current);
							}
							current.setLigne(cpt_ligne);
							familles_indice.add(id);
						}
						line = content_file.readLine();
						cpt_ligne++;
					} else {
						if (current != null) {							
							String bloc = line.trim() + "\n";
							int cpt_ligne_debut_bloc = cpt_ligne;
							line = content_file.readLine();
							cpt_ligne++;
							while (!line.trim().split(" ")[0].equals("0")) {
								bloc += line.trim() + "\n";
								line = content_file.readLine();
								cpt_ligne++;
							}
							current.parser(bloc, cpt_ligne_debut_bloc);
						} else {
							line = content_file.readLine();
							cpt_ligne++;
						}
					}

				}

				Collections.sort(this.individus_indice);
				Collections.sort(this.familles_indice);

				for (int i = 0; i < this.individus_indice.size(); i++) {
					if (this.individus_indice.get(i) != i+1) {
						throw new InvalidIdentifiantsException("Identifiants d'individus incorrects.");
					}
				}
				for (int i = 0; i < this.familles_indice.size(); i++) {
					if (this.familles_indice.get(i) != i+1) {
						throw new InvalidIdentifiantsException("Identifiants de familles incorrects.");
					}
				}
			
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
					try {
						content_file.close();
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}

		}

	}

}
