package fr.imie.poo.chainResp;

public class Compartiment10 extends Compartiment {

	public String retirer(int montant) {
		int quantite, reste;
		quantite = montant / 10;
//		reste = montant % 10;
		return String.format("%d*10",quantite);
	}

}
