package fr.imie.jdbc.tp3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Persist {

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
	 * @throws SQLException
	 */
	public List<Personne> personSearch(String searchElement) throws SQLException{
		//initialisation valeurs de retour
		personnes = new ArrayList<Personne>();
		try{
			//ouverture connexion BDD
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/imie", "postgres", "postgres");
			//preparation requete, utilisation de prepared statement
			query.setLength(0);
			query.append("SELECT nom,prenom,datenaiss FROM personne WHERE LOWER(nom) LIKE LOWER(?);");
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
				personne.setDateNaissance(rs.getDate("datenaiss"));
				//nouvelle ligne dans List<Personne>
				personnes.add(personne);
			}
			
			//passage valeur de retour
			return personnes;
			
		}		
		catch(SQLException e){
//			System.out.println("Erreur de connexion");
			throw new RuntimeException(e);
		}
		finally{
			if(rs!=null && !rs.isClosed()){
				rs.close();
			}
			if(stmt!=null && !stmt.isClosed()){
				stmt.close();
			}
			if(conn!=null && !conn.isClosed()){
				conn.close();
			}
		}

	
	}

}
