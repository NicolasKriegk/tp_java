package org.imie.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;

public class AbstractProxy implements IJDBCCompatible{
	
	protected Connection connection;
	protected IJDBCCompatible real;

	/**
	 * @param real
	 */
	public AbstractProxy(IJDBCCompatible real) {
		super();
		this.real = real;
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
					System.out.println("new Connection");
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
		//this.connection=localConnection;
		this.real.setconnection(localConnection);
		//setconnection(localConnection);
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

	@Override
	public void setconnection(Connection con) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public Connection getConnection() {
		throw new UnsupportedOperationException();
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
}
