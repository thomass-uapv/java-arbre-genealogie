package fr.arbre.genealogie.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.exceptions.ESException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.exceptions.InvalidParameterException;
import fr.arbre.genealogie.shell.Shell;
import fr.arbre.genealogie.utils.Entree;

public class Parsing{
	private ArrayList<Integer> individusIndice;
	private ArrayList<Integer> famillesIndice;
	private int cptLigne;


	public Parsing() {
		this.individusIndice = new ArrayList<Integer>();
		this.famillesIndice = new ArrayList<Integer>();
		this.cptLigne = 0;
	}



	public void parser(String filepath) throws InvalidParameterException, InvalidIdentifiantsException, FileNotFoundException, ESException {
		if (filepath.isEmpty()) {
			throw new InvalidParameterException("Paramètre filepath vide.");
		} else {
			FileReader fr = null;
			BufferedReader contentFile = null;
			try {
				if (filepath.charAt(0) == '"'){
					filepath = filepath.substring(1);
				}
				if (filepath.charAt(filepath.length()-1) == '"') {
					filepath = filepath.substring(0,filepath.length()-1);
				}
				fr = new FileReader(filepath);
				contentFile = new BufferedReader(fr);

				String line = contentFile.readLine();
				cptLigne++;
				Entree current = null;
				while (!line.trim().equals("0 TRLR")) {
					String[] elems;
					if (line.trim().equals("0 HEAD")) {
						line = contentFile.readLine();
						cptLigne++;
						elems = line.trim().split(" ");
						while (!elems[0].equals("0")) {
							line = contentFile.readLine();
							cptLigne++;
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
							current.setLigne(cptLigne);
							individusIndice.add(id);
						} else if (elems[1].substring(0,2).equals("@F")){
							id = Integer.parseInt(line.trim().split(" ")[1].substring(2,line.trim().split(" ")[1].length()-1));
							current = Shell.getBddFam(id);
							if (current == null) {
								current = new Famille(id);
								Shell.addBddFam((Famille) current);
							}
							current.setLigne(cptLigne);
							famillesIndice.add(id);
						}
						line = contentFile.readLine();
						cptLigne++;
					} else {
						if (current != null) {							
							String bloc = line.trim() + "\n";
							int cptLigneDebutBloc = cptLigne;
							line = contentFile.readLine();
							cptLigne++;
							while (!line.trim().split(" ")[0].equals("0")) {
								bloc += line.trim() + "\n";
								line = contentFile.readLine();
								cptLigne++;
							}
							current.parser(bloc, cptLigneDebutBloc);
						} else {
							line = contentFile.readLine();
							cptLigne++;
						}
					}

				}

				Collections.sort(this.individusIndice);
				Collections.sort(this.famillesIndice);

				for (int i = 0; i < this.individusIndice.size(); i++) {
					if (this.individusIndice.get(i) != i+1) {
						throw new InvalidIdentifiantsException("Identifiants d'individus incorrects.");
					}
				}
				for (int i = 0; i < this.famillesIndice.size(); i++) {
					if (this.famillesIndice.get(i) != i+1) {
						throw new InvalidIdentifiantsException("Identifiants de familles incorrects.");
					}
				}
			
			} catch (IOException e) {
				System.err.println(e.getMessage());
				throw new ESException("L'importation a écouhé");
			} finally {
					try {
						if (contentFile != null) {							
							contentFile.close();
						}
						if (fr != null) {							
							fr.close();
						}
					} catch (IOException e) {
						System.err.println(e.getMessage());
						throw new ESException("La fermeture des variables ont échoué");
					}
			}

		}

	}

}
