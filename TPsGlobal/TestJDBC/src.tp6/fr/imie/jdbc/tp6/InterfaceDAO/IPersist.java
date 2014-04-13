package fr.imie.jdbc.tp6.InterfaceDAO;

import java.util.List;

import fr.imie.jdbc.tp6.Personne;
import fr.imie.jdbc.tp6.Exception.PersistanceException;


public interface IPersist {

	public abstract List<Personne> personSearch(String searchElement) throws PersistanceException;
	
}
