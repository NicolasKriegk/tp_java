package fr.imie.jdbc.Main;

import fr.imie.jdbc.Exception.PersistanceException;
import fr.imie.jdbc.Ihm.Ihm;
import fr.imie.jdbc.InterfaceIhm.IIhm;




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
		ihm.menu();
			
		
		
	}

}
