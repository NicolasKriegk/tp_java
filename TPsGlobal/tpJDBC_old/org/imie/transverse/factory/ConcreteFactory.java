package org.imie.transverse.factory;

import org.imie.DAO.PersonneDAO;
import org.imie.DAO.PromotionDAO;
import org.imie.Transaction.NTSGestionEcoleServiceProxy;
import org.imie.Transaction.TSPersonneDAOProxy;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.service.GestionEcoleService;

public class ConcreteFactory implements AbstractFactory {

	static ConcreteSingletonFactory instance = null;

	private void ConcreteSingletonFactory() {
		// TODO Auto-generated constructor stub
	}

	static public synchronized ConcreteSingletonFactory getInstance() {
		if (instance == null) {
			instance = new ConcreteSingletonFactory();
		}
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.imie.transverse.factory.AbstractFactory#creategGestionEcoleService()
	 */
	@Override
	public IGestionEcoleService creategGestionEcoleService() {
		return new NTSGestionEcoleServiceProxy(new GestionEcoleService(this));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.transverse.factory.AbstractFactory#createPersonneDAO()
	 */
	@Override
	public IPersonneDAO createPersonneDAO() {
		return new TSPersonneDAOProxy(new PersonneDAO());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.transverse.factory.AbstractFactory#createPromotionDAO()
	 */
	@Override
	public IPromotionDAO createPromotionDAO() {
		return new PromotionDAO();
	}

}
