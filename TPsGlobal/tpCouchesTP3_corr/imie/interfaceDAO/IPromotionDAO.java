package org.imie.interfaceDAO;

import java.sql.Connection;
import java.util.List;

import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.PersistanceException;

/**
 * @author imie
 * 
 */
public interface IPromotionDAO {


	
	/**
	 * @param Promotion
	 * @return
	 * @throws PersistanceException
	 */
	public abstract Promotion insertPromotion(Promotion promotion)
			throws PersistanceException;

	/**
	 * @param Promotion
	 * @return
	 * @throws PersistanceException
	 */
	public abstract void deletePromotion(Promotion promotion)
			throws PersistanceException;

	/**
	 * @param Promotion
	 * @return 
	 * @throws PersistanceException 
	 */
	public abstract List<Promotion> rechercherPromotion(Promotion promotion) throws PersistanceException;


	/**
	 * @param PromotionToUpdate
	 * @return 
	 * @throws PersistanceException 
	 */
	public abstract Promotion updatePromotion(Promotion promotionToUpdate) throws PersistanceException;

	/**
	 * @param connection
	 */
	public void setConnection(Connection connection); 


}