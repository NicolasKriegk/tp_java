package fr.imie.poo.tp4;

import java.util.ArrayList;
import java.util.List;

public class Question {

//Attributs
	private String titre = null; 
	private List<Reponse> reponses = new ArrayList<Reponse>();
	private List<Integer> bonneReponses = new ArrayList<Integer>();
	
//Constructeurs
	public Question(String titre) {
		this.titre = titre;
		
	}
	
//Methodes
	//Saisie titre de la question
//	public void setTitre(String titre) {
//		this.titre = titre;
//	}
	
	//Affichage titre de la question
	public void affichageTitre() {
		System.out.format("%s%n", titre);
		//return titre;
	}
	
	//Saisie proposition reponse et sauvegarde numero reponse (index+1) si bonne reponse
	public void ajouterReponse(String libelle, boolean bonneRep) {
		Reponse rep = new Reponse(libelle, bonneRep);
		this.reponses.add(rep);
		if (bonneRep){
			int index = reponses.indexOf(rep);
			bonneReponses.add(index+1);
		}
	}
	
	//Propositions de reponse
	public List<Integer> affichageProposition() {
		for (Reponse reponse : reponses) {
			System.out.format("%d - %s%n", reponses.indexOf(reponse)+1, reponse.getProposition());
		}	
		return bonneReponses;
	}
	
	
	
	
}


//question.ajouterReponse("Une ile de l'ocean Atlantique", false);
//question.ajouterReponse("Une ile de l'ocean Indien", true);
//question.ajouterReponse("Moins de 20 millions", false);
//question.ajouterReponse("Entre 20 et 50 millions", false);
//question.ajouterReponse("Plus de 50 millions", true);
// 