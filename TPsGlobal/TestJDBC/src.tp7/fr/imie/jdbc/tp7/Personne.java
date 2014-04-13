package fr.imie.jdbc.tp7;

import java.util.Date;


public class Personne {

	private String nom = null;
	private String prenom = null;
	private Date dateNaissance = null;
	private String passwd = "P@sswordJava";
	private int Id = -1;
	
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
	

}
