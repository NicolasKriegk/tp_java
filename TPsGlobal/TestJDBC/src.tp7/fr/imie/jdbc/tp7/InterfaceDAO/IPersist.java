package fr.imie.jdbc.tp7.InterfaceDAO;

import java.util.List;

import fr.imie.jdbc.tp7.Personne;
import fr.imie.jdbc.tp7.Exception.PersistanceException;


public interface IPersist {

/* valide jusqu'au TP8
*	public abstract List<Personne> searchPerson(String searchElement) throws PersistanceException;
*/
	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract Personne createPerson(Personne person) throws PersistanceException;
	
	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract List<Personne> searchPerson(Personne person) throws PersistanceException;

	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract Personne updatePerson(Personne person) throws PersistanceException;

	/**
	 * @param person
	 * @throws PersistanceException
	 */
	public abstract void deletePerson(Personne person) throws PersistanceException;
	
}
