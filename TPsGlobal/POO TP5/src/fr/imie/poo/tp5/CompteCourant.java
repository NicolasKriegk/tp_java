package fr.imie.poo.tp5;

public class CompteCourant extends Compte {

	//Attributs
	private static int nbComptes = 0;

	//Constructeurs
	public CompteCourant(Titulaire t){
		this(t, 0, 800, 1000);
	}
	
	public CompteCourant(Titulaire t, double solde){
		this(t, solde, 800, 1000);
	}
	
	public CompteCourant(Titulaire t, double solde, double decouvertMax, double debitMax){
		super(t, solde, decouvertMax, debitMax);
		nbComptes += 1;
		super.setNumero(nbComptes, "C");
	}
	
}
