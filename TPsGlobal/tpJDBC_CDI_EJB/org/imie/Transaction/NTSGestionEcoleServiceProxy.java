package org.imie.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;
import org.imie.transverse.exception.ServiceException;

public class NTSGestionEcoleServiceProxy extends AbstractProxy implements
		IGestionEcoleService {

	private Connection connection;

	// private IGestionEcoleService gestionEcoleService;

	/**
	 * @param gestionEcoleService
	 */
	public NTSGestionEcoleServiceProxy(IGestionEcoleService gestionEcoleService) {
		super(gestionEcoleService);
		// this.gestionEcoleService = gestionEcoleService;
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

	@Override
	public List<Personne> rechercherPersonne(String nomInput)
			throws ServiceException {
		List<Personne> retour = new ArrayList<Personne>();

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			retour = ((IGestionEcoleService) this.real)
					.rechercherPersonne(nomInput);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

		return retour;
	}

	@Override
	public Personne insertPersonne(Personne personne) throws ServiceException {
		Personne retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			retour = ((IGestionEcoleService) this.real)
					.insertPersonne(personne);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

		return retour;
	}

	@Override
	public void deletePersonne(Personne personne) throws ServiceException {

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			((IGestionEcoleService) this.real).deletePersonne(personne);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

	}

	@Override
	public List<Personne> rechercherPersonne(Personne personne)
			throws ServiceException {
		List<Personne> retour = new ArrayList<Personne>();

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			retour = ((IGestionEcoleService) this.real)
					.rechercherPersonne(personne);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

		return retour;
	}

	@Override
	public Personne updatePersonne(Personne personneToUpdate)
			throws ServiceException {
		Personne retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			retour = ((IGestionEcoleService) this.real)
					.updatePersonne(personneToUpdate);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

		return retour;
	}

	@Override
	public Promotion insertPromotion(Promotion promotion)
			throws ServiceException {
		Promotion retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			retour = ((IGestionEcoleService) this.real)
					.insertPromotion(promotion);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

		return retour;
	}

	@Override
	public void deletePromotion(Promotion promotion) throws ServiceException {
		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			((IGestionEcoleService) this.real).deletePromotion(promotion);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

	}

	@Override
	public List<Promotion> rechercherPromotion(Promotion promotion)
			throws ServiceException {
		List<Promotion> retour = new ArrayList<Promotion>();

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			retour = ((IGestionEcoleService) this.real)
					.rechercherPromotion(promotion);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

		return retour;
	}

	@Override
	public Promotion updatePromotion(Promotion promotionToUpdate)
			throws ServiceException {
		Promotion retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			retour = ((IGestionEcoleService) this.real)
					.updatePromotion(promotionToUpdate);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

		return retour;
	}

	@Override
	public void duplicatePersonne(Personne personneToDuplicate, Integer nbFois)
			throws ServiceException {
		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			((IGestionEcoleService) this.real).duplicatePersonne(
					personneToDuplicate, nbFois);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

	}

	@Override
	public Personne verifierAuthPersonne(Personne personne)
			throws ServiceException {
		Personne retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		ServiceException serviceException = null;
		try {
			retour = ((IGestionEcoleService) this.real)
					.updatePersonne(personne);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);

		return retour;
	}

}
