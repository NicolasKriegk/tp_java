package org.imie.service;

import java.sql.Connection;
import java.util.List;

import org.imie.DAO.PersonneDAO;
import org.imie.Transaction.IJDBCCompatible;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;
import org.imie.transverse.exception.ServiceException;
import org.imie.transverse.factory.AbstractFactory;

public class GestionEcoleService implements IGestionEcoleService {

	private IPersonneDAO personneDAO = null;
	private IPromotionDAO promotionDAO = null;
	private Connection connection;

	/**
	 * @param factory
	 */
	public GestionEcoleService(AbstractFactory factory) {
		personneDAO = factory.createPersonneDAO();
		promotionDAO = factory.createPromotionDAO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.interfaceservice.IGestionEcoleService#rechercherPersonne(java
	 * .lang.String)
	 */
	@Override
	public List<Personne> rechercherPersonne(String nomInput)
			throws ServiceException {
		List<Personne> retour;
		try {
			retour = personneDAO.rechercherPersonne(nomInput);
			for (Personne personne : retour) {
				personne = achievePersonne(personne);
			}
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return retour;
	}

	@Override
	public Personne insertPersonne(Personne personne) throws ServiceException {
		Personne retour;
		try {
			retour = personneDAO.insertPersonne(personne);
			retour = achievePersonne(retour);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return retour;
	}

	@Override
	public void deletePersonne(Personne personne) throws ServiceException {
		try {
			personneDAO.deletePersonne(personne);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Personne> rechercherPersonne(Personne personne)
			throws ServiceException {
		List<Personne> retour = null;
		try {
			personneDAO.beginTransaction(this);
			ApplicationException exception = null;
			try {

				ApplicationException exception2 = null;
				retour = personneDAO.rechercherPersonne(personne);
				promotionDAO.beginTransaction();
				try {
					for (Personne personneFinded : retour) {
						personneFinded = achievePersonne(personneFinded);
					}
				} catch (PersistanceException e1) {
					exception2 = e1;
				}
				promotionDAO.endTransaction(exception2);
			} catch (PersistanceException e) {
				exception = e;
			}
			personneDAO.endTransaction(exception);

		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return retour;
	}

	@Override
	public Personne updatePersonne(Personne personneToUpdate)
			throws ServiceException {
		Personne retour;
		try {
			retour = personneDAO.updatePersonne(personneToUpdate);
			retour = achievePersonne(retour);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return retour;
	}

	@Override
	public Promotion insertPromotion(Promotion promotion)
			throws ServiceException {
		Promotion retour;
		try {
			retour = promotionDAO.insertPromotion(promotion);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return retour;
	}

	@Override
	public void deletePromotion(Promotion promotion) throws ServiceException {
		try {

			personneDAO.beginTransaction(this);

			ApplicationException exception = null;
			try {
				Personne searchPersonne = new Personne();
				searchPersonne.setPromotion(promotion);
				List<Personne> personnes = personneDAO
						.rechercherPersonne(searchPersonne);
				for (Personne personne : personnes) {
					personneDAO.updatePersonne(personne);
				}
			} catch (PersistanceException e) {
				exception = e;
			}
			personneDAO.endTransaction(exception);

			promotionDAO.beginTransaction(this);
			try {
				promotionDAO.deletePromotion(promotion);
			} catch (PersistanceException e) {
				exception = e;
			}
			promotionDAO.endTransaction(exception);

		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Promotion> rechercherPromotion(Promotion promotion)
			throws ServiceException {
		List<Promotion> retour;
		try {
			retour = promotionDAO.rechercherPromotion(promotion);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return retour;
	}

	@Override
	public Promotion updatePromotion(Promotion promotionToUpdate)
			throws ServiceException {
		Promotion retour;
		try {
			retour = promotionDAO.updatePromotion(promotionToUpdate);
		} catch (PersistanceException e) {
			throw new ServiceException(e);
		}
		return retour;
	}

	@Override
	public void duplicatePersonne(Personne personneToDuplicate, Integer nbFois)
			throws ServiceException {
		for (Integer i = 0; i < nbFois; i++) {
			this.insertPersonne(personneToDuplicate);
		}

	}

	@Override
	public void setconnection(Connection con) {
		this.connection = con;

	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void beginTransaction(IJDBCCompatible masterTRansaction) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void beginTransaction() {
		throw new UnsupportedOperationException();

	}

	@Override
	public void endTransaction(ApplicationException applicationException)
			throws ApplicationException {
		throw new UnsupportedOperationException();

	}

	private Personne achievePersonne(Personne personne)
			throws PersistanceException {

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
