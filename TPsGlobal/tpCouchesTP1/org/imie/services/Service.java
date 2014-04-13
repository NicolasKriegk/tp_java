package org.imie.services;

import java.util.ArrayList;
import java.util.List;

import org.imie.DAO.PersonneDAO;
import org.imie.DAO.PromotionDAO;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceServices.IService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.PersistanceException;
import org.imie.transverse.exception.ServiceException;

public class Service implements IService {


	/*	DAO Personne --------------------------------------------------------------------------------------------------*/

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#rechercherPersonne(java.lang.String)
	 */
	public List<Personne> rechercherPersonne(String nomInput) throws ServiceException {
		IPersonneDAO personneDAO = new PersonneDAO();
		List<Personne> personnesListResult = new ArrayList<Personne>();
		try {
			personnesListResult = personneDAO.rechercherPersonne(nomInput);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return personnesListResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#rechercherPersonne(org.imie.transverse.DTO.Personne)
	 */
	public List<Personne> rechercherPersonne(Personne personne) throws ServiceException {
		IPersonneDAO personneDAO = new PersonneDAO();
		List<Personne> personnesListResult = new ArrayList<Personne>();
		try {
			personnesListResult = personneDAO.rechercherPersonne(personne);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return personnesListResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#insertPersonne(org.imie.transverse.DTO.Personne)
	 */
	public Personne insertPersonne(Personne personne) throws ServiceException {
		IPersonneDAO personneDAO = new PersonneDAO();
		Personne personneResult = new Personne();
		try {
			personne = personneDAO.insertPersonne(personne);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return personneResult;
	}
	
	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#deletePersonne(org.imie.transverse.DTO.Personne)
	 */
	public void deletePersonne(Personne personne) throws ServiceException {
		IPersonneDAO personneDAO = new PersonneDAO();
		try {
			personneDAO.deletePersonne(personne);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#updatePersonne(org.imie.transverse.DTO.Personne)
	 */
	public void updatePersonne(Personne personne) throws ServiceException {
		IPersonneDAO personneDAO = new PersonneDAO();
		try {
			personneDAO.updatePersonne(personne);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#dupliquerPersonne(java.lang.Integer, org.imie.transverse.DTO.Personne)
	 */
	public void dupliquerPersonne(Integer nb, Personne personne) throws ServiceException {
		/* duplication de la personne */
		for(int i=0;i<nb;i++){
			insertPersonne(personne/*Source*/);
		}
	}

/*	DAO Promotion --------------------------------------------------------------------------------------------------*/
	
	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#rechercherPromotion(org.imie.transverse.DTO.Promotion)
	 */
	public List<Promotion> rechercherPromotion(Promotion promotion) throws ServiceException {
		IPromotionDAO promotionDAO = new PromotionDAO();
		List<Promotion> promotionListResult = new ArrayList<Promotion>();
		try {
			promotionListResult = promotionDAO.rechercherPromotion(promotion);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return promotionListResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#insertPromotion(org.imie.transverse.DTO.Promotion)
	 */
	public Promotion insertPromotion(Promotion promotion) throws ServiceException {
		IPromotionDAO promotionDAO = new PromotionDAO();
		Promotion promotionResult = new Promotion();
		try {
			promotionResult = promotionDAO.insertPromotion(promotion);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return promotionResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#deletePromotion(org.imie.transverse.DTO.Promotion)
	 */
	public void deletePromotion(Promotion promotion) throws ServiceException {
		IPromotionDAO promotionDAO = new PromotionDAO();
		try {
			promotionDAO.deletePromotion(promotion);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#updatePromotion(org.imie.transverse.DTO.Promotion)
	 */
	public Promotion updatePromotion(Promotion promotion) throws ServiceException {
		IPromotionDAO promotionDAO = new PromotionDAO();
		Promotion promotionResult = new Promotion();
		try {
			promotionResult = promotionDAO.updatePromotion(promotion);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return promotionResult;
	}

	
}
