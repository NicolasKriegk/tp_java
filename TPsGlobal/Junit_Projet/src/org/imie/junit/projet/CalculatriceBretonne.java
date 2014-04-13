package org.imie.junit.projet;

public class CalculatriceBretonne {

	//retourne traitement des 2 parametres
	//si l'un est negatif, retourne -1
	//si division par 0, retourne 0
	
	
	public long addition(long a, long b){
		long retour = 0; 
		if(a<0 || b<0){
			retour = -1; 
		}
		else{
			retour = a + b;
		}
		return retour;
	}
	
	public long multiplication(long a, long b){
		long retour = 0; 
		if(a<0 || b<0){
			retour = -1; 
		}
		else{
			retour = a * b;
		}
		return retour;
	}
	
	public long division(long a, long b){
		long retour = 0; 
		if(a<0 || b<0){
			retour = -1; 
		}
		else if(b==0){
			retour = 0; 
		}
		else{
			retour = a / b;
		}
		return retour;
	}
	
}
