package fr.imie.poo.tp4;

import java.util.Scanner;

/**
 * Cree et pose un QCM.
 */

/*
		Les classes de ce TP modelisent les QCM (questionnaires a choix multiples). Un
		QCM contient plusieurs questions. Une question est representee par un texte qui pose
		la question et par plusieurs reponses possibles (les choix multiples). Ces reponses
		possibles peuvent etre des bonnes reponses ou des mauvaises reponses.
		Le programme principal affiche les questions en numerotant les reponses possibles.
		L'utilisateur indique quelle reponse il choisit. Lorsque toutes les questions ont ete
		posees, le programme donne une note a l'utilisateur. Pour commencer, choisissez la
		notation suivante : chaque bonne reponse compte 1 point ; la note est la moyenne des
		reponses multipliee par 20, et arrondie a l'entier le plus proche.
*/

public class Main {
  public static void main(String[] args) {

	Questionnaire questionnaire = new Questionnaire("QCM de geographie");
	Question question = new Question("Qu'est-ce que Java ?");
	question.ajouterReponse("Une ile de l'ocean Atlantique", false);
	question.ajouterReponse("Une ile de l'ocean Indien", true);
	questionnaire.ajouterQuestion(question);
	question = new Question("Combien d'habitants en France ?");
	question.ajouterReponse("Moins de 20 millions", false);
	question.ajouterReponse("Entre 20 et 50 millions", false);
	question.ajouterReponse("Plus de 50 millions", true);
	questionnaire.ajouterQuestion(question);
        // Pour indiquer que la prochaine question a poser sera la 1ere question
        // et initialiser le total des points obtenus par l'utilisateur.
	questionnaire.reinitialiser();
	System.out.println(questionnaire.titre());
	System.out.println();
	while (questionnaire.resteDesQuestions()) {
      // Affiche une question et lit la reponse de l'utilisateur
		questionnaire.afficherQuestionSuivante();
		questionnaire.lireReponseQuestion();
	}
	System.out.println();
	System.out.println("Votre note : " + questionnaire.resultat());
    // Prepare le questionnaire pour la prochaine fois
	questionnaire.reinitialiser();

  }
}