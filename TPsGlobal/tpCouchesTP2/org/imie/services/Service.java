package org.imie.services;

import java.util.ArrayList;
import java.util.List;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceServices.IService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.PersistanceException;
import org.imie.transverse.exception.ServiceException;
import org.imie.transverse.factory.Factory;
import org.imie.transverse.interfaceFactory.IFactory;

public class Service implements IService {

	private IFactory factory;
	
	public Service(IFactory factory) {
		this.factory = factory;
		// TODO Auto-generated constructor stub
	}

	
	/*	DAO Personne --------------------------------------------------------------------------------------------------*/

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#rechercherPersonne(java.lang.String)
	 */
	public List<Personne> rechercherPersonne(String nomInput) throws ServiceException {
		IPersonneDAO personneDAO = factory.createPersonDAO();
		List<Personne> personnesListResult = new ArrayList<Personne>();
		try {
			personnesListResult = personneDAO.rechercherPersonne(nomInput);
			for (Personne personneFound : personnesListResult) {
				personneFound = achievePersonne(personneFound);
			}
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return personnesListResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#rechercherPersonne(org.imie.transverse.DTO.Personne)
	 */
	public List<Personne> rechercherPersonne(Personne personne) throws ServiceException {
		IPersonneDAO personneDAO = factory.createPersonDAO();
		List<Personne> personnesListResult = new ArrayList<Personne>();
		try {
			personnesListResult = personneDAO.rechercherPersonne(personne);
			for (Personne personneFound : personnesListResult) {
				personneFound = achievePersonne(personneFound);
			}
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return personnesListResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#insertPersonne(org.imie.transverse.DTO.Personne)
	 */
	public Personne insertPersonne(Personne personne) throws ServiceException {
		IPersonneDAO personneDAO = factory.createPersonDAO();
		Personne personneResult = new Personne();
		try {
			personneResult = personneDAO.insertPersonne(personne);
			personneResult = achievePersonne(personneResult);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return personneResult;
	}
	
	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IServiceDAO#deletePersonne(org.imie.transverse.DTO.Personne)
	 */
	public void deletePersonne(Personne personne) throws ServiceException {
		IPersonneDAO personneDAO = factory.createPersonDAO();
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
		IPersonneDAO personneDAO = factory.createPersonDAO();
		Personne personneResult = new Personne();
		try {
			personneResult = personneDAO.updatePersonne(personne);
			personneResult = achievePersonne(personneResult);
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
		IPromotionDAO promotionDAO = factory.createPromotionDAO();
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
		IPromotionDAO promotionDAO = factory.createPromotionDAO();
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
		IPromotionDAO promotionDAO = factory.createPromotionDAO();
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
		IPromotionDAO promotionDAO = factory.createPromotionDAO();
		Promotion promotionResult = new Promotion();
		try {
			promotionResult = promotionDAO.updatePromotion(promotion);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return promotionResult;
	}

	
	/**
	 * @param personne
	 * @return
	 * @throws PersistanceException
	 */
	private Personne achievePersonne(Personne personne)
			throws PersistanceException {

		IPromotionDAO promotionDAO = factory.createPromotionDAO();
		if (personne.getPromotion() != null) {
			List<Promotion> promotions = promotionDAO
					.rechercherPromotion(personne.getPromotion());
			if (promotions.size() > 0) {
				personne.setPromotion(promotions.get(0));
			}
		}
		return personne;
	}

}
