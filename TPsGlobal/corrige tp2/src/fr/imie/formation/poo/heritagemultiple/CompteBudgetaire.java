package fr.imie.formation.poo.heritagemultiple;

public class CompteBudgetaire extends Compte implements Budgetaire {

	private String previsionnel;
	
	public String getPrevisionnel() {
		return previsionnel;
	}
	
	public void setPrevisionnel(String previsionnel) {
		this.previsionnel = previsionnel;
	}
	
}
