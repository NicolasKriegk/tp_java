package fr.imie.poo.genericite;

public interface Pile<A> {
	boolean estVide();
	void empile(A donnee);
	A depile();
	Integer nbElements();
	A sommet();
}
