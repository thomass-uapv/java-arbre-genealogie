package fr.arbre.genealogie.utils;

public abstract class Entree extends TagTemplate{
	private int identificateur;

	public Entree(int niveau, String tag, int identificateur) {
		super(niveau, tag);
		this.setIdentificateur(identificateur);
		
	}

	public int getIdentificateur() {
		return identificateur;
	}

	public void setIdentificateur(int identificateur) {
		this.identificateur = identificateur;
	}

}
