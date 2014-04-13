package org.imie.transverse.factory;

import java.sql.Connection;

import org.imie.interfaceDAO.IPersonneDAO;
import org.imie.interfaceDAO.IPromotionDAO;
import org.imie.interfaceservice.IGestionEcoleService;

public interface AbstractFactory {
	/**
	 * @return
	 */
	public abstract IGestionEcoleService creategGestionEcoleService();
	/**
	 * @return
	 */
	public abstract IPersonneDAO createPersonneDAO();
	
	/**
	 * @return
	 */
	public abstract IPromotionDAO createPromotionDAO();

	
}
