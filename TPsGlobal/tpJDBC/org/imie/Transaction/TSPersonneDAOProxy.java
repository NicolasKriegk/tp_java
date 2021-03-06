package org.imie.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import model.Personne;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.qualifier.TransactionBeanJDBC;
import org.imie.qualifier.TransactionModeJDBC;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;

@TransactionBeanJDBC(TransactionModeJDBC.THREADSAFE)
@Stateless(name="PersonneDAO")
public class TSPersonneDAOProxy extends AbstractProxy implements IPersonneDAO,IJDBCCompatible {


	private Boolean inTransaction = false;
	private IJDBCCompatible jdbcCompatible = null;
	private Thread thread = null;

	@Inject
	public TSPersonneDAOProxy(@TransactionBeanJDBC(TransactionModeJDBC.REAL) IPersonneDAO dao){
		super((IJDBCCompatible) dao);
	}
	
	
//	public TSPersonneDAOProxy(IJDBCCompatible real) {
//		super(real);
//		throw new UnsupportedOperationException();
//	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.Transaction.NTSPersonneDAOProxy#beginTransaction(org.imie.
	 * Transaction.IJDBCCompatible)
	 */
	@Override
	public synchronized void beginTransaction(IJDBCCompatible masterTransaction) {

		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : PersonneDAO beginTransaction"));
		while (inTransaction) {
			try {
				System.out.println(String.valueOf(
						Thread.currentThread().getId()).concat(
						" : PersonneDAO blocTransaction"));
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
			retour = ((IPersonneDAO)this.real).rechercherPersonne(nomInput);
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
			retour = ((IPersonneDAO)this.real).insertPersonne(personne);
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
			((IPersonneDAO)this.real).deletePersonne(personne);
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
			retour = ((IPersonneDAO)this.real).rechercherPersonne(personne);
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
			retour = ((IPersonneDAO)this.real).updatePersonne(personneToUpdate);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}

	@Override
	public Personne verifierAuthPersonne(Personne personne) {
		Boolean standAloneTransaction = false;
		Personne retour = null;
		standAloneTransaction = ckeckThreadTransaction(standAloneTransaction);
		PersistanceException persistanceException = null;
		try {
			retour = ((IPersonneDAO)this.real).verifierAuthPersonne(personne);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}

}
