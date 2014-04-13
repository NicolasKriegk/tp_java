package org.imie.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;
import org.imie.transverse.exception.ServiceException;

public class TSGestionEcoleServiceProxy extends AbstractProxy implements IGestionEcoleService {


	private Boolean inTransaction = false;
	private IJDBCCompatible jdbcCompatible = null;
	private Thread thread = null;

	
	public TSGestionEcoleServiceProxy(IGestionEcoleService service){
		super(service);
	}
	
	public TSGestionEcoleServiceProxy(IJDBCCompatible real) {
		super(real);
		throw new UnsupportedOperationException();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.Transaction.NTSPersonneDAOProxy#beginTransaction(org.imie.
	 * Transaction.IJDBCCompatible)
	 */
	@Override
	public synchronized void beginTransaction(IJDBCCompatible masterTransaction) {

		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : Service beginTransaction"));
		while (inTransaction) {
			try {
				System.out.println(String.valueOf(
						Thread.currentThread().getId()).concat(
						" : Service blocTransaction"));
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		inTransaction = true;
		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : startTransaction"));
		thread = Thread.currentThread();

		if (masterTransaction != null) {
			jdbcCompatible = masterTransaction;
			this.connection= masterTransaction.getConnection();
		}

		try {
			this.connection = super.superOpenJDBC(this.connection);
		} catch (PersistanceException e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.Transaction.NTSPersonneDAOProxy#beginTransaction()
	 */
	@Override
	public void beginTransaction() {
		beginTransaction(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.Transaction.NTSPersonneDAOProxy#endTransaction()
	 */
	@Override
	public synchronized void endTransaction(ApplicationException applicationException)
			throws ApplicationException {
		super.superCloseJDBC(this.connection, jdbcCompatible == null,
				applicationException);
		jdbcCompatible = null;
		thread = null;
		this.connection =null;
		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : endTransaction"));
		inTransaction = false;
		notify();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPersonneDAOProxy#rechercherPersonne(java.lang
	 * .String)
	 */
	@Override
	public List<Personne> rechercherPersonne(String nomInput)
			throws PersistanceException {
		Boolean standAloneTransaction = false;
		List<Personne> retour = new ArrayList<Personne>();
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		ApplicationException applicationException = null;
		try {
			retour = ((IGestionEcoleService)this.real).rechercherPersonne(nomInput);
		} catch (PersistanceException e) {
			applicationException = e;
		}
		if (standAloneTransaction) {
			endTransaction(applicationException);
		}
		return retour;
	}

	/**
	 * @param standAloneTransaction
	 * @return
	 */
	private Boolean ckeckThreadTransaction(Boolean standAloneTransaction) {
		if (!(inTransaction && Thread.currentThread() == thread)) {
			beginTransaction();
			standAloneTransaction = true;
		}
		return standAloneTransaction;
	}



	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPersonneDAOProxy#insertPersonne(org.imie.transverse
	 * .DTO.Personne)
	 */
	@Override
	public Personne insertPersonne(Personne personne)
			throws PersistanceException {
		Boolean standAloneTransaction = false;
		Personne retour = null;
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			retour = ((IGestionEcoleService)this.real).insertPersonne(personne);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPersonneDAOProxy#deletePersonne(org.imie.transverse
	 * .DTO.Personne)
	 */
	@Override
	public void deletePersonne(Personne personne) throws PersistanceException {
		Boolean standAloneTransaction = false;
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			((IGestionEcoleService)this.real).deletePersonne(personne);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPersonneDAOProxy#rechercherPersonne(org.imie.
	 * transverse.DTO.Personne)
	 */
	@Override
	public List<Personne> rechercherPersonne(Personne personne)
			throws PersistanceException {
		Boolean standAloneTransaction = false;
		List<Personne> retour = new ArrayList<Personne>();
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			retour = ((IGestionEcoleService)this.real).rechercherPersonne(personne);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPersonneDAOProxy#updatePersonne(org.imie.transverse
	 * .DTO.Personne)
	 */
	@Override
	public Personne updatePersonne(Personne personneToUpdate)
			throws PersistanceException {
		Boolean standAloneTransaction = false;
		Personne retour = null;
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			retour = ((IGestionEcoleService)this.real).updatePersonne(personneToUpdate);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPromotionDAOProxy#insertPromotion(org.imie.transverse
	 * .DTO.Promotion)
	 */
	@Override
	public Promotion insertPromotion(Promotion promotion)
			throws PersistanceException {
		Boolean standAloneTransaction = false;
		Promotion retour = null;
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			retour = ((IGestionEcoleService)this.real).insertPromotion(promotion);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPromotionDAOProxy#deletePromotion(org.imie.transverse
	 * .DTO.Promotion)
	 */
	@Override
	public void deletePromotion(Promotion promotion) throws PersistanceException {
		Boolean standAloneTransaction = false;
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			((IGestionEcoleService)this.real).deletePromotion(promotion);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPromotionDAOProxy#rechercherPromotion(org.imie.
	 * transverse.DTO.Promotion)
	 */
	@Override
	public List<Promotion> rechercherPromotion(Promotion promotion)
			throws PersistanceException {
		Boolean standAloneTransaction = false;
		List<Promotion> retour = new ArrayList<Promotion>();
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			retour = ((IGestionEcoleService)this.real).rechercherPromotion(promotion);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.Transaction.NTSPromotionDAOProxy#updatePromotion(org.imie.transverse
	 * .DTO.Promotion)
	 */
	@Override
	public Promotion updatePromotion(Promotion promotionToUpdate)
			throws PersistanceException {
		Boolean standAloneTransaction = false;
		Promotion retour = null;
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			retour = ((IGestionEcoleService)this.real).updatePromotion(promotionToUpdate);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}

	@Override
	public void duplicatePersonne(Personne personneToDuplicate, Integer nbFois)
			throws ServiceException {
		Boolean standAloneTransaction = false;
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			((IGestionEcoleService)this.real).duplicatePersonne(personneToDuplicate,nbFois);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		
	}

}
