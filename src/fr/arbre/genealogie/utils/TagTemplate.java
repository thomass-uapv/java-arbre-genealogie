package fr.arbre.genealogie.utils;

/**
 * Classe abstraite correspondant aux TAGs du standard GEDOM. Tous les TAGs hériteront de cette classe.
 */
public abstract class TagTemplate {

	/**
	 * Correspond au niveau de profondeur de l'arbre (voir Standard GEDOM) où se situe le Tag.
	 */
	private int niveau;

	/**
	 * Le nom du TAG (voir Standard GEDOM)
	 */
	private String tag;

	/**
	 * Correspond à la ligne dans le fichier où apparaît le TAG.
	 */
	private int ligne;

	/**
	 * Constructeur de la classe TagTemplate
	 * @param niveau
	 * @param tag
	 */
	public TagTemplate(int niveau, String tag) {
		super();
		this.niveau = niveau;
		this.tag = tag;
		this.ligne = -1;
	}

	/**
	 * Méthode qui prend en entrée un String qui contient toutes les informations liées au Tag et qui va remplir les différents attributs de l'objet.
	 * @param texte : le String qui contient toutes les informations liées au Tag.
	 * @param cptLigne : Pour savoir à quel ligne dans le fichier on se situe (utile lorsqu'on lève des Exceptions).
	 */
	public abstract void parser(String texte, int cptLigne);

	/**
	 * Renvoie un String du Tag dans le bon format pour l'exportation en fichier GED.
	 * @return String contenant le Tag dans le bon format pour l'exportation en fichier GED.
	 */
	public abstract String export();

	/**
	 * Affiche toutes les informations du Tag.
	 */
	@Override
	public abstract String toString();

	/**
	 * @return le niveau du Tag
	 */
	public int getNiveau() {
		return niveau;
	}

	/**
	 * @return le nom du Tag
	 */
	public String getTag() {
		return tag;
	}


	/**
	 * @return la ligne où apparaît le Tag
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * Définir la ligne où apparaît le Tag
	 * @param ligne : ligne où apparaît le Tag
	 */
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}


}
