package fr.imie.poo.tp5;


public abstract class Compte {

//Attributs
	private String numero;
	private Titulaire titulaire;
	private double solde;
	private double decouvertMax = 800.0;
	private double debitMax = 1000.0;
	
//Constructeurs
	public Compte(Titulaire t, double solde, double decouvertMax, double debitMax){
		this.titulaire = t;
		this.solde = solde;
		this.decouvertMax = decouvertMax;
		this.debitMax = debitMax;
		
		//Fonctions.NumToNbCharString(source, size)
	}

	
//Methodes
//	public abstract String getNumero();
//	public abstract String getTitulaire();
//	public abstract double getDecouvMax();
//	public abstract double getDebitMax();
//	public abstract boolean getSituationOK();
//	public abstract double getDebitAutor();
//	public abstract void setDecouvMax();
//	public abstract void setDebitMax();

	protected void setNumero(int numero, String prefixe){
		int size = 8;	//numero de compte : 1 lettre et 8 chiffres
//		NumberFormat nf = NumberFormat.getInstance();
//		nf.setMinimumIntegerDigits(size);
//		nf.setMaximumIntegerDigits(size);
//		nf.setGroupingUsed(false);
		this.numero = Fonctions.NumToNbCharString(numero, size);
		this.numero = prefixe + this.numero;
	}
	
	public String getNumero() {
		return numero;
	}

//	protected void setTitulaire(String nom) {
//		this.titulaire = nom;
//	}

	public Titulaire getTitulaire() {
		return titulaire;
	}

//	protected void setSolde(double solde) {
//		this.solde = solde;
//	}

	public double getSolde() {
		return solde;
	}

//	public void setDecouvertMax(double decouvertMax) {
//		this.decouvertMax = decouvertMax;
//	}

	public double getDecouvertMax() {
		return decouvertMax;
	}

//	public void setDebitMax(double debitMax) {
//		this.debitMax = debitMax;
//	}
	
	public double getDebitMax() {
		return debitMax;
	}

	public boolean getSituationOK() {
		return ( solde >= 0 );
	}

	public double getDebitAutor() {
		// TODO Auto-generated method stub
		return Math.max(solde, debitMax);
	}

}

//Compte:
//numero identifiant string (ouverture, non modifiable)
//prefixe
//titulaire (non modifiable)
//solde  (0 defaut, facult creation)
//decouvert max (arbitraire à la création, 800 defaut, facult creation)
//debit max (arbitraire à la création, 1000 defaut, facult creation)
