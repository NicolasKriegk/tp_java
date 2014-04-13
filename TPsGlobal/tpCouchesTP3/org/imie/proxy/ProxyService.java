package org.imie.proxy;

import java.util.ArrayList;
import java.util.List;

import org.imie.interfaceServices.IService;
import org.imie.services.Service;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.ServiceException;
import org.imie.transverse.interfaceFactory.IFactory;

public class ProxyService implements IService {

	private IPersonneDAO personneDAO;
	private IPromotionDAO PromotionDAO;
	private Service service;
	
	/**
	 * @param factory
	 */
	public ProxyService(IFactory factory) {
		this.factory = factory;
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#rechercherPersonne(java.lang.String)
	 */
	public List<Personne> rechercherPersonne(String nomInput)
			throws ServiceException {
		List<Personne> personnesListResult = new ArrayList<Personne>();
		personnesListResult = service.rechercherPersonne(nomInput);
		return personnesListResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#rechercherPersonne(org.imie.transverse.DTO.Personne)
	 */
	public List<Personne> rechercherPersonne(Personne personne)
			throws ServiceException {
		List<Personne> personnesListResult = new ArrayList<Personne>();
		personnesListResult = service.rechercherPersonne(personne);
		return personnesListResult;
	}

	
	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#insertPersonne(org.imie.transverse.DTO.Personne)
	 */
	public Personne insertPersonne(Personne personne) throws ServiceException {
		Personne personneResult = new Personne();
		personneResult = service.insertPersonne(personne);
		return personneResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#deletePersonne(org.imie.transverse.DTO.Personne)
	 */
	public void deletePersonne(Personne personne) throws ServiceException {
		service.deletePersonne(personne);
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#updatePersonne(org.imie.transverse.DTO.Personne)
	 */
	public Personne updatePersonne(Personne personne) throws ServiceException {
		Personne personneResult = new Personne();
		personneResult = service.updatePersonne(personne);
		return personneResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#dupliquerPersonne(java.lang.Integer, org.imie.transverse.DTO.Personne)
	 */
	public void dupliquerPersonne(Integer nb, Personne personne)
			throws ServiceException {
		service.dupliquerPersonne(nb, personne);
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#rechercherPromotion(org.imie.transverse.DTO.Promotion)
	 */
	public List<Promotion> rechercherPromotion(Promotion promotion)
			throws ServiceException {
		List<Promotion> promotionListResult = new ArrayList<Promotion>();
		promotionListResult = service.rechercherPromotion(promotion);
		return promotionListResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#insertPromotion(org.imie.transverse.DTO.Promotion)
	 */
	public Promotion insertPromotion(Promotion promotion)
			throws ServiceException {
		Promotion promotionResult = new Promotion();
		promotionResult = service.insertPromotion(promotion);
		return promotionResult;
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#deletePromotion(org.imie.transverse.DTO.Promotion)
	 */
	public void deletePromotion(Promotion promotion) throws ServiceException {
		service.deletePromotion(promotion);
	}

	/* (non-Javadoc)
	 * @see org.imie.interfaceServices.IService#updatePromotion(org.imie.transverse.DTO.Promotion)
	 */
	public Promotion updatePromotion(Promotion promotion)
			throws ServiceException {
		Promotion promotionResult = new Promotion();
		promotionResult = service.updatePromotion(promotion);
		return promotionResult;
	}

}
