package fr.imie.jdbc.tp3;

import java.sql.SQLException;

/*
 * idem TP2 avec prepared statement et boucle d'IHM pour recommencer
 * separer en couches
 */

public class Tp3TestJdbc {

	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException{

		
		//appel IHM
		IHM ihm = new IHM(); 
		ihm.personSearch();
		
		
		
	}

}
