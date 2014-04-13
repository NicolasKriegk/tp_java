package fr.imie.poo.chainResp;

public class Distributeur {

	public static void main(String[] args) {
		Compartiment premierCompartiment = new Compartiment100();
		System.out.format("Detail :%s%n", premierCompartiment.retirer(380));
	}
	
}
