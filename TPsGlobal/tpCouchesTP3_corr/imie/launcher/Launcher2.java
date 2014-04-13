package org.imie.launcher;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.imie.DAO.PersonneDAO;
import org.imie.Transaction.SimonPersonneDAOProxy;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.service.GestionEcoleService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.PersistanceException;
import org.imie.transverse.exception.ServiceException;
import org.imie.transverse.factory.AbstractFactory;
import org.imie.transverse.factory.ConcreteFactory;
import org.imie.transverse.factory.ConcreteSingletonFactory;

public class Launcher2 {

	/**
	 * @param args
	 * @throws ServiceException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ServiceException {
		final AbstractFactory factory = new ConcreteSingletonFactory();
		for (int i = 0; i < 20; i++) {
			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					List<Personne> personnes;
					try {
						// IPersonneDAO dao = PersonneDAOProxy.getInstance();

						IPersonneDAO dao = factory.createPersonneDAO();
						PersistanceException persistanceException=null;
						try {
							dao.beginTransaction();
							personnes = dao.rechercherPersonne(new Personne());
							System.out.println(String.valueOf(
									Thread.currentThread().getId()).concat(
									" : step 1"));
							for (Personne personne : personnes) {
								// dao.beginTransaction();
								personne.setPrenom(personne.getPrenom().concat(
										"X"));
								dao.updatePersonne(personne);
								personne.setPrenom(personne
										.getPrenom()
										.substring(
												0,
												personne.getPrenom().length() - 1));
								dao.updatePersonne(personne);
								// dao.endTransaction();
							}
							System.out.println(String.valueOf(
									Thread.currentThread().getId()).concat(
									" : step 2"));
						} catch (PersistanceException e) {
							persistanceException = e;
						}
						dao.endTransaction(persistanceException );
					} catch (PersistanceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			thread.start();
		}
	}
}
