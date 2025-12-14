package fr.arbre.genealogie.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.shell.Shell;

/**
 * Classe pour l'exportation de la base de donnée en fichier GED.
 */
public class Export {

	/**
	 * L'objet du fichier une fois ouvert.
	 */
	private File file;

	/**
	 * L'objet pour écrire dans le fichier
	 */
	private FileWriter wr;

	/**
	 * Le chemin d'accès du fichier avec son nom.
	 */
	private String path;

	/**
	 * Constructeur de la classe Export.
	 * @param path : correspond au chemin/fichier où exporter la base de donnée.
	 * @throws IOException
	 */
	public Export(String path) throws IOException {
		super();
		this.path = path;
		if (path.charAt(path.length()-1) == '/' || path.charAt(path.length()-1) == '\\') {
			this.path += "output.ged";
		} else if (path.length() < 5 || !path.substring(path.length()-4).equals(".ged")) {
			this.path += ".ged";
		}
		this.file = new File(this.path);
		this.wr = new FileWriter(file);
	}

	/**
	 * Ecris dans le fichier en appelant les méthodes d'exportation de tous les objets de la base.
	 * @return String correspondant au chemin d'accès où a été sauvegardé le fichier.
	 * @throws IOException
	 */
	public String export() throws IOException {
		String res = "0 HEAD\n";

		for(Individu indi : Shell.getBddListInd()) {
			res += indi.export();
		}
		for (Famille fam : Shell.getBddListFam()) {
			res += fam.export();
		}

		res += "0 TRLR\n";

		wr.write(res);

		wr.close();
		return this.file.getAbsolutePath();
	}

}
