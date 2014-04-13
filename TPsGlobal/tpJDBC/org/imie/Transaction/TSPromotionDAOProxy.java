package org.imie.Transaction;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import model.Promotion;

import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.qualifier.TransactionBeanJDBC;
import org.imie.qualifier.TransactionModeJDBC;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;

@TransactionBeanJDBC(TransactionModeJDBC.THREADSAFE)
@Stateless(name="PromotionDAO")
public class TSPromotionDAOProxy extends AbstractProxy implements IPromotionDAO,IJDBCCompatible {

	private Boolean inTransaction = false;
	private IJDBCCompatible jdbcCompatible = null;
	private Thread thread = null;

	@Inject
	public TSPromotionDAOProxy(@TransactionBeanJDBC(TransactionModeJDBC.REAL) IPromotionDAO dao){
		super((IJDBCCompatible) dao);
	}
	
//	public TSPromotionDAOProxy(IJDBCCompatible real) {
//		super(real);
//		throw new UnsupportedOperationException();
//	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.Transaction.NTSPromotionDAOProxy#beginTransaction(org.imie.
	 * Transaction.IJDBCCompatible)
	 */
	@Override
	public synchronized void beginTransaction(IJDBCCompatible masterTransaction) {

		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : PromotionDAO beginTransaction"));
		while (inTransaction) {
			try {
				System.out.println(String.valueOf(
						Thread.currentThread().getId()).concat(
						" : PromotionDAO blocTransaction"));
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
	 * @see org.imie.Transaction.NTSPromotionDAOProxy#beginTransaction()
	 */
	@Override
	public void beginTransaction() {
		beginTransaction(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.Transaction.NTSPromotionDAOProxy#endTransaction()
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
			retour = ((IPromotionDAO)this.real).insertPromotion(promotion);
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
			((IPromotionDAO)this.real).deletePromotion(promotion);
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
			retour = ((IPromotionDAO)this.real).rechercherPromotion(promotion);
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
			retour = ((IPromotionDAO)this.real).updatePromotion(promotionToUpdate);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		if (standAloneTransaction) {
			endTransaction(persistanceException);
		}
		return retour;
	}

}
