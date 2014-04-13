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

import org.imie.Transaction.IJDBCCompatible;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.qualifier.TransactionBeanJDBC;
import org.imie.qualifier.TransactionModeJDBC;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.ApplicationException;
import org.imie.transverse.exception.PersistanceException;

@TransactionBeanJDBC(TransactionModeJDBC.REAL)
public class PromotionDAO implements IPromotionDAO {

	private Connection connection;

	@Override
	public Promotion insertPromotion(Promotion promotion)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC
		Promotion retour = null;
		PreparedStatement statement = null;
		String columns = "";
		String values = "";

		if (promotion.getLibelle() != null) {
			columns = concatPreparedStatementString(columns, "libelle");
			values = concatPreparedStatementString(values, "?");
		}
		try {

			// construcution de la requête paramétrée
			String query = "insert into promotion(libelle) values(?) returning libelle,id";
			statement = this.connection.prepareStatement(query);
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

		} catch (SQLException e) {

			throw new PersistanceException(e);
		}
		return retour;
	}

	@Override
	public List<Promotion> rechercherPromotion(Promotion promotion)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC

		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Promotion> promotions = new ArrayList<Promotion>();
		try {

			// construcution de la requête paramétrée
			String query = "select id,libelle from promotion ";
			String condition = "";
			if (promotion.getLibelle() != null) {
				condition = concatConditionString(condition, "libelle like ? ");
			}
			if (promotion.getId() != null) {
				condition = concatConditionString(condition, "id = ? ");
			}

			statement = this.connection.prepareStatement(query
					.concat(condition));
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

		Statement statement = null;

		try {

			String deleteQuery = "delete from promotion where id="
					.concat(promotion.getId().toString());
			statement = this.connection.createStatement();
			statement.executeUpdate(deleteQuery);

		} catch (SQLException e) {

			throw new PersistanceException(e);
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

		PreparedStatement statement = null;
		PersistanceException persistanceException = null;
		Promotion retour = null;
		ResultSet executeUpdateResult = null;
		try {

			String updateQuery = "update promotion set libelle=? where id="
					.concat(promotion.getId().toString()).concat(
							"  returning libelle,id");

			statement = this.connection.prepareStatement(updateQuery);

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
		} catch (SQLException e) {
			throw new PersistanceException(e);
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
	public void endTransaction(ApplicationException applicationException)
			throws ApplicationException {
		throw new UnsupportedOperationException();

	}

}
