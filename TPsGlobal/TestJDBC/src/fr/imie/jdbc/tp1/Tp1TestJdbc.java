package fr.imie.jdbc.tp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Tp1TestJdbc {

	public static void main(String[] args) throws SQLException {

//		try {
//			Class.forName("org.postgresql.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}


		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String nom = null;
		String prenom = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date_naiss = null;
		StringBuilder chaine = new StringBuilder();
		
		try{
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/imie", "postgres", "postgres");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT nom,prenom,datenaiss FROM personne");

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
		catch(Exception e){
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
		
	}

}
