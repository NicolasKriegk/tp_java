package fr.imie.formation.poo.heritagemultiple;

import java.util.List;

public class CompteBudgetaireComptable extends Compte implements Budgetaire, Comptable {

	private Budgetaire budgetaire;
	
	private Comptable comptable;
	
	public CompteBudgetaireComptable() {
		budgetaire = new CompteBudgetaire();
		comptable = new CompteComptable();
	}
	
	public List<String> getJournal() {
		return comptable.getJournal();
	}
	
	public String getPrevisionnel() {
		return budgetaire.getPrevisionnel();
	}
	
	public void setJournal(List<String> journal) {
		comptable.setJournal(journal);
	}
	
	public void setPrevisionnel(String previsionnel) {
		budgetaire.setPrevisionnel(previsionnel);
	}
	
}
