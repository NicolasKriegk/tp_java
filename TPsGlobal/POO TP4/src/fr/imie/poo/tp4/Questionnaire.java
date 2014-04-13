package fr.imie.poo.tp4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Questionnaire {

//Attributs
	private String titre = null; 
	private List<Question> question = new ArrayList<Question>();
	private int nbRepOK = 0;
	private Question nouvQuestion;
	private List<Integer> bonneReponses = new ArrayList<Integer>();
//	private Iterator questIterator = new Iterator();
	
//Constructeurs
	public Questionnaire(String titre) {
		this.titre = titre;
		
	}
	
//Methodes
	//Saisie titre de la question
//	public void setTitre(String titre) {
//		this.titre = titre;
//	}
	
	//Affichage titre du questionnaire
	public String titre() {
		return titre;
	}
	
	//Saisie question
	public void ajouterQuestion(Question question) {
		this.question.add(question);
	}
	
	//Propositions de reponse
//	public void ajouterReponse() {
	//	return titre;
//	}
	
	//Initialisation questionnaire
	public void reinitialiser() {
		nbRepOK =0;
		while (question.listIterator().hasPrevious()){
			question.listIterator().previous();
		}
		
	}
	
	//Recherche de questions restantes
	public boolean resteDesQuestions(){
		return question.listIterator().hasNext();
	}

	//Affichage question suivante
	public void afficherQuestionSuivante() {
		nouvQuestion = question.listIterator().next();
		nouvQuestion.affichageTitre();
		bonneReponses = nouvQuestion.affichageProposition();		
	}
	
	//Saisie reponse utilisateur
	public void lireReponseQuestion() {
		//Ouverture scanner
		Scanner scan = new Scanner(System.in);

	    int saisie = scan.nextInt();

	    if (bonneReponses.contains(saisie)){
	    	nbRepOK += 1;
	    }
	    
	}
	
	//Calcul et affichage resultat
	public int resultat() {
		return nbRepOK * 20;
	}
	
	
	
}



//Questionnaire questionnaire = new Questionnaire("QCM de geographie");
//questionnaire.ajouterQuestion(question);
//questionnaire.ajouterQuestion(question);
//    // Pour indiquer que la prochaine question a poser sera la 1ere question
//    // et initialiser le total des points obtenus par l'utilisateur.
//questionnaire.reinitialiser();
//System.out.println(questionnaire.titre());
//while (questionnaire.resteDesQuestions()) {
//  // Affiche une question et lit la reponse de l'utilisateur
//  questionnaire.afficherQuestionSuivante();
//  questionnaire.lireReponseQuestion();
//}
//System.out.println("Votre note : " + questionnaire.resultat());
//// Prepare le questionnaire pour la prochaine fois
//questionnaire.reinitialiser();
