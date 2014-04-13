package org.imie.transverse.DTO;

import java.util.Date;

public class Personne {
	String nom;
	String prenom;
	Date dateNaiss;
	String passw;
	Integer id;
	Promotion promotion;
	
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the dateNaiss
	 */
	public Date getDateNaiss() {
		return dateNaiss;
	}
	/**
	 * @param dateNaiss the dateNaiss to set
	 */
	public void setDateNaiss(Date dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	/**
	 * @return the passw
	 */
	public String getPassw() {
		return passw;
	}
	/**
	 * @param passw the passw to set
	 */
	public void setPassw(String passw) {
		this.passw = passw;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the promotion
	 */
	public Promotion getPromotion() {
		return promotion;
	}
	/**
	 * @param promotion the promotion to set
	 */
	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	

}
