package org.imie.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.imie.DAO.PersonneDAO;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;

public class SimonPersonneDAOProxy implements IPersonneDAO {

	private volatile IJDBCCompatible master;
	private volatile Boolean inTransaction = false;
	private Thread transactionThread = null;
	private volatile Connection connection = null;
	private IPersonneDAO personneDAO = null;

	private static SimonPersonneDAOProxy instance;

	private void PersonneDAOProxy() {
	}

	public static synchronized SimonPersonneDAOProxy getInstance() {
		if (instance == null) {
			instance = new SimonPersonneDAOProxy(new PersonneDAO());
		}
		return instance;
	}

	@Override
	public void beginTransaction() {
		beginTransaction(null);
	}

	@Override
	public synchronized void beginTransaction(IJDBCCompatible masterTransaction) {
		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : beginTransaction"));
		while (inTransaction) {
			try {
				System.out.println(String.valueOf(
						Thread.currentThread().getId()).concat(
						" : blocTransaction"));
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		inTransaction = true;
		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : startTransaction"));
		transactionThread = Thread.currentThread();

		if (masterTransaction != null) {
			master = masterTransaction;
			connection = masterTransaction.getConnection();
		}

		if (connection == null) {
			try {
				synchronized (DriverManager.class) {
					connection = DriverManager.getConnection(
							"jdbc:postgresql://127.0.0.1:5432/imie",
							"postgres", "postgres");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void endTransaction(ApplicationException applicationException) throws ApplicationException {
		master = null;
		transactionThread = null;
		connection = null;
		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : endTransaction"));
		inTransaction = false;
		notify();
	}

	@Override
	public void setconnection(Connection con) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Connection getConnection() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @param personneDAO
	 */
	public SimonPersonneDAOProxy(IPersonneDAO personneDAO) {
		super();
		this.personneDAO = personneDAO;
	}

	@Override
	public List<Personne> rechercherPersonne(String nomInput)
			throws PersistanceException {
		List<Personne> retour = null;
		Boolean autoCommit = false;
		System.out.println(String.valueOf(Thread.currentThread().getId())
				.concat(" : rechercherPersonne"));
		if (!(inTransaction && Thread.currentThread() == transactionThread)) {
			beginTransaction();
			autoCommit = true;
			try {
				this.connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new PersistanceException(e);
			}
		}
		personneDAO.setconnection(this.connection);
		try {
			retour = personneDAO.rechercherPersonne(nomInput);
			this.connection.commit();
		} catch (Exception e) {
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new PersistanceException(e1);
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
		if (autoCommit) {
			endTransaction(null);
		}
		return retour;
	}

	@Override
	public Personne insertPersonne(Personne personne)
			throws PersistanceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePersonne(Personne personne) throws PersistanceException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Personne> rechercherPersonne(Personne personne)
			throws PersistanceException {
		List<Personne> retour = null;
		Boolean autoCommit = false;
		if (!(inTransaction && Thread.currentThread() == transactionThread)) {
			beginTransaction();
			autoCommit = true;
			try {
				this.connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new PersistanceException(e);
			}
		}
		personneDAO.setconnection(this.connection);
		try {
			retour = personneDAO.rechercherPersonne(personne);
			if (autoCommit) {
				this.connection.commit();
			}
		} catch (Exception e) {
			if (autoCommit) {
				try {
					this.connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
		if (autoCommit) {
			endTransaction(null);
		}
		return retour;
	}

	@Override
	public Personne updatePersonne(Personne personneToUpdate)
			throws PersistanceException {
		Personne retour = null;
		Boolean autoCommit = false;
		if (!(inTransaction && Thread.currentThread() == transactionThread)) {
			beginTransaction();
			autoCommit = true;
			try {
				this.connection.setAutoCommit(false);
			} catch (SQLException e) {
				throw new PersistanceException(e);
			}
		}
		personneDAO.setconnection(this.connection);
		try {
			retour = personneDAO.updatePersonne(personneToUpdate);
			if (autoCommit) {
				this.connection.commit();
			}
		} catch (Exception e) {
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				throw new PersistanceException(e1);
			}
			throw new PersistanceException(e);
		} finally {
			try {
				if (autoCommit) {
					if (connection != null && !connection.isClosed()) {
						connection.close();
					}
				}
			} catch (SQLException e) {
				throw new PersistanceException(e);
			}
		}
		if (autoCommit) {
			endTransaction(null);
		}
		return retour;
	}


}
