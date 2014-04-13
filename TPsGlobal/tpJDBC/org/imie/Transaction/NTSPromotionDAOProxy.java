package org.imie.Transaction;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.Promotion;

import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.transverse.exception.PersistanceException;

public class NTSPromotionDAOProxy extends AbstractProxy implements
		IPromotionDAO,IJDBCCompatible {

	// private IPersonneDAO dao;
	// private Connection connection;

	public NTSPromotionDAOProxy(IPromotionDAO dao) {
		super((IJDBCCompatible) dao);
	}

//	public NTSPromotionDAOProxy(IJDBCCompatible real) {
//		super(real);
//		throw new UnsupportedOperationException();
//	}

	@Override
	public void setconnection(Connection con) {
		this.connection = con;

	}

	@Override
	public Connection getConnection() {
		return this.connection;
	}

	@Override
	public Promotion insertPromotion(Promotion promotion)
			throws PersistanceException {
		Promotion retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			retour = ((IPromotionDAO) this.real).insertPromotion(promotion);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

		return retour;
	}

	@Override
	public void deletePromotion(Promotion promotion)
			throws PersistanceException {
		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			((IPromotionDAO) this.real).deletePromotion(promotion);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

	}

	@Override
	public List<Promotion> rechercherPromotion(Promotion promotion)
			throws PersistanceException {
		List<Promotion> retour = new ArrayList<Promotion>();

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			retour = ((IPromotionDAO)this.real).rechercherPromotion(promotion);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

		return retour;
	}

	@Override
	public Promotion updatePromotion(Promotion promotionToUpdate)
			throws PersistanceException {
		Promotion retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			retour = ((IPromotionDAO)this.real).updatePromotion(promotionToUpdate);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

		return retour;
	}

}
