package org.imie.interfaceDAO;

import java.util.List;

import org.imie.transverse.DTO.Personne;
import org.imie.transverse.exception.PersistanceException;

/**
 * @author imie
 * 
 */
public interface IPersonneDAO {

	/**
	 * @param nomInput
	 * @return
	 * @throws PersistanceException
	 */
	public abstract List<Personne> rechercherPersonne(String nomInput)
			throws PersistanceException;

	
	/**
	 * @param personne
	 * @return
	 * @throws PersistanceException
	 */
	public abstract Personne insertPersonne(Personne personne)
			throws PersistanceException;

	/**
	 * @param personne
	 * @return
	 * @throws PersistanceException
	 */
	public abstract void deletePersonne(Personne personne)
			throws PersistanceException;

	/**
	 * @param personne
	 * @return 
	 * @throws PersistanceException 
	 */
	public abstract List<Personne> rechercherPersonne(Personne personne) throws PersistanceException;


	/**
	 * @param personneToUpdate
	 * @return 
	 * @throws PersistanceException 
	 */
	public abstract Personne updatePersonne(Personne personneToUpdate) throws PersistanceException;




}