package fr.imie.poo.genericite;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {

//		Paire<Integer,String> p = new Paire<Integer,String>(9,"plus grand chiffre");
//		Integer i=p.getFst();
//		String s=p.getSnd();
//		System.out.println(p);

		Paire<Integer,String> p5 = new Paire<Integer,String>(9,"plus grand chiffre");
		Integer[] tab=new Integer[2];
		Paire.copieFstTab(p5,tab,0);
		Paire<Integer,Integer> p2 = new Paire<Integer,Integer>(9,10);
		System.out.println(p5.memeFst(p2));

		boolean flag = p2.getClass()==p5.getClass();

//		ConsoleStandard c = new ConsoleStandard();
//		StringSaisissable s1 = new StringSaisissable("");
//		StringSaisissable s2 = new StringSaisissable("");
//		PaireSaisissable<StringSaisissable,StringSaisissable> mp
//		= new PaireSaisissable<StringSaisissable,StringSaisissable>
//				(s1,s2);
//		try {
//			mp.saisie(c);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	

		Paire<?,?> p3 = new Paire<Integer, String>();
		
		System.out.println(p3);
		
		
	}

}
