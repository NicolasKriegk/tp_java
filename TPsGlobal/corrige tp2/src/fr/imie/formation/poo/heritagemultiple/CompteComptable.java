package fr.imie.formation.poo.heritagemultiple;

import java.util.List;

public class CompteComptable extends Compte implements Comptable {

	private List<String> journal;

	public List<String> getJournal() {
		return journal;
	}

	public void setJournal(List<String> journal) {
		this.journal = journal;
	}
	
}
