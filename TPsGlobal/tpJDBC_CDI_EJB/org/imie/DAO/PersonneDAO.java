package org.imie.DAO;

import java.sql.Connection;
import java.sql.Date;
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
public class PersonneDAO implements IPersonneDAO {
	private volatile Connection connection;

	@Override
	public Personne verifierAuthPersonne(Personne personne) {
		// déclaration des variables necessaires à JDBC
		// Connection cn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Personne retour = null;
		try {

			// cn = openJDBC();
			// construcution de la requête paramétrée
			String query = "select id,nom,prenom,datenaiss,passw,promotionid from personne ";
			String condition = "";
			if (personne.getNom() != null) {
				condition = concatConditionString(condition, "nom = ? ");
			}
			if (personne.getPassw() != null) {
				condition = concatConditionString(condition, "passw = ? ");
			}

			statement = connection.prepareStatement(query.concat(condition));
			// affectation des valeurs de paramètres
			Integer compteurCondition = 1;
			if (personne.getNom() != null) {
				statement.setString(compteurCondition++, personne.getNom());
			}
			if (personne.getPassw() != null) {
				statement.setString(compteurCondition++, personne.getPassw());
			}

			// exécution de la requête
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				retour = buildDTO(resultSet);
			}

		} catch (SQLException e) {
			throw new PersistanceException(e);
		} finally {
			// clotures des objets JDBC avec ou sans exception
			try {
				closeJDBC(statement, resultSet);
			} catch (PersistanceException e1) {
				throw e1;
			}
		}
		return retour;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.IPersonneDAO#rechercherPersonne(java.lang.String)
	 */
	@Override
	public List<Personne> rechercherPersonne(String nomInput)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Personne> personnes = new ArrayList<Personne>();
		try {

			String query = "select id,nom,prenom,datenaiss,passw,promotionid from personne where nom like ?";
			statement = connection.prepareStatement(query);
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

			try {
				closeJDBC(statement, resultSet);
			} catch (PersistanceException e1) {
				throw e1;
			} finally {
			}

		}
		return personnes;
	}

	@Override
	public Personne insertPersonne(Personne personne)
			throws PersistanceException {

		// déclaration des variables necessaires à JDBC
		Personne retour = null;
		PreparedStatement statement = null;
		PersistanceException persistanceException = null;

		try {

			// cn = openJDBC();
			// cn.setAutoCommit(false);
			// construcution de la requête paramétrée
			String query = "insert into personne (nom,prenom,datenaiss,passw,promotionid) values (?,?,?,?,?) returning nom,prenom,datenaiss,passw,promotionid,id";
			statement = connection.prepareStatement(query);
			// affectation des valeurs de paramètres
			Integer compteurValues = 1;
			if (personne.getNom() != null) {
				statement.setString(1, personne.getNom());
			} else {
				statement.setNull(1, Types.VARCHAR);
			}

			if (personne.getPrenom() != null) {
				statement.setString(2, personne.getPrenom());
			} else {
				statement.setNull(2, Types.VARCHAR);
			}

			if (personne.getDateNaiss() != null) {
				java.sql.Date dateNaiss = null;
				dateNaiss = new Date(personne.getDateNaiss().getTime());
				statement.setDate(3, dateNaiss);
			} else {
				statement.setNull(3, Types.DATE);
			}

			if (personne.getPassw() != null) {
				statement.setString(4, personne.getPassw());
			} else {
				statement.setNull(4, Types.VARCHAR);
			}

			if (personne.getPromotion() != null) {
				statement.setInt(5, personne.getPromotion().getId());
			} else {
				statement.setNull(5, Types.INTEGER);
			}

			// exécution de la requête
			ResultSet executeUpdateResult = statement.executeQuery();
			if (executeUpdateResult.next()) {
				retour = buildDTO(executeUpdateResult);
			}

		} catch (SQLException e) {
			throw new PersistanceException(e);
		} finally {
			try {
				closeJDBC(statement, null);
			} catch (PersistanceException e1) {
				throw e1;
			}
		}
		return retour;
	}

	@Override
	public List<Personne> rechercherPersonne(Personne personne)
			throws PersistanceException {
		// déclaration des variables necessaires à JDBC
		// Connection cn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<Personne> personnes = new ArrayList<Personne>();
		try {

			// cn = openJDBC();
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
			if (personne.getId() != null) {
				condition = concatConditionString(condition, "id = ? ");
			}

			statement = connection.prepareStatement(query.concat(condition));
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
				statement.setInt(compteurCondition++, personne.getPromotion()
						.getId());
			}
			if (personne.getId() != null) {
				statement.setInt(compteurCondition++, personne.getId());
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
			try {
				closeJDBC(statement, resultSet);
			} catch (PersistanceException e1) {
				throw e1;
			} finally {
				// closeJDBC(cn);
			}
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
		// Connection cn = null;
		Statement statement = null;
		PersistanceException persistanceException = null;
		try {

			// cn = openJDBC();
			// cn.setAutoCommit(false);

			String deleteQuery = "delete from personne where id="
					.concat(personne.getId().toString());
			statement = connection.createStatement();
			statement.executeUpdate(deleteQuery);

			// cn.commit();
		} catch (SQLException e) {
			// try {

			// cn.rollback();
			// } catch (SQLException e1) {
			throw new PersistanceException(e);
			// }
		} finally {
			try {
				closeJDBC(statement, null);
			} catch (PersistanceException e1) {
				throw e1;
			} finally {
				// closeJDBC(cn);
			}
		}
	}

	@Override
	public Personne updatePersonne(Personne personne)
			throws PersistanceException {

		// Connection cn = null;
		Personne retour = null;
		// try {
		// cn = openJDBC();
		// cn.setAutoCommit(false);
		retour = this.updatePersonne(personne, this.connection);
		// cn.commit();
		// } catch (SQLException e) {
		// try {
		// cn.rollback();
		// } catch (SQLException e1) {
		// throw new PersistanceException(e);
		// }
		// } finally {
		// clotures des objets JDBC avec ou sans exception
		// closeJDBC(cn);
		// }
		return retour;

	}

	/**
	 * @param personne
	 * @param connection
	 * @return
	 * @throws PersistanceException
	 */
	public Personne updatePersonne(Personne personne, Connection connection)
			throws PersistanceException {
		PreparedStatement statement = null;
		PersistanceException persistanceException = null;
		Personne retour = null;
		ResultSet executeUpdateResult = null;

		try {

			// if ("croche".equals(personne.getNom())) {
			// throw new RuntimeException("BullShit");
			// }

			String updateQuery = "update personne set nom=?,prenom=?,datenaiss=?,passw=?,promotionid=? where id="
					.concat(personne.getId().toString())
					.concat("  returning nom,prenom,datenaiss,passw,promotionid,id");
			statement = this.connection.prepareStatement(updateQuery);

			if (personne.getNom() != null) {
				statement.setString(1, personne.getNom());
			} else {
				statement.setNull(1, Types.VARCHAR);
			}

			if (personne.getPrenom() != null) {
				statement.setString(2, personne.getPrenom());
			} else {
				statement.setNull(2, Types.VARCHAR);
			}

			if (personne.getDateNaiss() != null) {
				java.sql.Date dateNaiss = null;
				dateNaiss = new Date(personne.getDateNaiss().getTime());
				statement.setDate(3, dateNaiss);
			} else {
				statement.setNull(3, Types.DATE);
			}

			if (personne.getPassw() != null) {
				statement.setString(4, personne.getPassw());
			} else {
				statement.setNull(4, Types.VARCHAR);
			}

			if (personne.getPromotion() != null) {
				statement.setInt(5, personne.getPromotion().getId());
			} else {
				statement.setNull(5, Types.INTEGER);
			}

			// exécution de la requête
			executeUpdateResult = statement.executeQuery();
			if (executeUpdateResult.next()) {
				retour = buildDTO(executeUpdateResult);
			}
		} catch (SQLException e) {
			throw new PersistanceException(e);
		} finally {
			closeJDBC(statement, executeUpdateResult);
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
		newPersonne.setDateNaiss(executeUpdateResult.getDate("datenaiss"));
		newPersonne.setId(executeUpdateResult.getInt("id"));
		newPersonne.setPassw(executeUpdateResult.getString("passw"));

		Integer promotionId = executeUpdateResult.getInt("promotionid");
		if (executeUpdateResult.wasNull()) {
			newPersonne.setPromotion(null);
		} else {
			Promotion promotionSearch = new Promotion();
			promotionSearch.setId(promotionId);
			newPersonne.setPromotion(promotionSearch);
		}

		retour = newPersonne;
		return retour;
	}

	/**
	 * @param cn
	 * @param statement
	 * @param persistanceException
	 * @return
	 * @throws PersistanceException
	 */
	private void closeJDBC(Statement statement, ResultSet resultSet)
			throws PersistanceException {
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

	@Override
	public void setconnection(Connection con) {
		this.connection = con;

	}

	@Override
	public Connection getConnection() {
		return connection;
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
