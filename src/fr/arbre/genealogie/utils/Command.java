package fr.arbre.genealogie.utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import fr.arbre.genealogie.exceptions.ArgsNullException;
import fr.arbre.genealogie.exceptions.CycleException;
import fr.arbre.genealogie.exceptions.IncorrectSexeException;
import fr.arbre.genealogie.exceptions.InvalidIdentifiantsException;
import fr.arbre.genealogie.exceptions.InvalidParameterException;
import fr.arbre.genealogie.exceptions.MissingEntreeException;

public interface Command {
	public String getResult() throws ArgsNullException, MissingEntreeException, InvalidParameterException, InvalidIdentifiantsException, FileNotFoundException, IncorrectSexeException, CycleException, IOException;
	public String getDescription();
	public void setArgs(String args);
	public String getArgs();
}
