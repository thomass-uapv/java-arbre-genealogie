package fr.arbre.genealogie.tags;

import java.util.ArrayList;
import java.util.Arrays;

import fr.arbre.genealogie.utils.TagTemplate;

public abstract class EventValue extends TagTemplate {
	
	private String eventValue;

	public EventValue(int niveau, String tag) {
		super(niveau, tag);
		this.eventValue = "UNKNOWN";
	}
	
	@Override
	public void parser(String texte, int cpt_ligne) {
		ArrayList<String> splited = new ArrayList<String>(Arrays.asList(texte.split(" ")));

		if (splited.size() > 2) {
			this.setEventValue(texte.substring(texte.indexOf(this.getTag()) + this.getTag().length() + 1,texte.length()));
		}
	}

	public String getEventValue() {
		return eventValue;
	}

	public void setEventValue(String value) {
		this.eventValue = value;
	}

}
