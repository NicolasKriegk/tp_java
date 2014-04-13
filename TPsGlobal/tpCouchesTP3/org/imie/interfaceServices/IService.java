package org.imie.interfaceServices;

import java.util.List;

import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.ServiceException;

public interface IService {

	/* DAO personnes --------------------------------------------------------------------------------------------*/
	/**
	 * @param nomInput
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Personne> rechercherPersonne(String nomInput) throws ServiceException;
	/**
	 * @param personne
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Personne> rechercherPersonne(Personne personne) throws ServiceException;
	/**
	 * @param personne
	 * @return
	 * @throws ServiceException
	 */
	public abstract Personne insertPersonne(Personne personne) throws ServiceException;
	/**
	 * @param personne
	 * @throws ServiceException
	 */
	public abstract void deletePersonne(Personne personne) throws ServiceException;
	/**
	 * @param personne
	 * @throws ServiceException
	 */
	public abstract Personne updatePersonne(Personne personne) throws ServiceException;

	/**
	 * @param nb
	 * @param personne
	 * @throws ServiceException
	 */
	public abstract void dupliquerPersonne(Integer nb, Personne personne) throws ServiceException;

	
	/* DAO promotion --------------------------------------------------------------------------------------------*/
	/**
	 * @param promotion
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Promotion> rechercherPromotion(Promotion promotion) throws ServiceException;
	/**
	 * @param promotion
	 * @return
	 * @throws ServiceException
	 */
	public abstract Promotion insertPromotion(Promotion promotion) throws ServiceException;
	/**
	 * @param promotion
	 * @throws ServiceException
	 */
	public abstract void deletePromotion(Promotion promotion) throws ServiceException;
	/**
	 * @param promotion
	 * @return
	 * @throws ServiceException
	 */
	public abstract Promotion updatePromotion(Promotion promotion) throws ServiceException;

	
}
