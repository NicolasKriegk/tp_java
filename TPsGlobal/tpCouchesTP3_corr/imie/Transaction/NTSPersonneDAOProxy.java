package org.imie.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;

public class NTSPersonneDAOProxy implements IPersonneDAO {

	private IPersonneDAO dao;
	private Connection connection;

	/**
	 * @param dao
	 */
	public NTSPersonneDAOProxy(IPersonneDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public void setconnection(Connection con) {
		this.connection = con;

	}

	@Override
	public Connection getConnection() {
		return this.connection;
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
	public void endTransaction(ApplicationException applicationException) throws ApplicationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Personne> rechercherPersonne(String nomInput)
			throws PersistanceException {
		List<Personne> retour = new ArrayList<Personne>();

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			retour = dao.rechercherPersonne(nomInput);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

		return retour;
	}

	@Override
	public Personne insertPersonne(Personne personne)
			throws PersistanceException {
		Personne retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			retour = dao.insertPersonne(personne);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

		return retour;
	}

	@Override
	public void deletePersonne(Personne personne) throws PersistanceException {

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			dao.deletePersonne(personne);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

	}

	@Override
	public List<Personne> rechercherPersonne(Personne personne)
			throws PersistanceException {
		List<Personne> retour = new ArrayList<Personne>();

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			retour = dao.rechercherPersonne(personne);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

		return retour;
	}

	@Override
	public Personne updatePersonne(Personne personneToUpdate)
			throws PersistanceException {
		Personne retour = null;

		// begin connection JDBC
		Connection localConnection = superOpenJDBC(this.getConnection());
		// appel code fonctionel
		PersistanceException persistanceException = null;
		try {
			retour = dao.updatePersonne(personneToUpdate);
		} catch (PersistanceException e) {
			persistanceException = e;
		}
		// end connectionJDBC
		superCloseJDBC(localConnection, this.getConnection() == null,
				persistanceException);

		return retour;
	}

	/**
	 * @param localConnection
	 * @return
	 * @throws PersistanceException
	 */
	protected Connection superOpenJDBC(Connection localConnection)
			throws PersistanceException {
		if (localConnection == null) {
			synchronized (DriverManager.class) {
				try {
					localConnection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/imie",
							"postgres", "postgres");
				} catch (SQLException e) {
					throw new PersistanceException(e);
				}
			}
			try {
				localConnection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new PersistanceException(e);
			}
		}
		dao.setconnection(localConnection);
		return localConnection;
	}

	/**
	 * @param localConnection
	 * @param autoCommit
	 * @param persistanceException
	 * @throws PersistanceException
	 */
	protected void superCloseJDBC(Connection localConnection, Boolean autoCommit,
			ApplicationException applicationException)
			throws ApplicationException {
		try {
			if (applicationException != null) {
				throw applicationException;
			}
			if (autoCommit) {
				localConnection.commit();
			}
		} catch (Exception e) {
			if (autoCommit) {
				try {
					localConnection.rollback();
				} catch (SQLException e1) {
					throw new PersistanceException(e1);
				}
			}
			throw new PersistanceException(e);
		} finally {
			if (autoCommit) {
				try {
					if (connection != null && !connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
					throw new PersistanceException(e);
				}
			}
		}
	}

}
