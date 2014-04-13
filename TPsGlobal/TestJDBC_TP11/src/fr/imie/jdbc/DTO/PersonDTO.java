package fr.imie.jdbc.DTO;

import java.util.Date;


public class PersonDTO {

	private String nom = null;
	private String prenom = null;
	private Date dateNaissance = null;
	private String passwd = "P@sswordJava";
	private int Id = -1;
	private PromotionDTO promotionDTO = null;
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(int id) {
		this.Id = id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public PromotionDTO getClassDTO() {
		return promotionDTO;
	}
	public void setClassDTO(PromotionDTO classDTO) {
		this.promotionDTO = classDTO;
	}
	
	
}
