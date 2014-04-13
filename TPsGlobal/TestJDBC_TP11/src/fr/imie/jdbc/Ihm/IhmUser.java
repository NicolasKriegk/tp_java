package fr.imie.jdbc.Ihm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fr.imie.jdbc.DAO.PersonDAO;
import fr.imie.jdbc.DTO.PersonDTO;
import fr.imie.jdbc.Exception.PersistanceException;
import fr.imie.jdbc.InterfaceDAO.ICrudDAO;
import fr.imie.jdbc.InterfaceDAO.IPersonDAO;
import fr.imie.jdbc.InterfaceIhm.IIhmCRUD;

public class IhmUser implements IIhmCRUD {

	private Scanner scanner = null;
	private SimpleDateFormat simpleDateFormat = null;
	
	public IhmUser(Scanner scanner, SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat; 
		this.scanner = scanner; 
	}

	public void search() throws PersistanceException {

		ICrudDAO<PersonDTO> personneDAO = new PersonDAO();
		PersonDTO personne =new PersonDTO();
		//resultats de recherche
		List<PersonDTO> personnes = new ArrayList<PersonDTO>();
		//completion requete
		System.out.println("----------------------------------");
		System.out.println("Recherche personne:");
		personne = setPersonne(scanner, simpleDateFormat);
//		personne.setNom("".equals(personne.getNom())?null:personne.getNom());
//		personne.setPrenom("".equals(personne.getPrenom())?null:personne.getPrenom());
		personnes = personneDAO.search(personne);
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Personnes recherchees:");
		displayPersonList(personnes);
			
	}



	public void add() throws PersistanceException {

		ICrudDAO<PersonDTO> personneDAO = new PersonDAO();
		PersonDTO personne =new PersonDTO();
		//resultats de recherche
		List<PersonDTO> personnes = new ArrayList<PersonDTO>();
		//saisie des infos
		System.out.println("----------------------------------");
		System.out.println("Création personne:");
		personne = setPersonne(scanner, simpleDateFormat);
		
		//passages des valeurs a la persistance
		personnes.add(personneDAO.create(personne));
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Voici la (les) personne(s) inserée(s):");
		displayPersonList(personnes);
	}

	public void update() throws PersistanceException {

		ICrudDAO<PersonDTO> personneDAO = new PersonDAO();
		PersonDTO personne =new PersonDTO();
		//resultats de recherche
		List<PersonDTO> personnes = new ArrayList<PersonDTO>();
		//saisie des infos de(s) personne(s)
		personnes = personneDAO.search(personne);
		displayPersonList(personnes);

		System.out.println("Saisir le numero de la ligne a modifier:");
		// saisie ligne pers à modifier
		Integer lineToUpdate = Integer.valueOf(scanner.nextInt())-1; //numero ligne commence a "1" et index list a "0"
		scanner.nextLine();
		// récupération de la personne dans la liste qui a servi à
		// l'affichage
		PersonDTO personneToUpdate = personnes.get(lineToUpdate);
		personneToUpdate = setPersonne(personneToUpdate, scanner, simpleDateFormat);
		personnes.clear();
		personnes.add(personneDAO.update(personneToUpdate));
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Personne mise a jour:");
		displayPersonList(personnes);
	}



	public void delete() throws PersistanceException {

		ICrudDAO<PersonDTO> personneDAO = new PersonDAO();
		PersonDTO personne =new PersonDTO();
		//resultats de recherche
		List<PersonDTO> personnes = new ArrayList<PersonDTO>();
		//saisie des infos de(s) personne(s)
		personnes = personneDAO.search(personne);
		displayPersonList(personnes);

		System.out.println("Saisir le numero de la ligne a supprimer:");
		// saisie ligne pers à modifier
		Integer lineIndex = Integer.valueOf(scanner.nextInt())-1; //numero ligne commence a "1" et index list a "0"
		scanner.nextLine();
		// récupération de la personne dans la liste qui a servi à
		// l'affichage
		PersonDTO personneToDelete = personnes.get(lineIndex);
		personneDAO.delete(personneToDelete);
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Personne mise a jour:");
		personnes.clear();
		personnes = personneDAO.search(new PersonDTO());
		displayPersonList(personnes);
	}


	private PersonDTO setPersonne(Scanner scanner,SimpleDateFormat simpleDateFormat) {
		return setPersonne(null,scanner,simpleDateFormat);
	}

	/**
	 * @return
	 */
	private PersonDTO setPersonne(PersonDTO personneOrigin,Scanner scanner,SimpleDateFormat simpleDateFormat) {

		String nomInput;
		String prenomInput;
		String passwInput;
		Date dateNaissInput = null;
		System.out.println("Saisir nom:");
		if (personneOrigin != null && personneOrigin.getNom() != null) {
			System.out.print(personneOrigin.getNom());
		}
		nomInput = scanner.nextLine();
		System.out.println("Saisir prenom:");
		if (personneOrigin != null && personneOrigin.getPrenom() != null) {
			System.out.print(personneOrigin.getPrenom());
		}
		prenomInput = scanner.nextLine();
		System.out.println("saisir date naissance:");
		if (personneOrigin != null && personneOrigin.getDateNaissance() != null) {
			System.out.print(simpleDateFormat.format(personneOrigin.getDateNaissance()));
		}
		Boolean saisieDateNaiss = false;
		while (!saisieDateNaiss) {
			try {
				String inputTmp = scanner.nextLine();
				if (!"".equals(inputTmp)) {
					dateNaissInput = simpleDateFormat.parse(inputTmp);
				}
				saisieDateNaiss = true;
			} catch (ParseException e) {
				System.out.format("Respecter le format %s%n", simpleDateFormat.toPattern());
			}
		}
		System.out.println("Saisir mot de passe:");
		if (personneOrigin != null && personneOrigin.getPasswd() != null) {
			System.out.print(personneOrigin.getPasswd());
		}
		passwInput = scanner.nextLine();

		PersonDTO personOutput = null;
		if (personneOrigin != null) {
			personOutput = personneOrigin;
		} else {
			personOutput = new PersonDTO();
		}
		personOutput.setNom("".equals(nomInput) ? personOutput.getNom() : nomInput);
		personOutput.setPrenom("".equals(prenomInput) ? personOutput.getPrenom() : prenomInput);
		personOutput.setDateNaissance(dateNaissInput==null?personOutput.getDateNaissance():dateNaissInput);
		personOutput.setPasswd("".equals(passwInput) ? personOutput.getPasswd() : passwInput);
		return personOutput;
	}


	/**
	 * @param personnes
	 */
	private void displayPersonList(List<PersonDTO> personnes) {
		Integer compteurLigne = Integer.valueOf(1);
		System.out.println("N° | Nom | Prenom | Date de naissance");
		for (PersonDTO resultPersonne : personnes) {
			//construction chaine à afficher
			System.out.print(compteurLigne++);
			System.out.print(" | ");
			System.out.print(resultPersonne.getNom());
			System.out.print(" | ");
			System.out.print(resultPersonne.getPrenom());
			System.out.print(" | ");
			System.out.print(simpleDateFormat.format(resultPersonne.getDateNaissance()));
			System.out.println("");
		}
	}
	
}
