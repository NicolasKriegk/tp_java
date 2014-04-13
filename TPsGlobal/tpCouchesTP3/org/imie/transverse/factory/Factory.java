package org.imie.transverse.factory;

import org.imie.DAO.PersonneDAO;
import org.imie.DAO.PromotionDAO;
import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceServices.IService;
import org.imie.proxy.ProxyService;
import org.imie.transverse.interfaceFactory.IFactory;

public class Factory implements IFactory {

	/* (non-Javadoc)
	 * @see org.imie.transverse.interfaceFactory.IFactory#createPersonDAO()
	 */
	public IPersonneDAO createPersonDAO() {
		return new PersonneDAO();
	}

	/* (non-Javadoc)
	 * @see org.imie.transverse.interfaceFactory.IFactory#createPromotionDAO()
	 */
	public IPromotionDAO createPromotionDAO() {
		return new PromotionDAO();
	}

	/* (non-Javadoc)
	 * @see org.imie.transverse.interfaceFactory.IFactory#createService()
	 */
	public IService createService() {
		return new ProxyService(this);
	}

}
