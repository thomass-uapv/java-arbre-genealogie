package fr.arbre.genealogie.utils;

public abstract class TagTemplate {
	private int niveau;
	private String tag;
	private int ligne;
	
	public TagTemplate(int niveau, String tag) {
		super();
		this.niveau = niveau;
		this.tag = tag;
		this.ligne = -1;
	}
	
	public abstract void parser(String texte, int cpt_ligne);

	public int getNiveau() {
		return niveau;
	}

	public String getTag() {
		return tag;
	}


	public int getLigne() {
		return ligne;
	}
	
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	
	
}
