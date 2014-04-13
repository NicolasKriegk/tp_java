package fr.imie.poo.tp5;


public class Livret extends Compte {

	//Attributs
	private static int nbComptes = 0;
	
	//Constructeurs
	/*
	 * @param nom
	 */
	public Livret(Titulaire t){
		this(t, 0, 800, 1000);
	}
	
	public Livret(Titulaire t, double solde){
		this(t, solde, 800, 1000);
	}
	
	public Livret(Titulaire t, double solde, double decouvertMax, double debitMax){
		super(t, solde, decouvertMax, debitMax);
		nbComptes += 1;
		super.setNumero(nbComptes, "L");
	}

//	public String getNumero() {
//		return numero;
//	}
//
//	public String getTitulaire() {
//		return titulaire;
//	}
//
//	public double getDecouvMax() {
//		return decouvertMax;
//	}
//
//	public double getDebitMax() {
//		return debitMax;
//	}
//
//	public boolean getSituationOK() {
//		return ( solde >= 0 );
//	}
//
//	public double getDebitAutor() {
//		// TODO Auto-generated method stub
//		return Math.max(solde, debitMax);
//	}
//
//	public void setDecouvMax(double decouvertMax) {
//		this.decouvertMax = decouvertMax;
//	}
//
//	public void setDebitMax(double debitMax) {
//		this.debitMax = debitMax;
//	}
	
}
