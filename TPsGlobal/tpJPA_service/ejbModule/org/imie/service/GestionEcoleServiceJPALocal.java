package org.imie.service;

import java.util.List;

import javax.ejb.Local;

import org.imie.transverse.exception.ServiceException;

import model.Personne;
import model.Promotion;

@Local
public interface GestionEcoleServiceJPALocal {
	//Personne	
    public List<Personne> rechercherPersonne(Personne personne);
	public void deletePersonne(Personne personne);
	public Personne updatePersonne(Personne personne);
	public Personne insertPersonne(Personne personne);
	public Personne verifierAuthPersonne(Personne personne) throws ServiceException ;

	//Promotion	
	public Promotion insertPromotion(Promotion promotion);
	public void deletePromotion(Promotion promotion);
	public List<Promotion> rechercherPromotion(Promotion promotion);
	public Promotion updatePromotion(Promotion promotionToUpdate);
	public void duplicatePersonne(Personne personneToDuplicate, Integer nbFois);

}
