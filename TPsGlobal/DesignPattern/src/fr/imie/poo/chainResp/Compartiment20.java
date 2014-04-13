package fr.imie.poo.chainResp;

public class Compartiment20 extends Compartiment {

	public Compartiment20() {
		suivant = new Compartiment10();
	}

	public String retirer(int montant) {
		int quantite, reste;
		quantite = montant / 20;
		reste = montant % 20;
		return String.format("%d*20, %s",quantite, suivant.retirer(reste));
	}

}
