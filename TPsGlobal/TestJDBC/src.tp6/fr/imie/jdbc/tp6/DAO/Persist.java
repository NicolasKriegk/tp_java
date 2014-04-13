package fr.imie.jdbc.tp6.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.imie.jdbc.tp6.Personne;
import fr.imie.jdbc.tp6.Exception.PersistanceException;
import fr.imie.jdbc.tp6.InterfaceDAO.IPersist;

public class Persist implements IPersist  {

	//SQL
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	//Liste de personnes
	private Personne personne = new Personne();
	private List<Personne> personnes = new ArrayList<Personne>();
	
	//
	StringBuilder query = new StringBuilder();
	

	//recherche dans la base
	/**
	 * @param searchElement
	 * @return
	 * @throws PersistanceException
	 */
	public List<Personne> personSearch(String searchElement) throws PersistanceException{
		//initialisation valeurs de retour
		personnes = new ArrayList<Personne>();
		try{
			//ouverture connexion BDD
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test_jdbc", "nicolas", "nicolas");
			//preparation requete, utilisation de prepared statement
			query.setLength(0);
			query.append("SELECT nom,prenom,date_naiss FROM personne WHERE LOWER(nom) LIKE LOWER(?);");
			stmt = conn.prepareStatement(query.toString());
			//completion requete
			stmt.setString(1,"%".concat(searchElement).concat("%"));
			//affichage et execution requete
//			System.out.println(stmt.toString());
			rs = stmt.executeQuery();
			//traitement des resultats
			while(rs.next()){
				//initialisation valeurs tampon
				personne = new Personne();
				//recuperation des infos BDD
				personne.setNom(rs.getString("nom"));
				personne.setPrenom(rs.getString("prenom"));
				personne.setDateNaissance(rs.getDate("date_naiss"));
				//nouvelle ligne dans List<Personne>
				personnes.add(personne);
			}
			
		}		
		catch(SQLException e){
			throw new PersistanceException(e);
		}
		finally{
			//clotures des objets JDBC avec ou sans exception
			PersistanceException persistanceException =null;
			try {
				if(rs!=null && !rs.isClosed()){
					rs.close();
				}
			} catch (SQLException e) {
				persistanceException = new PersistanceException(e);
			}
			try {
				if(stmt!=null && !stmt.isClosed()){
					stmt.close();
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

		//passage valeur de retour
		return personnes;
	
	}

}
