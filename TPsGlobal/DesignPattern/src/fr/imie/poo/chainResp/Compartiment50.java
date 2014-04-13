package fr.imie.poo.chainResp;

public class Compartiment50 extends Compartiment {


	public Compartiment50() {
		suivant = new Compartiment20();
	}

	public String retirer(int montant) {
		int quantite, reste;
		quantite = montant / 50;
		reste = montant % 50;
		return String.format("%d*50, %s",quantite, suivant.retirer(reste));
	}

}
