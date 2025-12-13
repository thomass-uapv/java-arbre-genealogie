package fr.arbre.genealogie.utils;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.tags.Date;
import fr.arbre.genealogie.tags.Place;

public abstract class Event extends TagTemplate{

	private Date date;
	private Place lieu;

	public Event(int niveau, String tag) {
		super(niveau, tag);
		this.date = new Date();
		this.lieu = new Place();
	}

	@Override
	public void parser(String texte, int cptLigne) {
		TagTemplate current = null;
		ArrayList<String> lines = new ArrayList<String>(Arrays.asList(texte.split("\n")));
		int i = 0;
		while (i < lines.size()) {
			String[] splited = lines.get(i).split(" ");
			if (splited[0].equals(Integer.toString(this.getNiveau() + 1))) {
				if (splited[1].equals(this.date.getTag())) {
					current = this.date;
				} else if(splited[1].equals(this.lieu.getTag())) {
					current = this.lieu;
				}
				current.parser(lines.get(i).trim(), cptLigne+i);
				i++;
			} else {
				if (current != null) {
					String bloc = lines.get(i++).trim() + "\n";
					int cptLigneDebutBloc = cptLigne+i-1;
					while (i < lines.size() && !lines.get(i).trim().split(" ")[0].equals(Integer.toString(this.getNiveau() + 1))) {
						bloc += lines.get(i++).trim() + "\n";
					}
					current.parser(bloc, cptLigneDebutBloc);
				} else {
					i++;
				}
			}
		}

	}

	@Override
	public String export() {
		return "  ".repeat(this.getNiveau()) + " " + this.getNiveau() + " " + this.getTag() + "\n" +
				this.date.export() + "\n" +
				this.lieu.export();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Place getLieu() {
		return lieu;
	}

	public void setLieu(Place lieu) {
		this.lieu = lieu;
	}

}
