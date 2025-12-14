package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.exceptions.IncorrectSexeException;
import fr.arbre.genealogie.utils.TagTemplate;

/**
 *  Classe pour le TAG SEX du standard GEDOM. Hérite de TagTemplate.
 */
public class Sex extends TagTemplate{

	/**
	 * Contient le sexe.
	 */
	private String value;

	/**
	 * Constructeur de la classe Sex. Défini l'attribut à "UNKNOWN".
	 */
	public Sex() {
		super(1, "SEX");
		this.value = "UNKNOWN";
	}

	@Override
	public void parser(String texte, int cptLigne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));
		if (splited.size() > 2) {
			if (splited.get(2).equals("M") || splited.get(2).equals("F")) {
				this.value = splited.get(2);
			}
		}
	}

	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public String export() {
		return "  ".repeat(this.getNiveau()) + " " + this.getNiveau() + " " + this.getTag() + " " + this.value;
	}

	/**
	 * @return la valeur du sexe
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Définit la valeur du sexe. Doit être "M" ou "F". Si une Exception est retournée, alors "UNKNOWN" est défini.
	 * @param sexe
	 * @throws IncorrectSexeException
	 */
	public void setValue(String sexe) throws IncorrectSexeException {
		if (sexe.equals("M") || sexe.equals("F")) {			
			this.value = sexe;
		} else {
			this.value = "UNKNOWN";
			throw new IncorrectSexeException("Le sexe doit être M ou F. \"UNKNOWN\" a été automatiquement défini.");
		}
	}



}

