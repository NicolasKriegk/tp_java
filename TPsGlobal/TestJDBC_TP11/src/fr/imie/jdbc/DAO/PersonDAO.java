package fr.imie.jdbc.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.imie.jdbc.DTO.PersonDTO;
import fr.imie.jdbc.Exception.PersistanceException;
import fr.imie.jdbc.InterfaceDAO.ICrudDAO;
import fr.imie.jdbc.InterfaceDAO.IPersonDAO;

public class PersonDAO implements ICrudDAO<PersonDTO>  {

	/**
	 * @param searchElement
	 * @return
	 * @throws PersistanceException
	 */
	public List<PersonDTO> search(PersonDTO person) throws PersistanceException{
		//SQL
		Connection conn = null;
		PreparedStatement stmt = null;
		//initialisation valeurs de retour
		ResultSet rs = null;
		List<PersonDTO> personnes = new ArrayList<PersonDTO>();

		//preparation chaine de requete
		StringBuilder query = new StringBuilder();
		String fieldSelection = new String();
		String whereClause = new String();
		if(person.getNom() != null){
			whereClause = concatPreparedStatementStringWhere(whereClause, "nom");
		}
		if(person.getPrenom() != null){
			whereClause = concatPreparedStatementStringWhere(whereClause, "prenom");
		}
		if(person.getDateNaissance()!=null){
			whereClause = concatPreparedStatementDateWhere(whereClause, "datenaiss");
		}
		query.append("SELECT ");
		query.append(fieldSelection.length() > 0 ? fieldSelection : " * ");
		query.append(" FROM personne ");
		query.append(whereClause);
		query.append(";");
		System.out.println(query.toString());

		try{
			conn = openJdbc();
			//preparation requete, utilisation de prepared statement
			stmt = conn.prepareStatement(query.toString());
			//completion requete
			int index = 1;
			if(person.getNom() != null){
				stmt.setString(index++,"%".concat(person.getNom()).concat("%"));
			}
			if(person.getPrenom() != null){
				stmt.setString(index++,"%".concat(person.getPrenom()).concat("%"));
			}
			if(person.getDateNaissance()!=null){
				stmt.setDate(index++, new Date(person.getDateNaissance().getTime()));
			}
			//affichage et execution requete
			System.out.println(stmt.toString());
			rs = stmt.executeQuery();
			personnes = getListFromResultSetPerson(rs);
			
		}		
		catch(SQLException e){
			throw new PersistanceException(e);
		}
		finally{
			//clotures des objets JDBC avec ou sans exception
			PersistanceException persistanceException =null;
			closeJdbc(conn, stmt, rs, persistanceException);
		}

		//passage valeur de retour
		return personnes;
	
	}

/*	code methode d'origine jusqu'au TP8
 * 
 */
//	public List<Personne> searchPerson(String searchElement) throws PersistanceException{
//		StringBuilder query = new StringBuilder();
//		//initialisation valeurs de retour
//		ResultSet rs = null;
//		Personne personne = new Personne();
//		List<Personne> personnes = new ArrayList<Personne>();
//		try{
//			//ouverture connexion BDD
//			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_jdbc", "nicolas", "nicolas");
//			//preparation requete, utilisation de prepared statement
//			query.setLength(0);
//			query.append("SELECT nom, prenom, date_naiss, id " +
//					"FROM personne WHERE LOWER(nom) LIKE LOWER(?);");
//			stmt = conn.prepareStatement(query.toString());
//			//completion requete
//			stmt.setString(1,"%".concat(searchElement).concat("%"));
//			//affichage et execution requete
////			System.out.println(stmt.toString());
//			rs = stmt.executeQuery();
//			//traitement des resultats
//			while(rs.next()){
//				//initialisation valeurs tampon
//				personne = new Personne();
//				//recuperation des infos BDD
//				personne.setId(rs.getInt("id"));
//				personne.setNom(rs.getString("nom"));
//				personne.setPrenom(rs.getString("prenom"));
//				personne.setDateNaissance(rs.getDate("date_naiss"));
//				//nouvelle ligne dans List<Personne>
//				personnes.add(personne);
//			}
//			
//		}		
//		catch(SQLException e){
//			throw new PersistanceException(e);
//		}
//		finally{
//			//clotures des objets JDBC avec ou sans exception
//			PersistanceException persistanceException =null;
//			try {
//				if(rs!=null && !rs.isClosed()){
//					rs.close();
//				}
//			} catch (SQLException e) {
//				persistanceException = new PersistanceException(e);
//			}
//			try {
//				if(stmt!=null && !stmt.isClosed()){
//					stmt.close();
//				}
//			} catch (SQLException e) {
//				persistanceException = new PersistanceException(e);
//			}
//			try {
//				if(conn!=null && !conn.isClosed()){
//					conn.close();
//				}
//			} catch (SQLException e) {
//				persistanceException = new PersistanceException(e);
//			}
//			if(persistanceException!=null){
//				throw persistanceException;
//			}
//		}
//
//		//passage valeur de retour
//		return personnes;
//	
//	}


	/* (non-Javadoc)
	 * @see fr.imie.jdbc.tp7.InterfaceDAO.IPersist#createPerson(fr.imie.jdbc.tp7.Personne)
	 */
	public PersonDTO create(PersonDTO person) throws PersistanceException {
		//SQL
		Connection conn = null;
		PreparedStatement stmt = null;
		PersistanceException persistanceException =null;
		StringBuilder query = new StringBuilder();
		//initialisation valeurs de retour
		ResultSet rs = null;
		List<PersonDTO> personnes = new ArrayList<PersonDTO>();
		try{
			conn = openJdbc();
			conn.setAutoCommit(false);
			//preparation requete, utilisation de prepared statement
			boolean dateNotNull = person.getDateNaissance() != null;
			boolean passwordNotNull = person.getPasswd() != null;
			query.setLength(0);
			query.append("INSERT INTO personne (nom, prenom");
			query = queryBuilder(dateNotNull, ", datenaiss", query);
			query = queryBuilder(passwordNotNull, ", passw", query);
			query.append(") values(?, ?");
			query = queryBuilder(dateNotNull, ", ?", query);
			query = queryBuilder(passwordNotNull, ", ?", query);
			query.append(") RETURNING nom, prenom, datenaiss, passw, id;");
			stmt = conn.prepareStatement(query.toString());
			//completion requete
			int index = 1;
			stmt.setString(index++,person.getNom());
			stmt.setString(index++,person.getPrenom());
			if(dateNotNull){
				stmt.setDate(index++, new Date(person.getDateNaissance().getTime()));
			}
			if(passwordNotNull){
				stmt.setString(index++,person.getPasswd());
			}
			//execution requete
			rs = stmt.executeQuery();
			conn.commit();
			personnes = getListFromResultSetPerson(rs);
			
		}		
		catch(SQLException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				persistanceException = new PersistanceException(e1);
			}
			if(persistanceException!=null){
				throw persistanceException;
			}
			throw new PersistanceException(e);
		}
		finally{
			closeJdbc(conn, stmt, persistanceException);
		}
		return personnes.get(0); 
		
	}

	public PersonDTO update(PersonDTO person) throws PersistanceException {
		//SQL
		Connection conn = null;
		PreparedStatement stmt = null;
		PersistanceException persistanceException =null;
		//initialisation valeurs de retour
		ResultSet rs = null;
		List<PersonDTO> personnes = new ArrayList<PersonDTO>();
	
		try{
			conn = openJdbc();
			conn.setAutoCommit(false);
			//preparation requete, utilisation de prepared statement
			String affectation = "";
			if (person.getNom() != null) {
				affectation = concatPreparedStatementString(affectation,
						"nom=?");
			}
			if (person.getPrenom() != null) {
				affectation = concatPreparedStatementString(affectation,
						"prenom=?");
			}
			if (person.getDateNaissance() != null) {
				affectation = concatPreparedStatementString(affectation,
						"date_naiss=?");
			}
			if (person.getPasswd() != null) {
				affectation = concatPreparedStatementString(affectation,
						"passw=?");
			}
	
			String updateQuery = "update personne set ".concat(affectation)
					.concat(" where id=")
					.concat(person.getId().toString())
					.concat("  returning nom,prenom,date_naiss,passw,id");
			stmt = conn.prepareStatement(updateQuery);
	
			Integer index = 1;
			if (person.getNom() != null) {
				stmt.setString(index++, person.getNom());
			}
			if (person.getPrenom() != null) {
				stmt.setString(index++,
						person.getPrenom());
			}
	
			if (person.getDateNaissance() != null) {
				stmt.setDate(index++, new Date(person
						.getDateNaissance().getTime()));
			}
			if (person.getPasswd() != null) {
				stmt.setString(index++,
						person.getPasswd());
			}
	
			//execution requete
			rs = stmt.executeQuery();
			conn.commit();
			personnes = getListFromResultSetPerson(rs);
			
		}		
		catch(SQLException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				persistanceException = new PersistanceException(e1);
			}
			if(persistanceException!=null){
				throw persistanceException;
			}
			throw new PersistanceException(e);
		}
		finally{
			closeJdbc(conn, stmt, rs, persistanceException);
		}
		return personnes.get(0); 
		
	}

	@Override
	public void delete(PersonDTO person) throws PersistanceException {
		//SQL
		Connection conn = null;
		Statement stmt = null;
		PersistanceException persistanceException =null;
		try{
			conn = openJdbc();
			conn.setAutoCommit(false);
	
			String deleteQuery = "delete from personne where id="
					.concat(person.getId().toString());
			stmt = conn.createStatement();
	
			//execution requete
			stmt.executeUpdate(deleteQuery);
			conn.commit();
			
		}		
		catch(SQLException e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				persistanceException = new PersistanceException(e1);
			}
			if(persistanceException!=null){
				throw persistanceException;
			}
			throw new PersistanceException(e);
		}
		finally{
			closeJdbc(conn, stmt, persistanceException);
		}
		
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private List<PersonDTO> getListFromResultSetPerson(ResultSet rs)
			throws SQLException {
		List<PersonDTO> personnes = new ArrayList<PersonDTO>(); 
		while(rs.next()){
			//initialisation valeurs tampon
			PersonDTO resultPersonne = new PersonDTO();
			//recuperation des infos BDD
			resultPersonne.setId(rs.getInt("id"));
			resultPersonne.setNom(rs.getString("nom"));
			resultPersonne.setPrenom(rs.getString("prenom"));
			resultPersonne.setDateNaissance(rs.getDate("datenaiss"));
//			resultPersonne.setPasswd(rs.getString("passw"));
			//nouvelle ligne dans List<Personne>
			personnes.add(resultPersonne);
		}
		return personnes;
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	private Connection openJdbc() throws SQLException {
		//ouverture connexion BDD
		Connection conn = null;
		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/imie", "postgres", "postgres");
		return conn;
	}

	/**
	 * @param persistanceException
	 * @throws PersistanceException
	 */
	private void closeJdbc(Connection conn, Statement statement, PersistanceException persistanceException)
			throws PersistanceException {
		//clotures des objets JDBC avec ou sans exception
		closeJdbc(conn, statement, null, persistanceException);
	}
	private void closeJdbc(Connection conn, Statement statement, ResultSet resultSet, PersistanceException persistanceException)
			throws PersistanceException {
		//clotures des objets JDBC avec ou sans exception
		if(resultSet != null){
			try {
				if(!resultSet.isClosed()){
					resultSet.close();
				}
			} catch (SQLException e) {
				persistanceException = new PersistanceException(e);
			}
		}
		try {
			if(statement!=null && !statement.isClosed()){
				statement.close();
			}
		} catch (SQLException e) {
			persistanceException = new PersistanceException(e);
		}
		try {
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		} catch (SQLException e) {
			persistanceException = new PersistanceException(e);
		}
		if(persistanceException!=null){
			throw persistanceException;
		}
	}

	/**
	 * @param condition
	 * @param ajout
	 * @param query
	 * @return
	 */
	private StringBuilder queryBuilder(boolean condition, String ajout, StringBuilder query){
		if(condition){
			query.append(ajout);
		}
		return query;
	}

//	corrige de Simon
	/**
	 * @param buildString
	 * @param champ
	 * @return
	 */
	private String concatPreparedStatementString(String buildString, String champ) {
		buildString = buildString
				.concat(buildString.length() > 0 ? "," : "")
				.concat(champ);
		return buildString;
	}

	/**
	 * @param buildString
	 * @param champ
	 * @return
	 */
	private String concatPreparedStatementStringSelect(String buildString, String champ) {
		buildString = buildString
				.concat(buildString.length() > 0 ? ", " : "")
				.concat(champ);
		return buildString;
	}

	/**
	 * @param buildString
	 * @param champ
	 * @return
	 */
	private String concatPreparedStatementStringWhere(String buildString, String champ) {
		buildString = buildString
				.concat(buildString.length() > 0 ? " AND (" : " WHERE (")
				.concat("LOWER(")
				.concat(champ)
				.concat(") LIKE LOWER(?))")
				;
		return buildString;
	}

	private String concatPreparedStatementDateWhere(String buildString, String champ) {
		buildString = buildString
				.concat(buildString.length() > 0 ? " AND (" : " WHERE (")
				.concat(champ)
				.concat("= ?)")
				;
		return buildString;
	}
}
