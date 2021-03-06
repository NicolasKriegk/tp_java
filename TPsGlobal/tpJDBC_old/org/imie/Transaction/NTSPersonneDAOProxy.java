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

public class NTSPersonneDAOProxy extends AbstractProxy implements IPersonneDAO {

	private IPersonneDAO dao;
	//private Connection connection;

	public NTSPersonneDAOProxy(IPersonneDAO dao){
		super(dao);
	}
	
	public NTSPersonneDAOProxy(IJDBCCompatible real) {
		super(real);
		throw new UnsupportedOperationException();
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



}
