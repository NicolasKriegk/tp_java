package org.imie.launcher;

import java.sql.SQLException;
import java.util.List;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.exception.PersistanceException;
import org.imie.transverse.exception.ServiceException;
import org.imie.transverse.factory.AbstractFactory;
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
