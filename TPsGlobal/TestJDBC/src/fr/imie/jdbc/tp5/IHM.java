package fr.imie.jdbc.tp5;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IHM {

	//
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private StringBuilder chaine = new StringBuilder();
	private String continuer = null;
	private Persist jdbc = new Persist();
	//resultats de recherche
	private List<Personne> personnes = new ArrayList<Personne>();

	//scanner
	Scanner sc = new Scanner(System.in);
	

	public void personSearch() throws PersistanceException{
		do{
			//completion requete
			System.out.println("Saisir nom ou partie du nom");
			personnes = jdbc.personSearch(sc.nextLine());
			
			//Affichage des resultats
			for (Personne personne : personnes) {
				//construction chaine à afficher
				chaine.setLength(0);
				chaine.append(personne.getNom());
				chaine.append(", ");
				chaine.append(personne.getPrenom());
				chaine.append(", ");
				chaine.append(sdf.format(personne.getDateNaissance()));
				//affichage de la chaine
				System.out.println(chaine);
				
			}
			
			System.out.println("Continuer? (N ou n: quitter)");
			continuer = sc.nextLine();
		}while((!continuer.equals("N"))&&(!continuer.equals("n")));

		sc.close();
	}
	
	
}
