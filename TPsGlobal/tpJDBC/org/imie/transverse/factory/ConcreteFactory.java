package org.imie.transverse.factory;

import org.imie.DAO.PersonneDAO;
import org.imie.DAO.PromotionDAO;
import org.imie.Transaction.NTSGestionEcoleServiceProxy;
import org.imie.Transaction.NTSPersonneDAOProxy;
import org.imie.Transaction.NTSPromotionDAOProxy;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.service.GestionEcoleService;

public class ConcreteFactory implements AbstractFactory {

	static ConcreteFactory instance = null;

	private ConcreteFactory() {
		// TODO Auto-generated constructor stub
	}

	static public synchronized ConcreteFactory getInstance() {
		if (instance == null) {
			instance = new ConcreteFactory();
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
		return new NTSPersonneDAOProxy(new PersonneDAO());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.imie.transverse.factory.AbstractFactory#createPromotionDAO()
	 */
	@Override
	public IPromotionDAO createPromotionDAO() {
		return new NTSPromotionDAOProxy(new PromotionDAO());
	}

}
