package fr.imie.poo.genericite;

import java.util.LinkedList;

public class PileList<A> implements Pile<A> {

	private LinkedList<A> pile;

	public boolean estVide() {
		return pile.isEmpty();
	}

	public void empile(A donnee) {
		pile.addLast(donnee);
	}

	public A depile() {
		A element = pile.getFirst();
		pile.removeFirst();
		return element;
	}

	public Integer nbElements() {
		return pile.size();
	}

	public A sommet() {
		return pile.peek();
	}

}
