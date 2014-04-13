package fr.imie.poo.tp5;

import java.text.NumberFormat;

public abstract class Fonctions {

	/*
	 * Mise en forme chaine de caracteres sur nombre donne
	 */
	//code factorise
	private static<X> String NumToNbCharStringGen(X source, int size){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(size);
		nf.setMaximumIntegerDigits(size);
		nf.setGroupingUsed(false);
		return nf.format(source);
	}
	//source type double
	public static String NumToNbCharString(double source, int size){
		return NumToNbCharStringGen(source, size);
	}
	//source type int
	public static String NumToNbCharString(int source, int size){
		return NumToNbCharStringGen(source, size);
	}

}
