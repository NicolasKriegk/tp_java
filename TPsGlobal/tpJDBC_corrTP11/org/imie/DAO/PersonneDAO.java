package org.imie.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.PersistanceException;

/**
 * @author imie
 *
 */
/**
 * @author imie
 *
 */
public class PersonneDAO implements IPersonneDAO {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.IPersonneDAO#rechercherPersonne(java.lang.String)
	 */
	@Override
	public List<Personne> rechercherPersonne(String nomInput)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Personne> personnes = new ArrayList<Personne>();
		try {

			cn = openJDBC();
			// construcution de la requête paramétrée
			String query = "select id,nom,prenom,datenaiss,promotionid from personne where nom like ?";
			statement = cn.prepareStatement(query);
			// affectation des valeurs de paramètres
			statement.setString(1, "%".concat(nomInput.concat("%")));
			// exécution de la requête
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personnes.add(buildDTO(resultSet));
			}

		} catch (SQLException e) {
			throw new PersistanceException(e);
		} finally {
			// clotures des objets JDBC avec ou sans exception
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(cn);
		}
		return personnes;
	}

	@Override
	public Personne insertPersonne(Personne personne)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC
		Personne retour = null;
		Connection cn = null;
		PreparedStatement statement = null;
		PersistanceException persistanceException = null;
		String columns = "";
		String values = "";


		if (personne.getNom() != null) {
			columns = concatPreparedStatementString(columns, "nom");
			values = concatPreparedStatementString(values, "?");
		}
		if (personne.getPrenom() != null) {
			columns = concatPreparedStatementString(columns, "prenom");
			values = concatPreparedStatementString(values, "?");
		}
		if (personne.getDateNaiss() != null) {
			columns = concatPreparedStatementString(columns, "datenaiss");
			values = concatPreparedStatementString(values, "?");
		}
		if (personne.getPassw() != null) {
			columns = concatPreparedStatementString(columns, "passw");
			values = concatPreparedStatementString(values, "?");
		}
		if (personne.getPromotion() != null) {
			columns = concatPreparedStatementString(columns, "promotionid");
			values = concatPreparedStatementString(values, "?");
		}

		try {

			cn = openJDBC();
			cn.setAutoCommit(false);
			// construcution de la requête paramétrée
			String query = "insert into personne(".concat(columns)
					.concat(") values(").concat(values)
					.concat(") returning nom,prenom,datenaiss,passw,promotionid,id");
			statement = cn.prepareStatement(query);
			// affectation des valeurs de paramètres
			Integer compteurValues = 1;
			if (personne.getNom() != null) {
				statement.setString(compteurValues++, personne.getNom());
			}
			if (personne.getPrenom() != null) {
				statement.setString(compteurValues++, personne.getPrenom());
			}

			if (personne.getDateNaiss() != null) {
				java.sql.Date dateNaiss = null;
				dateNaiss = new Date(personne.getDateNaiss().getTime());
				statement.setDate(compteurValues++, dateNaiss);
			}
			if (personne.getPassw() != null) {
				statement.setString(compteurValues++, personne.getPassw());
			}
			if (personne.getPromotion() != null) {
				statement.setInt(compteurValues++, personne.getPromotion().getId());
			}

			// exécution de la requête
			ResultSet executeUpdateResult = statement.executeQuery();
			if (executeUpdateResult.next()) {
				retour = buildDTO(executeUpdateResult);
			}
			cn.commit();

		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				throw new PersistanceException(e1);
			}
		} finally {
			closeStatement(statement);
			closeConnection(cn);
		}
		return retour;
	}


	

	@Override
	public List<Personne> rechercherPersonne(Personne personne)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Personne> personnes = new ArrayList<Personne>();
		try {

			cn = openJDBC();
			// construcution de la requête paramétrée
			String query = "select id,nom,prenom,datenaiss,passw,promotionid from personne ";
			String condition = "";
			if (personne.getNom() != null) {
				condition = concatConditionString(condition, "nom like ? ");
			}
			if (personne.getPrenom() != null) {
				condition = concatConditionString(condition, "prenom like ? ");
			}
			if (personne.getDateNaiss() != null) {
				condition = concatConditionString(condition, "datenaiss = ? ");
			}
			if (personne.getPromotion() != null) {
				condition = concatConditionString(condition, "promotionid = ? ");
			}

			statement = cn.prepareStatement(query.concat(condition));
			// affectation des valeurs de paramètres
			Integer compteurCondition = 1;
			if (personne.getNom() != null) {
				statement.setString(compteurCondition++,
						"%".concat(personne.getNom()).concat("%"));
			}
			if (personne.getPrenom() != null) {
				statement.setString(compteurCondition++,
						"%".concat(personne.getPrenom()).concat("%"));
			}
			if (personne.getDateNaiss() != null) {
				statement.setDate(compteurCondition++, new Date(personne
						.getDateNaiss().getTime()));
			}
			if (personne.getPromotion() != null) {
				statement.setInt(compteurCondition++, personne.getPromotion().getId());
			}


			// exécution de la requête
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personnes.add(buildDTO(resultSet));
			}

		} catch (SQLException e) {
			throw new PersistanceException(e);
		} finally {
			// clotures des objets JDBC avec ou sans exception
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(cn);
		}
		return personnes;

	}



	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.interfaceDAO.IPersonneDAO#deletePersonne(org.imie.transverse
	 * .DTO.Personne)
	 */
	@Override
	public void deletePersonne(Personne personne) throws PersistanceException {
		Connection cn = null;
		Statement statement = null;
		PersistanceException persistanceException = null;
		try {

			cn = openJDBC();
			cn.setAutoCommit(false);

			String deleteQuery = "delete from personne where id="
					.concat(personne.getId().toString());
			statement = cn.createStatement();
			statement.executeUpdate(deleteQuery);

			cn.commit();
		} catch (SQLException e) {
			try {

				cn.rollback();
			} catch (SQLException e1) {
				throw  new PersistanceException(e1);
			}
		} finally {
			// clotures des objets JDBC avec ou sans exception
			closeStatement(statement);
			closeConnection(cn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.interfaceDAO.IPersonneDAO#updatePersonne(org.imie.transverse
	 * .DTO.Personne)
	 */
	@Override
	public Personne updatePersonne(Personne personneToUpdate)
			throws PersistanceException {
		Connection cn = null;
		PersistanceException persistanceException = null;
		Personne retour=null;
		try {

			cn = openJDBC();
			cn.setAutoCommit(false);

			retour = updatePersonne(personneToUpdate, cn);
			
			cn.commit();
		} catch (SQLException e) {
			try {

				cn.rollback();
			} catch (SQLException e1) {
				throw new PersistanceException(e1);
			}
		} finally {

			// clotures des objets JDBC avec ou sans exception
			closeConnection(cn);
		}
		return retour;

	}
	public Personne updatePersonne(Personne personneToUpdate, Connection cn)
			throws PersistanceException {
//		Connection cn = null;
		PreparedStatement statement = null;
//		PersistanceException persistanceException = null;
		Personne retour=null;
		ResultSet executeUpdateResult=null;
		try {

//			cn = openJDBC();
//			cn.setAutoCommit(false);

			String affectation = "nom=?, prenom=?, datenaiss=?, passw=?, promotionid=?";

			String updateQuery = "update personne set ".concat(affectation)
					.concat(" where id=")
					.concat(personneToUpdate.getId().toString())
					.concat("  returning nom,prenom,datenaiss,passw,promotionid,id");
			statement = cn.prepareStatement(updateQuery);

			Integer compteurValues = 1;
			statement.setString(compteurValues++, personneToUpdate.getNom());
			statement.setString(compteurValues++,personneToUpdate.getPrenom());
			statement.setDate(compteurValues++, new Date(personneToUpdate.getDateNaiss().getTime()));
			statement.setString(compteurValues++,personneToUpdate.getPassw());
			statement.setInt(compteurValues++,personneToUpdate.getPromotion().getId());

			// exécution de la requête
			executeUpdateResult = statement.executeQuery();
			if (executeUpdateResult.next()) {
				retour = buildDTO(executeUpdateResult);
			}
//			cn.commit();
		} catch (SQLException e) {
			try {

				cn.rollback();
			} catch (SQLException e1) {
				throw new PersistanceException(e1);
			}
		} finally {

			// clotures des objets JDBC avec ou sans exception
			closeResultSet(executeUpdateResult);
			closeStatement(statement);
		}
		return retour;

	}

	/**
	 * @param executeUpdateResult
	 * @return
	 * @throws SQLException
	 * @throws PersistanceException 
	 */
	private Personne buildDTO(ResultSet executeUpdateResult)
			throws SQLException, PersistanceException {
		Personne retour;
		Personne newPersonne = new Personne();
		newPersonne.setNom(executeUpdateResult.getString("nom"));
		newPersonne.setPrenom(executeUpdateResult.getString("prenom"));
		newPersonne.setDateNaiss(executeUpdateResult
				.getDate("datenaiss"));
		newPersonne.setId(executeUpdateResult.getInt("id"));
		newPersonne.setPassw(executeUpdateResult.getString("passw"));
		
		Integer promotionId = executeUpdateResult.getInt("promotionid");
		if (executeUpdateResult.wasNull()){
			newPersonne.setPromotion(null);
		}else{
			IPromotionDAO promotionDAO = new PromotionDAO();
			Promotion promotionSearch = new Promotion();
			promotionSearch.setId(promotionId);
			List<Promotion> promotions = promotionDAO.rechercherPromotion(promotionSearch);
			newPersonne.setPromotion(promotions.get(0));
		}
		
		retour = newPersonne;
		return retour;
	}
	
	/**
	 * @param cn
	 * @param statement
	 * @param resultSet
	 * @return
	 * @throws PersistanceException 
	 */
	private void closeConnection(Connection cn) throws PersistanceException {
		// clotures des objets JDBC avec ou sans exception
		PersistanceException persistanceException= null;
		try {
			if (cn != null && !cn.isClosed()) {
				cn.close();
			}
		} catch (SQLException e) {
			persistanceException = new PersistanceException(e);
		}
		if (persistanceException != null) {
			throw persistanceException;
		}

	}
	
	/**
	 * @param statement
	 * @throws PersistanceException
	 */
	private void closeStatement(Statement statement) throws PersistanceException {
		// clotures des objets JDBC avec ou sans exception
		PersistanceException persistanceException= null;
		try {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		} catch (SQLException e) {
			persistanceException = new PersistanceException(e);
		}
		if (persistanceException != null) {
			throw persistanceException;
		}


	}

	/**
	 * @param resultSet
	 * @return
	 * @throws PersistanceException 
	 */
	private void closeResultSet(ResultSet resultSet) throws PersistanceException {
		// clotures des objets JDBC avec ou sans exception
		PersistanceException persistanceException= null;
		try {
			if (resultSet != null && !resultSet.isClosed()) {
				resultSet.close();
			}
		} catch (SQLException e) {
			persistanceException = new PersistanceException(e);
		}
		if (persistanceException != null) {
			throw persistanceException;
		}

	}
	/**
	 * @param buildString
	 * @param champ
	 * @return
	 */
	private String concatPreparedStatementString(String buildString,
			String champ) {
		buildString = buildString.concat(buildString.length() > 0 ? "," : "")
				.concat(champ);
		return buildString;
	}

	/**
	 * @param buildString
	 * @param condition
	 * @return
	 */
	private String concatConditionString(String buildString, String condition) {
		buildString = buildString.concat(
				buildString.length() > 0 ? "and " : "where ").concat(condition);
		return buildString;
	}

	/**
	 * @return
	 * @throws SQLException
	 */
	private Connection openJDBC() throws SQLException {
		Connection cn;
		// création de la connection
		cn = DriverManager
				.getConnection("jdbc:postgresql://127.0.0.1:5432/tp_jdbc",
						"postgres", "postgres");
		return cn;
	}


}
