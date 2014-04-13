package fr.imie.jdbc.tp7.InterfaceIhm;

import fr.imie.jdbc.tp7.Exception.PersistanceException;

public interface IIhm {

	public abstract void searchPerson() throws PersistanceException;
	public abstract void createPerson() throws PersistanceException;
	public abstract void menu() throws PersistanceException;
	public abstract void updatePerson() throws PersistanceException;
	public abstract void deletePerson() throws PersistanceException;
	
}
