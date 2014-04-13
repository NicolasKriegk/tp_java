package org.imie.Transaction;

import java.sql.Connection;

import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;

public interface IJDBCCompatible{
	public void setconnection(Connection con);
	public Connection getConnection();
	void beginTransaction(IJDBCCompatible masterTRansaction);
	public void beginTransaction();
	public void endTransaction(ApplicationException applicationException) throws ApplicationException;
}
