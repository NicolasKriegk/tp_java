package org.imie.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.PersistanceException;

public class PromotionDAO implements IPromotionDAO {

	private Connection connection;

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
			String query = "insert into promotion(libelle) values(?) returning libelle,id";
			statement = cn.prepareStatement(query);
			// affectation des valeurs de paramètres

			if (promotion.getLibelle() != null) {
				statement.setString(1, promotion.getLibelle());
			} else {
				statement.setNull(1, Types.NVARCHAR);
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
				persistanceException = new PersistanceException(e);
			}
			throw new PersistanceException(e);
		} finally {
			persistanceException = closeJDBC(cn, statement, null);
			if (persistanceException != null) {
				throw persistanceException;
			}
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
			PersistanceException persistanceException = null;
			persistanceException = closeJDBC(cn, statement, resultSet);
			if (persistanceException != null) {
				throw persistanceException;
			}
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
		try {

			cn = openJDBC();
			cn.setAutoCommit(false);

			

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
			persistanceException = closeJDBC(cn, statement, null);
			if (persistanceException != null) {
				throw persistanceException;
			}
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
	public Promotion updatePromotion(Promotion promotion)
			throws PersistanceException {
		Connection cn = null;
		PreparedStatement statement = null;
		PersistanceException persistanceException = null;
		Promotion retour = null;
		ResultSet executeUpdateResult = null;
		try {

			cn = openJDBC();
			cn.setAutoCommit(false);

			String updateQuery = "update promotion set libelle=? where id="
					.concat(promotion.getId().toString()).concat(
							"  returning libelle,id");
			statement = cn.prepareStatement(updateQuery);

			if (promotion.getLibelle() != null) {
				statement.setString(1, promotion.getLibelle());
			} else {
				statement.setNull(1, Types.NVARCHAR);
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
			persistanceException = closeJDBC(cn, statement, executeUpdateResult);
			if (persistanceException != null) {
				throw persistanceException;
			}
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
	 * @param persistanceException
	 * @return
	 * @throws PersistanceException
	 */
	private PersistanceException closeJDBC(Connection cn, Statement statement,
			ResultSet resultSet) throws PersistanceException {
		// clotures des objets JDBC avec ou sans exception
		PersistanceException persistanceException = null;
		if (resultSet != null) {
			try {
				if (resultSet != null && !resultSet.isClosed()) {
					resultSet.close();
				}
			} catch (SQLException e) {
				persistanceException = new PersistanceException(e);
			}
		}

		try {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		} catch (SQLException e) {
			persistanceException = new PersistanceException(e);
		}

		try {
			if (cn != null && !cn.isClosed()) {
				cn.close();
			}
		} catch (SQLException e) {
			persistanceException = new PersistanceException(e);
		}

		return persistanceException;

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
				.getConnection("jdbc:postgresql://127.0.0.1:5432/imie",
						"postgres", "postgres");
		return cn;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;

	}

}
