package org.imie.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;
import org.imie.transverse.exception.ServiceException;

public class NTSGestionEcoleServiceProxy implements IGestionEcoleService {
	
	private Connection connection;
	private IGestionEcoleService gestionEcoleService;
	
	

	/**
	 * @param gestionEcoleService
	 */
	public NTSGestionEcoleServiceProxy(IGestionEcoleService gestionEcoleService) {
		super();
		this.gestionEcoleService = gestionEcoleService;
	}

	@Override
	public void setconnection(Connection con) {
		this.connection=con;
		
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
	public void endTransaction(ApplicationException applicationException) throws ApplicationException{
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
			retour = gestionEcoleService.rechercherPersonne(nomInput);
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
			retour = gestionEcoleService.insertPersonne(personne);
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
			gestionEcoleService.deletePersonne(personne);
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
			retour = gestionEcoleService.rechercherPersonne(personne);
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
			retour = gestionEcoleService.updatePersonne(personneToUpdate);
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
			retour = gestionEcoleService.insertPromotion(promotion);
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
			gestionEcoleService.deletePromotion(promotion);
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
			retour = gestionEcoleService.rechercherPromotion(promotion);
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
			retour = gestionEcoleService.updatePromotion(promotionToUpdate);
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
			gestionEcoleService.duplicatePersonne(personneToDuplicate, nbFois);
		} catch (ServiceException e) {
			serviceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				serviceException);
		
	}

	
	/**
	 * @param localConnection
	 * @return
	 * @throws PersistanceException
	 */
	private Connection superOpenJDBC(Connection localConnection)
			throws ServiceException {
		if (localConnection == null) {
			synchronized (DriverManager.class) {
				try {
					localConnection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/imie",
							"postgres", "postgres");
				} catch (SQLException e) {
					throw new ServiceException(e);
				}
			}
			try {
				localConnection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new ServiceException(e);
			}
		}
		//ATTENTION pour mutualisation
		gestionEcoleService.setconnection(localConnection);
		return localConnection;
	}

	/**
	 * @param localConnection
	 * @param autoCommit
	 * @param persistanceException
	 * @throws PersistanceException
	 */
	private void superCloseJDBC(Connection localConnection, Boolean autoCommit,
			ServiceException serviceException)
			throws ServiceException {
		try {
			if (serviceException != null) {
				throw serviceException;
			}
			if (autoCommit) {
				localConnection.commit();
			}
		} catch (SQLException e) {
			if (autoCommit) {
				try {
					localConnection.rollback();
				} catch (SQLException e1) {
					throw new ServiceException(e1);
				}
			}
			throw new ServiceException(e);
		} finally {
			if (autoCommit) {
				try {
					if (connection != null && !connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
					throw new ServiceException(e);
				}
			}
		}
	}

}
