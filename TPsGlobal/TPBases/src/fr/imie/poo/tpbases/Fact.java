package fr.imie.poo.tpbases;

public class Fact {

	public static int fact(int nombre) {
		var resultat = 1;
		
		if (nombre > 1) {
			resultat = nombre * fact(nombre -1);
		}

		return resultat;
	}

}
