package fr.imie.poo.genericite;

import java.io.IOException;

public class PaireSaisissable<A extends Saisissable, B extends Saisissable>
		implements Saisissable {

	private A fst;
	private B snd;
	public PaireSaisissable(A f, B s){fst=f; snd=s;}
	public A getFst(){return fst;}
	public B getSnd(){return snd;}
	public void setFst(A a){fst=a;}
	public void setSnd(B b){snd=b;}
	public String toString(){return getFst()+"-"+getSnd();}
	public void saisie(ConsoleStandard c) throws IOException{
	System.out.print("Valeur first:"); fst.saisie(c);
	System.out.print("Valeur second:"); snd.saisie(c);
	}





}
