package fr.imie.jdbc.tp6;

import fr.imie.jdbc.tp6.Exception.PersistanceException;
import fr.imie.jdbc.tp6.Ihm.Ihm;
import fr.imie.jdbc.tp6.InterfaceIhm.IIhm;




/*
 * idem TP5 avec interface de DAO (data access object)
 * 
 */

public class TestJdbc {

	/**
	 * @param args
	 * @throws PersistanceException
	 */
	public static void main(String[] args) throws PersistanceException{

		//appel IHM
		IIhm ihm = new Ihm(); 
		ihm.personSearch();
			
		
		
	}

}
