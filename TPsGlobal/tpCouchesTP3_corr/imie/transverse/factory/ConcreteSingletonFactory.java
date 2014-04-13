package org.imie.transverse.factory;

import org.imie.DAO.PersonneDAO;
import org.imie.DAO.PromotionDAO;
import org.imie.Transaction.NTSGestionEcoleServiceProxy;
import org.imie.Transaction.SimonPersonneDAOProxy;
import org.imie.Transaction.TSPersonneDAOProxy;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.service.GestionEcoleService;

public class ConcreteSingletonFactory implements AbstractFactory {

	IPersonneDAO personneDAO = new TSPersonneDAOProxy(new PersonneDAO());
	
	/* (non-Javadoc)
	 * @see org.imie.transverse.factory.AbstractFactory#creategGestionEcoleService()
	 */
	@Override
	public IGestionEcoleService creategGestionEcoleService() {
		return new NTSGestionEcoleServiceProxy(new GestionEcoleService(this));
	}

	/* (non-Javadoc)
	 * @see org.imie.transverse.factory.AbstractFactory#createPersonneDAO()
	 */
	@Override
	public IPersonneDAO createPersonneDAO() {
		return personneDAO;
	}

	/* (non-Javadoc)
	 * @see org.imie.transverse.factory.AbstractFactory#createPromotionDAO()
	 */
	@Override
	public IPromotionDAO createPromotionDAO() {
		return new PromotionDAO();
	}

}
