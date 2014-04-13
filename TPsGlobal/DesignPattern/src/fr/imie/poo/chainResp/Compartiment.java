package fr.imie.poo.chainResp;

public abstract class Compartiment {

	protected Compartiment suivant;
	
	public Compartiment() {
		
	}
	
	public abstract String retirer(int montant);
	
	
}
