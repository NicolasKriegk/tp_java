package org.imie.transverse.factory;

import org.imie.DAO.PersonneDAO;
import org.imie.DAO.PromotionDAO;
import org.imie.Transaction.TSGestionEcoleServiceProxy;
import org.imie.Transaction.TSPersonneDAOProxy;
import org.imie.Transaction.TSPromotionDAOProxy;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceservice.IGestionEcoleService;
import org.imie.service.GestionEcoleService;

public class ConcreteSingletonFactory implements AbstractFactory {

	IPersonneDAO personneDAO = new TSPersonneDAOProxy(new PersonneDAO());
	IPromotionDAO promotionDAO = new TSPromotionDAOProxy(new PromotionDAO());
	IGestionEcoleService gestionEcoleService = new TSGestionEcoleServiceProxy(new GestionEcoleService(this));
	static ConcreteSingletonFactory instance =null;
	
	private void ConcreteSingletonFactory() {
		// TODO Auto-generated constructor stub
	}
	
	static public synchronized ConcreteSingletonFactory getInstance(){
		if (instance==null){
			instance = new ConcreteSingletonFactory();
		}
		return instance;
	}
	
	/* (non-Javadoc)
	 * @see org.imie.transverse.factory.AbstractFactory#creategGestionEcoleService()
	 */
	@Override
	public IGestionEcoleService creategGestionEcoleService() {
		return gestionEcoleService;
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
		return promotionDAO;
	}


}
