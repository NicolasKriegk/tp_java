package fr.imie.jdbc.tp7.Ihm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fr.imie.jdbc.tp7.Personne;
import fr.imie.jdbc.tp7.DAO.Persist;
import fr.imie.jdbc.tp7.Exception.PersistanceException;
import fr.imie.jdbc.tp7.InterfaceDAO.IPersist;
import fr.imie.jdbc.tp7.InterfaceIhm.IIhm;

public class Ihm implements IIhm{

	//
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private IPersist jdbc = new Persist();

	//scanner
	Scanner sc = new Scanner(System.in);
	

	public void menu() throws PersistanceException{

		int choix = -1;
		boolean stopApp = false;
		
		while(!stopApp){
			System.out.println("----------------------------------");
			System.out.println("Menu:");
			System.out.println("1 - Recherche");
			System.out.println("2 - Creation personne");
			System.out.println("3 - Mise a jour personne");
			System.out.println("4 - Suppression personne");
			System.out.println("0 - Quitter");
			choix = sc.nextInt();
			sc.nextLine();
			switch(choix){
			case 0:	//fin application
				stopApp = true;
				break;
			case 1://recherche de personne
				this.searchPerson();
				break;
			case 2://creation de personne
				this.createPerson();
				break;
			case 3://mise a jour de personne
				this.updatePerson();
				break;
			case 4://suppression de personne
				this.deletePerson();
				break;
			default:
//				stopApp = true;
				break;
			}
		}
		sc.close();
	}
	
	
	public void createPerson() throws PersistanceException{

		Personne personne =new Personne();
		//resultats de recherche
		List<Personne> personnes = new ArrayList<Personne>();
		//saisie des infos
		System.out.println("----------------------------------");
		System.out.println("Création personne:");
		personne = setPersonne();
//		System.out.print("Saisir mot de passe: ");
//		String password = sc.nextLine();
//		personne.setPasswd("".equals(password)?null:password);
		
		//passages des valeurs a la persistance
		personnes.add(jdbc.createPerson(personne));
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Voici la (les) personne(s) inserée(s):");
		displayPersonList(personnes);
			
			
	}


	public void searchPerson() throws PersistanceException{

		Personne personne =new Personne();
		//resultats de recherche
		List<Personne> personnes = new ArrayList<Personne>();
		//completion requete
		System.out.println("----------------------------------");
		System.out.println("Recherche personne:");
		personne = setPersonne();
//		personne.setNom("".equals(personne.getNom())?null:personne.getNom());
//		personne.setPrenom("".equals(personne.getPrenom())?null:personne.getPrenom());
		personnes = jdbc.searchPerson(personne);
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Personnes recherchees:");
		displayPersonList(personnes);
			
	}


	/**
	 * @param personnes
	 */
	private void displayPersonList(List<Personne> personnes) {
		Integer compteurLigne = Integer.valueOf(1);
		System.out.println("N° | Nom | Prenom | Date de naissance");
		for (Personne resultPersonne : personnes) {
			//construction chaine à afficher
			System.out.print(compteurLigne++);
			System.out.print(" | ");
			System.out.print(resultPersonne.getNom());
			System.out.print(" | ");
			System.out.print(resultPersonne.getPrenom());
			System.out.print(" | ");
			System.out.print(sdf.format(resultPersonne.getDateNaissance()));
			System.out.println("");
		}
	}
	
	private Personne setPersonne() {
		return setPersonne(null);
	}
		/**
	 * @return
	 */
	private Personne setPersonne(Personne personneOrigin) {
//		Personne personne =new Personne();
//		System.out.print("Saisir nom: ");
//		personne.setNom(sc.nextLine());
//		System.out.print("Saisir prenom: ");
//		personne.setPrenom(sc.nextLine());
////		chaine.setLength(0);
//		System.out.print("Saisir date de naissance: ");
//		boolean saisieDateNaissance = false;
//		while(!saisieDateNaissance){
//			try {
//				String saisie = sc.nextLine();
//				if(!"".equals(saisie)){
//					Date dateNaissance = sdf.parse(saisie);
//					personne.setDateNaissance(dateNaissance);
//				}
//				saisieDateNaissance = true;
//			} catch (ParseException e) {
//				System.out.format("Respecter le format %s%n", sdf.toPattern());
//			}
//		}
		String nomInput;
		String prenomInput;
		String passwInput;
		Date dateNaissInput = null;
		System.out.println("Saisir nom:");
		if (personneOrigin != null && personneOrigin.getNom() != null) {
			System.out.print(personneOrigin.getNom());
		}
		nomInput = sc.nextLine();
		System.out.println("Saisir prenom:");
		if (personneOrigin != null && personneOrigin.getPrenom() != null) {
			System.out.print(personneOrigin.getPrenom());
		}
		prenomInput = sc.nextLine();
		System.out.println("saisir date naissance:");
		if (personneOrigin != null && personneOrigin.getDateNaissance() != null) {
			System.out.print(sdf.format(personneOrigin.getDateNaissance()));
		}
		Boolean saisieDateNaiss = false;
		while (!saisieDateNaiss) {
			try {
				String inputTmp = sc.nextLine();
				if (!"".equals(inputTmp)) {
					dateNaissInput = sdf.parse(inputTmp);
				}
				saisieDateNaiss = true;
			} catch (ParseException e) {
				System.out.format("Respecter le format %s%n", sdf.toPattern());
			}
		}
		System.out.println("Saisir mot de passe:");
		if (personneOrigin != null && personneOrigin.getPasswd() != null) {
			System.out.print(personneOrigin.getPasswd());
		}
		passwInput = sc.nextLine();

		Personne personOutput = null;
		if (personneOrigin != null) {
			personOutput = personneOrigin;
		} else {
			personOutput = new Personne();
		}
		personOutput.setNom("".equals(nomInput) ? personOutput.getNom() : nomInput);
		personOutput.setPrenom("".equals(prenomInput) ? personOutput.getPrenom() : prenomInput);
		personOutput.setDateNaissance(dateNaissInput==null?personOutput.getDateNaissance():dateNaissInput);
		personOutput.setPasswd("".equals(passwInput) ? personOutput.getPasswd() : passwInput);
		return personOutput;
	}


	public void updatePerson() throws PersistanceException {
		Personne personne =new Personne();
		//resultats de recherche
		List<Personne> personnes = new ArrayList<Personne>();
		//saisie des infos de(s) personne(s)
		personnes = jdbc.searchPerson(personne);
		displayPersonList(personnes);

		System.out.println("Saisir le numero de la ligne a modifier:");
		// saisie ligne pers à modifier
		Integer lineToUpdate = Integer.valueOf(sc.nextInt())-1; //numero ligne commence a "1" et index list a "0"
		sc.nextLine();
		// récupération de la personne dans la liste qui a servi à
		// l'affichage
		Personne personneToUpdate = personnes.get(lineToUpdate);
		personneToUpdate = setPersonne(personneToUpdate);
		personnes.clear();
		personnes.add(jdbc.updatePerson(personneToUpdate));
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Personne mise a jour:");
		displayPersonList(personnes);
	}


	@Override
	public void deletePerson() throws PersistanceException {
		Personne personne =new Personne();
		//resultats de recherche
		List<Personne> personnes = new ArrayList<Personne>();
		//saisie des infos de(s) personne(s)
		personnes = jdbc.searchPerson(personne);
		displayPersonList(personnes);

		System.out.println("Saisir le numero de la ligne a supprimer:");
		// saisie ligne pers à modifier
		Integer lineIndex = Integer.valueOf(sc.nextInt())-1; //numero ligne commence a "1" et index list a "0"
		sc.nextLine();
		// récupération de la personne dans la liste qui a servi à
		// l'affichage
		Personne personneToDelete = personnes.get(lineIndex);
		jdbc.deletePerson(personneToDelete);
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Personne mise a jour:");
		personnes.clear();
		personnes = jdbc.searchPerson(new Personne());
		displayPersonList(personnes);
	}
	
}
