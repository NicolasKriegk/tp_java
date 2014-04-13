package org.imie.interfaceservice;

import java.util.List;

import model.Personne;
import model.Promotion;

import org.imie.Transaction.IJDBCCompatible;
import org.imie.transverse.exception.ServiceException;

public interface IGestionEcoleService extends IJDBCCompatible {

	/**
	 * @param nomInput
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Personne> rechercherPersonne(String nomInput)
			throws ServiceException;

	/**
	 * @param personne
	 * @return
	 * @throws ServiceException
	 */
	public abstract Personne insertPersonne(Personne personne)
			throws ServiceException;

	/**
	 * @param personne
	 * @return
	 * @throws ServiceException
	 */
	public abstract void deletePersonne(Personne personne)
			throws ServiceException;

	/**
	 * @param personne
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Personne> rechercherPersonne(Personne personne)
			throws ServiceException;

	/**
	 * @param personneToUpdate
	 * @return
	 * @throws ServiceException
	 */
	public abstract Personne updatePersonne(Personne personneToUpdate)
			throws ServiceException;

	/**
	 * @param Promotion
	 * @return
	 * @throws ServiceException
	 */
	public abstract Promotion insertPromotion(Promotion promotion)
			throws ServiceException;

	/**
	 * @param Promotion
	 * @return
	 * @throws ServiceException
	 */
	public abstract void deletePromotion(Promotion promotion)
			throws ServiceException;

	/**
	 * @param Promotion
	 * @return
	 * @throws ServiceException
	 */
	public abstract List<Promotion> rechercherPromotion(Promotion promotion)
			throws ServiceException;

	/**
	 * @param PromotionToUpdate
	 * @return
	 * @throws ServiceException
	 */
	public abstract Promotion updatePromotion(Promotion promotionToUpdate)
			throws ServiceException;

	/**
	 * @param personneToDuplicate
	 * @param i
	 * @throws ServiceException
	 */
	void duplicatePersonne(Personne personneToDuplicate, Integer nbFois)
			throws ServiceException;

	/**
	 * @param personne
	 * @return
	 */
	public abstract Personne verifierAuthPersonne(Personne personne) throws ServiceException;

}
