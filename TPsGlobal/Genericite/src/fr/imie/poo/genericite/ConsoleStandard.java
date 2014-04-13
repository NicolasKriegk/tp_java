package fr.imie.poo.genericite;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleStandard {
	private Scanner sc = new Scanner(System.in);
	public String lireChaine() throws IOException{
	return sc.nextLine(); 
	}
}
