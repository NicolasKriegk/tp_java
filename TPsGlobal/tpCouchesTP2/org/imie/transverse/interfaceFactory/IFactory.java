package org.imie.transverse.interfaceFactory;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceServices.IService;

public interface IFactory {

	public IPersonneDAO createPersonDAO();
	public IPromotionDAO createPromotionDAO();
	public IService createService();
	
}
