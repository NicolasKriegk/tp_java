package fr.imie.jdbc.InterfaceDAO;

import java.util.List;

import fr.imie.jdbc.Exception.PersistanceException;

public interface ICrudDAO<DTOElement> {

	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract DTOElement create(DTOElement dtoElement) throws PersistanceException;
	
	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract List<DTOElement> search(DTOElement dtoElement) throws PersistanceException;

	/**
	 * @param person
	 * @return
	 * @throws PersistanceException
	 */
	public abstract DTOElement update(DTOElement dtoElement) throws PersistanceException;

	/**
	 * @param person
	 * @throws PersistanceException
	 */
	public abstract void delete(DTOElement dtoElement) throws PersistanceException;
	
}
