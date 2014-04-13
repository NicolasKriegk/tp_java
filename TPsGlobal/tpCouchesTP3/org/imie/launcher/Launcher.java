package org.imie.launcher;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.imie.proxy.ProxyService;
import org.imie.transverse.DTO.Personne;
import org.imie.transverse.DTO.Promotion;
import org.imie.transverse.exception.ServiceException;
import org.imie.transverse.factory.Factory;

public class Launcher {
	
	private static Factory factory = new Factory();
	private static ProxyService service = factory.createService();

	/**
	 * @param args
	 * @throws ServiceException 
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ServiceException {
		// formater pour affichage date
		String dateFormat = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		// fonction en cours d'execution
		Integer choixFonction = null;
		// semaphore de fin d'application
		boolean applicationRun = true;
		Scanner scanIn = new Scanner(System.in);

		
		// boucle d'affichage
		while (applicationRun) {
			// notice
			System.out.println("_____________________");
			System.out.println("1 : rechercher personne par nom");
			System.out.println("2 : rechercher personne avancée");
			System.out.println("3 : créer presonne");
			System.out.println("4 : supprimer personne");
			System.out.println("5 : modifier personne");
			System.out.println("6 : supprimer promotion");
			System.out.println("7 : dupliquer une personne");
			System.out.println("8 : quitter");
			System.out.println("saisir choix fonction :");
			// saisie fonction
			choixFonction = Integer.valueOf(scanIn.nextInt());
			scanIn.nextLine();

			// variable de saisie
			String nomInput = null;
			// // variable de saisie
			// String prenomInput = null;
			// // variable de saisie
			// Date dateNaissInput = null;
			// // variable de saisie
			// String passwInput = null;

			Personne currentPersonne = null;

			List<Personne> personnes = new ArrayList<Personne>();

			// implémentation de chaque fonctionnalité
			switch (choixFonction) {
			case 1:

				System.out.println("saisir nom :");
				// saisie du paramètre de recherche
				nomInput = scanIn.nextLine();
				// rechercherPersonne(nomInput);
				// boucle d'affichage des personnes
				personnes.clear();
				personnes = service.rechercherPersonne(nomInput);
				displayPersonnes(simpleDateFormat, personnes);

				break;
			case 2:
				currentPersonne = setPersonne(simpleDateFormat, scanIn);
				personnes.clear();
				personnes = service.rechercherPersonne(currentPersonne);
				displayPersonnes(simpleDateFormat, personnes);
				break;
			case 3:
				currentPersonne = setPersonne(simpleDateFormat, scanIn);

				Personne insertedPersonne = service
						.insertPersonne(currentPersonne);
				System.out
						.println("personne inséré avec l'ID :"
								.concat(String.valueOf(insertedPersonne.getId()))
								.concat(" et le passw ")
								.concat(insertedPersonne.getPassw() != null ? insertedPersonne
										.getPassw() : ""));

				break;
			case 4:
				personnes.clear();
				personnes = service.rechercherPersonne(new Personne());
				displayPersonnes(simpleDateFormat, personnes);
				System.out.println("saisir la ligne à supprimer :");
				// saisie ligne pers à supprimer
				Integer lineToDelete = Integer.valueOf(scanIn.nextInt());
				// récupération de la personne dans la liste qui a servi à
				// l'affichage
				Personne personneToDelete = personnes.get(lineToDelete);
				service.deletePersonne(personneToDelete);
				scanIn.nextLine();
				break;
			case 5:
				personnes.clear();
				personnes = service.rechercherPersonne(new Personne());
				displayPersonnes(simpleDateFormat, personnes);
				System.out.println("saisir la ligne à modifier :");
				// saisie ligne pers à modifier
				Integer lineToUpdate = Integer.valueOf(scanIn.nextInt());
				scanIn.nextLine();
				// récupération de la personne dans la liste qui a servi à
				// l'affichage
				Personne personneToUpdate = personnes.get(lineToUpdate);
				personneToUpdate = setPersonne(simpleDateFormat, scanIn,
						personneToUpdate);
				service.updatePersonne(personneToUpdate);
				break;
			case 6:
//				IPromotionDAO promotionDAO = new PromotionDAO();
				List<Promotion> promotions = service.rechercherPromotion(new Promotion());
				displayPromotion(promotions);
				System.out.println("saisir la ligne à supprimer :");
				// saisie ligne promotion à supprimer
				Integer linePromotionToDelete = Integer.valueOf(scanIn.nextInt());
				scanIn.nextLine();
				// récupération de la promotion dans la liste qui a servi à
				// l'affichage
				Promotion promotionToDelete= promotions.get(linePromotionToDelete);
				service.deletePromotion(promotionToDelete);
				break;
			case 7:
				// duplication personne
				personnes.clear();
				personnes = service.rechercherPersonne(new Personne());
				displayPersonnes(simpleDateFormat, personnes);
				System.out.println("saisir la ligne à dupliquer :");
				// saisie ligne pers à modifier
				Integer lineToCopy = Integer.valueOf(scanIn.nextInt());
				scanIn.nextLine();
				// récupération de la personne dans la liste qui a servi à
				// l'affichage
				Personne personneToCopy = personnes.get(lineToCopy);
				personneToCopy = setPersonne(simpleDateFormat, scanIn,
						personneToCopy);
				System.out.println("Saisir le nombre de copies:");
				// saisie du nombre de duplications voulu
				Integer nb = Integer.valueOf(scanIn.nextInt());
				scanIn.nextLine();
				service.dupliquerPersonne(nb, personneToCopy);
				break;
			case 8:
				// fin d'application
				applicationRun = false;
				break;
			default:
				break;
			}

		}
		// fermeture du scan
		scanIn.close();

	}

	/**
	 * @param simpleDateFormat
	 * @param personnes
	 */
	private static void displayPersonnes(SimpleDateFormat simpleDateFormat,
			List<Personne> personnes) {
		Integer compteurLigne = Integer.valueOf(0);
		for (Personne personne : personnes) {
			System.out.print(compteurLigne);
			System.out.print(":");
			System.out.print(personne.getNom());
			System.out.print("|");
			System.out.print(personne.getPrenom());
			System.out.print("|");
			System.out
					.print(personne.getDateNaiss() != null ? simpleDateFormat
							.format(personne.getDateNaiss()) : "");
			System.out.print("|");
			System.out.print(personne.getPromotion()!=null?personne.getPromotion().getLibelle():"");
			System.out.println("");
			
			compteurLigne++;
		}
	}

	/**
	 * @param simpleDateFormat
	 * @param personnes
	 */
	private static void displayPromotion(List<Promotion> promotions) {
		Integer compteurLigne = Integer.valueOf(0);
		for (Promotion promotion : promotions) {
			System.out.print(compteurLigne);
			System.out.print(":");
			System.out.println(promotion.getLibelle());
			compteurLigne++;
		}
	}

	private static Personne setPersonne(SimpleDateFormat simpleDateFormat,
			Scanner scanIn) throws ServiceException {
		return setPersonne(simpleDateFormat, scanIn, null);
	}

	/**
	 * @param simpleDateFormat
	 * @param scanIn
	 * @param dateNaissInput
	 * @param personne
	 * @return
	 * @throws ServiceException 
	 */
	private static Personne setPersonne(SimpleDateFormat simpleDateFormat,
			Scanner scanIn, Personne personneOrigin) throws ServiceException {
		String nomInput;
		String prenomInput;
		String passwInput;
		Date dateNaissInput = null;
		System.out.println("saisir nom :");
		if (personneOrigin != null && personneOrigin.getNom() != null) {
			System.out.print(personneOrigin.getNom());
		}
		nomInput = scanIn.nextLine();
		System.out.println("saisir prenom :");
		if (personneOrigin != null && personneOrigin.getPrenom() != null) {
			System.out.print(personneOrigin.getPrenom());
		}
		prenomInput = scanIn.nextLine();
		System.out.println("saisir date naissance :");
		if (personneOrigin != null && personneOrigin.getDateNaiss() != null) {
			System.out.print(simpleDateFormat.format(personneOrigin
					.getDateNaiss()));
		}
		Boolean saisieDateNaiss = false;
		while (!saisieDateNaiss) {
			try {
				String inputTmp = scanIn.nextLine();
				if (!"".equals(inputTmp)) {
					dateNaissInput = simpleDateFormat.parse(inputTmp);
				}
				saisieDateNaiss = true;
			} catch (ParseException e) {
				System.out.println("format à respecter :"
						.concat(simpleDateFormat.toPattern()));
			}
		}
		System.out.println("saisir password :");
		if (personneOrigin != null && personneOrigin.getPassw() != null) {
			System.out.print(personneOrigin.getPassw());
		}
		passwInput = scanIn.nextLine();



		String promotionInput = null;
		List<Promotion> promotions = service.rechercherPromotion(new Promotion());
		
		displayPromotion(promotions);
		Promotion promotionInputObject = null;
		Boolean saisiePromotion=false;
		while(!saisiePromotion){
			System.out.println("saisir promotion :");
			if (personneOrigin != null && personneOrigin.getPromotion() != null) {
				for (Promotion promotion : promotions) {
					if(promotion.getId()==personneOrigin.getPromotion().getId()){
						promotionInput=String.valueOf(promotions.indexOf(promotion));
					}
				}
				System.out.print(promotionInput);
			}
			promotionInput = scanIn.nextLine();
			if("N".equals(promotionInput)){
				saisiePromotion=true;
			}else{
				try{
					Integer promotionLigne = Integer.parseInt(promotionInput);
					promotionInputObject  = promotions.get(promotionLigne);	
					saisiePromotion=true;
				} catch (NumberFormatException e){
					System.out.println("format à respecter : N ou numérique");
				}
				
			}
		}
		


		Personne personneOuput = null;
		if (personneOrigin != null) {
			personneOuput = personneOrigin;
		} else {
			personneOuput = new Personne();
		}
		personneOuput.setNom("".equals(nomInput) ? personneOuput.getNom()
				: nomInput);
		personneOuput.setPrenom("".equals(prenomInput) ? personneOuput
				.getPrenom() : prenomInput);
		personneOuput.setDateNaiss(dateNaissInput == null ? personneOuput
				.getDateNaiss() : dateNaissInput);
		personneOuput.setPassw("".equals(passwInput) ? personneOuput.getPassw()
				: passwInput);
		personneOuput.setPromotion(promotionInputObject);
		return personneOuput;
	}
}
