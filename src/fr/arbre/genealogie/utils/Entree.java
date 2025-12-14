package fr.arbre.genealogie.utils;

/**
 * Classe abstraite correspondant à des TAGs particuliers du standard GEDOM.<br>
 * Les Tags qui ont un identificateur hériteront de cette classe.
 */
public abstract class Entree extends TagTemplate{

	/**
	 * La valeur de l'identificateur du TAG.
	 */
	private int identificateur;

	/**
	 * Constructeur de la classe Entree
	 * @param niveau : niveau de profondeur de l'arbre (voir Standard GEDOM) où se situe le Tag.
	 * @param tag : le nom du Tag
	 * @param identificateur : l'identificateur
	 */
	public Entree(int niveau, String tag, int identificateur) {
		super(niveau, tag);
		this.setIdentificateur(identificateur);

	}

	/**
	 * @return l'identificateur du Tag
	 */
	public int getIdentificateur() {
		return identificateur;
	}

	/**
	 * Définit l'identificateur du Tag
	 * @param identificateur
	 */
	public void setIdentificateur(int identificateur) {
		this.identificateur = identificateur;
	}

}

