package fr.imie.jdbc.InterfaceDAO;

import java.util.List;

import fr.imie.jdbc.DTO.PersonDTO;
import fr.imie.jdbc.Exception.PersistanceException;


public interface IPersonDAO {

/* valide jusqu'au TP8
*	public abstract List<Personne> searchPerson(String searchElement) throws PersistanceException;
*/
	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract PersonDTO createPerson(PersonDTO person) throws PersistanceException;
	
	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract List<PersonDTO> searchPerson(PersonDTO person) throws PersistanceException;

	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract PersonDTO updatePerson(PersonDTO person) throws PersistanceException;

	/**
	 * @param person
	 * @throws PersistanceException
	 */
	public abstract void deletePerson(PersonDTO person) throws PersistanceException;
	
}
