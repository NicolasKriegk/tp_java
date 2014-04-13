package org.imie.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import model.Personne;
import model.Promotion;

import org.imie.transverse.exception.ServiceException;

/**
 * Session Bean implementation class GestionEcoleServiceJPA
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionEcoleServiceJPA implements GestionEcoleServiceJPARemote, GestionEcoleServiceJPALocal {

    @PersistenceContext
    private EntityManager entityManager;
	
	/**
     * Default constructor. 
     */
    public GestionEcoleServiceJPA() {
        // TODO Auto-generated constructor stub
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Personne> rechercherPersonne(Personne personne) {
    	CriteriaBuilder qb = entityManager.getCriteriaBuilder();
    	
    	CriteriaQuery<Personne> query =qb.createQuery(Personne.class);
    	Root<Personne> personneRoot = query.from(Personne.class);

    	//creation liste de criteres
    	List<Predicate> criteria = new ArrayList<Predicate>();
    	if(personne.getNom() != null) {
    		criteria.add(qb.like(personneRoot.<String>get("nom"), "%"+personne.getNom()+"%"));
    	}
    	if(personne.getPrenom() != null) {
    	criteria.add(qb.like(personneRoot.<String>get("prenom"), "%"+personne.getPrenom()+"%"));
    	}
    	if(personne.getId() != null) {
    	criteria.add(qb.equal(personneRoot.get("id"), personne.getId()));
    	}
    	query.where(criteria.toArray(new Predicate[]{}));

    	List<Personne> result = entityManager.createQuery(query).getResultList();
    	
    	return result;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deletePersonne(Personne personne) {
//		try {
//			personneDAO.deletePersonne(personne);
//		} catch (PersistanceException e) {
//			throw new ServiceException(e);
//		}
		entityManager.find(Personne.class, personne);
    	entityManager.remove(personne);
    	
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Personne updatePersonne(Personne personne) {

		return entityManager.merge(personne);
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Personne insertPersonne(Personne personne) {

//		Personne test = new Personne();
		
		entityManager.persist(personne);
		return null;
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Personne verifierAuthPersonne(Personne personne) {
		Personne retour;
		if ((personne.getNom() == null || personne.getNom().isEmpty())
				|| (personne.getPassw() == null || personne.getPassw()
						.isEmpty())) {
			throw new ServiceException(
					"la personne à authentifier doit renseigner son nom et son passw");

		}

		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
    	
    	CriteriaQuery<Personne> query =qb.createQuery(Personne.class);
    	Root<Personne> personneRoot = query.from(Personne.class);

    	//creation liste de criteres
    	List<Predicate> criteria = new ArrayList<Predicate>();
   		criteria.add(qb.equal(personneRoot.<String>get("nom"), personne.getNom()));
    	criteria.add(qb.equal(personneRoot.<String>get("passw"), personne.getPassw()));
    	query.where(criteria.toArray(new Predicate[]{}));

    	retour = entityManager.createQuery(query).getSingleResult();
    	
    	return retour;
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Promotion insertPromotion(Promotion promotion) {

		entityManager.persist(promotion);
		return promotion;
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deletePromotion(Promotion promotion) {
		entityManager.find(Promotion.class, promotion);
    	entityManager.remove(promotion);
		
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Promotion> rechercherPromotion(Promotion promotion) {
//    	CriteriaBuilder qb = entityManager.getCriteriaBuilder();
//    	
//    	CriteriaQuery<Promotion> query =qb.createQuery(Promotion.class);
////    	Root<Promotion> personneRoot = query.from(Promotion.class);
//
//    	List<Promotion> result = entityManager.createQuery(query).getResultList();
//    	
//    	return result;
		return null;
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Promotion updatePromotion(Promotion promotionToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void duplicatePersonne(Personne personneToDuplicate, Integer nbFois) {
		// TODO Auto-generated method stub
		
	}
    
}
