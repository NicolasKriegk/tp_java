package fr.imie.poo.chainResp;

public class Compartiment100 extends Compartiment {


	public Compartiment100() {
		suivant = new Compartiment50();
	}

	public String retirer(int montant) {
		int quantite, reste;
		quantite = montant / 100;
		reste = montant % 100;
		return String.format("%d*100, %s",quantite, suivant.retirer(reste));
	}

}
