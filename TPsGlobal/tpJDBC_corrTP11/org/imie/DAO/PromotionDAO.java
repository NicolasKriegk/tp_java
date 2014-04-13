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
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.PersistanceException;

public class PromotionDAO implements IPromotionDAO {

	@Override
	public Promotion insertPromotion(Promotion promotion)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC
		Promotion retour = null;
		Connection cn = null;
		PreparedStatement statement = null;
		PersistanceException persistanceException = null;
		String columns = "";
		String values = "";

		if (promotion.getLibelle() != null) {
			columns = concatPreparedStatementString(columns, "libelle");
			values = concatPreparedStatementString(values, "?");
		}
		try {

			cn = openJDBC();
			cn.setAutoCommit(false);
			// construcution de la requête paramétrée
			String query = "insert into promotion(".concat(columns)
					.concat(") values(").concat(values)
					.concat(") returning libelle,id");
			statement = cn.prepareStatement(query);
			// affectation des valeurs de paramètres
			Integer compteurValues = 1;
			if (promotion.getLibelle() != null) {
				statement.setString(compteurValues++, promotion.getLibelle());
			} // exécution de la requête
			ResultSet executeUpdateResult = statement.executeQuery();
			if (executeUpdateResult.next()) {
				retour = buildDTO(executeUpdateResult);
			}
			cn.commit();

		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				persistanceException = new PersistanceException(e);
			}
			throw new PersistanceException(e);
		} finally {
			closeStatement(statement);
			closeConnection(cn);
		}
		return retour;
	}

	@Override
	public List<Promotion> rechercherPromotion(Promotion promotion)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Promotion> promotions = new ArrayList<Promotion>();
		try {

			cn = openJDBC();
			// construcution de la requête paramétrée
			String query = "select id,libelle from promotion ";
			String condition = "";
			if (promotion.getLibelle() != null) {
				condition = concatConditionString(condition, "libelle like ? ");
			}
			if (promotion.getId() != null) {
				condition = concatConditionString(condition, "id = ? ");
			}

			statement = cn.prepareStatement(query.concat(condition));
			// affectation des valeurs de paramètres
			Integer compteurCondition = 1;
			if (promotion.getLibelle() != null) {
				statement.setString(compteurCondition++,
						"%".concat(promotion.getLibelle()).concat("%"));
			}

			if (promotion.getId() != null) {
				statement.setInt(compteurCondition++, promotion.getId());
			}
			// exécution de la requête
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				promotions.add(buildDTO(resultSet));
			}

		} catch (SQLException e) {
			throw new PersistanceException(e);
		} finally {
			// clotures des objets JDBC avec ou sans exception
			closeResultSet(resultSet);
			closeStatement(statement);
			closeConnection(cn);
		}
		return promotions;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.interfaceDAO.IPromotionDAO#deletePromotion(org.imie.transverse
	 * .DTO.Promotion)
	 */
	@Override
	public void deletePromotion(Promotion promotion)
			throws PersistanceException {
		Connection cn = null;
		Statement statement = null;
		PersistanceException persistanceException = null;

		IPersonneDAO personneDAO = new PersonneDAO();
		List<Personne> personnes = new ArrayList<Personne>();

		try {

			cn = openJDBC();
			cn.setAutoCommit(false);

			Personne personne = new Personne();
			Promotion promotionNull = new Promotion();
			promotionNull.setId(-1);
			personne.setPromotion(promotion); 
			personnes = personneDAO.rechercherPersonne(personne);
			for (Personne personneToUpdate : personnes) {
				personneToUpdate.setPromotion(promotionNull);
				personneDAO.updatePersonne(personneToUpdate);
			}
			
			String deleteQuery = "delete from promotion where id="
					.concat(promotion.getId().toString());
			statement = cn.createStatement();
			statement.executeUpdate(deleteQuery);

			cn.commit();
		} catch (SQLException e) {
			try {

				cn.rollback();
			} catch (SQLException e1) {
				persistanceException = new PersistanceException(e1);
			}
			throw new PersistanceException(e);
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
	 * org.imie.interfaceDAO.IPromotionDAO#updatePromotion(org.imie.transverse
	 * .DTO.Promotion)
	 */
	@Override
	public Promotion updatePromotion(Promotion promotionToUpdate)
			throws PersistanceException {
		Connection cn = null;
		PreparedStatement statement = null;
		PersistanceException persistanceException = null;
		Promotion retour = null;
		ResultSet executeUpdateResult = null;
		try {

			cn = openJDBC();
			cn.setAutoCommit(false);

			String affectation = "";
			if (promotionToUpdate.getLibelle() != null) {
				affectation = concatPreparedStatementString(affectation,
						"libelle=?");
			}
			String updateQuery = "update promotion set ".concat(affectation)
					.concat(" where id=")
					.concat(promotionToUpdate.getId().toString())
					.concat("  returning libelle,id");
			statement = cn.prepareStatement(updateQuery);

			Integer compteurValues = 1;
			if (promotionToUpdate.getLibelle() != null) {
				statement.setString(compteurValues++,
						promotionToUpdate.getLibelle());
			}
			// exécution de la requête
			executeUpdateResult = statement.executeQuery();
			if (executeUpdateResult.next()) {
				retour = buildDTO(executeUpdateResult);
			}
			cn.commit();
		} catch (SQLException e) {
			try {

				cn.rollback();
			} catch (SQLException e1) {
				persistanceException = new PersistanceException(e1);
			}
			throw new PersistanceException(e);
		} finally {

			// clotures des objets JDBC avec ou sans exception
			closeResultSet(executeUpdateResult);
			closeStatement(statement);
			closeConnection(cn);
		}
		return retour;

	}

	/**
	 * @param executeUpdateResult
	 * @return
	 * @throws SQLException
	 */
	private Promotion buildDTO(ResultSet executeUpdateResult)
			throws SQLException {
		Promotion retour;
		Promotion newPromotion = new Promotion();
		newPromotion.setLibelle(executeUpdateResult.getString("libelle"));
		newPromotion.setId(executeUpdateResult.getInt("id"));
		retour = newPromotion;
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
