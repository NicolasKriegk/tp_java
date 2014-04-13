package fr.imie.jdbc.tp5;



/*
 * idem TP3 avec exception persistance de donnees
 * 
 */

public class TestJdbc {

	/**
	 * @param args
	 * @throws PersistanceException
	 */
	public static void main(String[] args) throws PersistanceException{

		//appel IHM
		IHM ihm = new IHM(); 
		ihm.personSearch();
			
		
		
	}

}
