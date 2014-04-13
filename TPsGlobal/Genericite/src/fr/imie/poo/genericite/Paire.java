package fr.imie.poo.genericite;

public class Paire<A, B> {

	private A fst;
	private B snd;
	private static Integer nbInstances=0;


	public Paire(){nbInstances += 1;getNbInstances();}
	public Paire(A f, B s){fst=f; snd=s; nbInstances += 1;getNbInstances();}
	public A getFst(){return fst;}
	public B getSnd(){return snd;}
	public void setFst(A a){fst=a;}
	public void setSnd(B b){snd=b;}
	public String toString(){return getFst()+"-"+getSnd();}
	
	public static<X,Y> void copieFstTab(Paire<X,Y> p,X[] tableau, int i){
		tableau[i]=p.getFst();
		}
	public <C> boolean memeFst(Paire<A,C> p) {return p.getFst()==getFst();}

	private static void getNbInstances(){
		System.out.format("Nombre d'instances = %s%n", nbInstances);
	}




}
