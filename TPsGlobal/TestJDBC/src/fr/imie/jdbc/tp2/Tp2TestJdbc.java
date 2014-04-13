package fr.imie.jdbc.tp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Tp2TestJdbc {

	public static void main(String[] args) throws SQLException {

		//SQL
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//scanner
		Scanner sc = new Scanner(System.in);
		
		//
		String nom = null;
		String prenom = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date_naiss = null;
		StringBuilder chaine = new StringBuilder();
		StringBuilder query = new StringBuilder();
		
		try{
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/imie", "postgres", "postgres");
			stmt = conn.createStatement();
			//preparation requete
			query.append("SELECT nom,prenom,datenaiss FROM personne WHERE LOWER(nom) LIKE LOWER('%");
			System.out.println("Saisir nom ou partie du nom");
			query.append(sc.nextLine());
			query.append("%');");
			System.out.println(query.toString());
			//execution requete
			rs = stmt.executeQuery(query.toString());

			while(rs.next()){
				//recuperation des infos BDD
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				date_naiss = rs.getDate("datenaiss");
				//construction chaine à afficher
				chaine.setLength(0);
				chaine.append(nom);
				chaine.append(", ");
				chaine.append(prenom);
				chaine.append(", ");
				chaine.append(sdf.format(date_naiss));
				//affichage de la chaine
				System.out.println(chaine);
			}
			
			
		}		
		catch(SQLException e){
			System.out.println("Erreur de connexion");
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
	
		sc.close();
		
	}

}
