package fr.imie.jdbc.tp7;

import fr.imie.jdbc.tp7.Exception.PersistanceException;
import fr.imie.jdbc.tp7.Ihm.Ihm;
import fr.imie.jdbc.tp7.InterfaceIhm.IIhm;




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
