package fr.imie.poo.tp5;

public class Banque {

	public static void main(String[] args) {

		Titulaire personne = new Personne();
		Titulaire entreprise = new Entreprise();
		Compte c1 = new CompteCourant(personne, 1000);
		Compte c2 = new Livret(personne, 50000, 5000, 6000);
		
		System.out.format("c1: %s%n", c1.getNumero());
		System.out.format("c2: %s%n", c2.getNumero());
		
	}

//	private AffichageInfos(Compte compte){
//		System.out.format("Nom: ", compte.getTitulaire());
//		System.out.format("Nom: ", compte.getTitulaire());
//		
//		
//	}
}
