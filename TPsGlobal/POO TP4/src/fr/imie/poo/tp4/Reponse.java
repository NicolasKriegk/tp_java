package fr.imie.poo.tp4;

public class Reponse {

	private String proposition = null;
	private boolean bonneRep = false;
	
	public Reponse(String proposition, boolean bonneRep){
		this.proposition = proposition;
		this.bonneRep = bonneRep;
	}
	
	public String getProposition(){
		return proposition;
	}
	
	public boolean getSolution(){
		return bonneRep;
	}
	
}
