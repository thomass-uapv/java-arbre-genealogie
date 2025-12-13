package fr.arbre.genealogie.io;

import java.io.FileWriter;
import java.io.IOException;

import fr.arbre.genealogie.entree.Famille;
import fr.arbre.genealogie.entree.Individu;
import fr.arbre.genealogie.shell.Shell;

public class Export {
	private FileWriter wr;
	private String path;

	public Export(String path) throws IOException {
		super();
		this.path = path;
		if (path.charAt(path.length()-1) == '/' || path.charAt(path.length()-1) == '\\') {
			this.path += "output.ged";
		}
		this.wr = new FileWriter(this.path);
	}
	
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
		return this.path;
	}
	
}
