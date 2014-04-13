package fr.imie.poo.cours.regex;

import java.text.NumberFormat;
import java.util.regex.*;

public abstract class Fonctions {

	/*
	 * Mise en forme chaine de caracteres sur nombre donne
	 */
	//code factorisé
	private static<X> String NumToNbCharString(X source, int size){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(size);
		nf.setMaximumIntegerDigits(size);
		nf.setGroupingUsed(false);
		return nf.format(source);
	}
	//source type double
	public static String NumToNbCharString(double source, int size){
		return NumToNbCharString(source, size);
	}
	//source type int
	public static String NumToNbCharString(int source, int size){
		return NumToNbCharString(source, size);
	}

	/*
	 * Test de pattern (regex)
	 */
	public static void TestPatternRegex(String[] jeuEssai, boolean etatAttendu, Pattern p){
		Matcher m;
		System.out.format("Normalement %s:%n", (etatAttendu ? "Vrai" : "Faux"));
		for (int i = 0; i <= (jeuEssai.length - 1); i++ ){
			m = p.matcher(jeuEssai[i]);
			boolean flag = !(etatAttendu^m.matches());
			System.out.println(jeuEssai[i]+" --> "+( flag ? "OK" : "Rate!"));
		}
		
	}
}
