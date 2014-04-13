package fr.imie.poo.genericite;

import java.io.IOException;

public class StringSaisissable implements Saisissable {

	private String s;
	public StringSaisissable(String s){this.s=s;}
	public void saisie(ConsoleStandard c) throws IOException{
	s=c.lireChaine();
	}
	public String toString(){
	return s;
	}
		
		

}
