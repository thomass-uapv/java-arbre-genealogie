package fr.arbre.genealogie.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.arbre.genealogie.exceptions.ArgsException;
import fr.arbre.genealogie.exceptions.CycleException;
import fr.arbre.genealogie.exceptions.ESException;
import fr.arbre.genealogie.exceptions.IncorrectSexeException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.exceptions.InvalidParameterException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;

public interface Command {
	public String getResult() throws ArgsException, MissingEntreeException, InvalidParameterException, InvalidIdentifiantsException, FileNotFoundException, IncorrectSexeException, CycleException, IOException, ESException;
	public String getDescription();
	public void setArgs(String args);
	public String getArgs();
}
