package fr.imie.jdbc.Ihm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fr.imie.jdbc.DAO.PromotionDAO;
import fr.imie.jdbc.DTO.PersonDTO;
import fr.imie.jdbc.DTO.PromotionDTO;
import fr.imie.jdbc.Exception.PersistanceException;
import fr.imie.jdbc.InterfaceDAO.ICrudDAO;
import fr.imie.jdbc.InterfaceIhm.IIhmCRUD;

public class IhmPromotion implements IIhmCRUD {

	private Scanner scanner = null;
	
	public IhmPromotion(Scanner scanner) {
		this.scanner = scanner; 
	}

	@Override
	public void search() throws PersistanceException {
		
		ICrudDAO<PromotionDTO> promotionDAO = new PromotionDAO();
		PromotionDTO promotion =new PromotionDTO();
		//resultats de recherche
		List<PromotionDTO> promotions = new ArrayList<PromotionDTO>();
		//completion requete
		System.out.println("----------------------------------");
		System.out.println("Recherche promotion:");
		promotion = setPromotion(scanner);
//		personne.setNom("".equals(personne.getNom())?null:personne.getNom());
//		personne.setPrenom("".equals(personne.getPrenom())?null:personne.getPrenom());
		promotions = promotionDAO.search(promotion);
		
		//Affichage des resultats
		System.out.println("----------------------------------");
		System.out.println("Promotions recherchees:");
		displayPromotionList(promotions);
			
	}

	@Override
	public void add() throws PersistanceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() throws PersistanceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete() throws PersistanceException {
		// TODO Auto-generated method stub

	}

	private PromotionDTO setPromotion(Scanner scanner) {
		return setPromotion(null,scanner);
	}

	/**
	 * @return
	 */
	private PromotionDTO setPromotion(PromotionDTO promotionOrigin,Scanner scanner) {

		String nomInput;
		String prenomInput;
		String passwInput;
		Date dateNaissInput = null;
		System.out.println("Saisir nom:");
		if (promotionOrigin != null && promotionOrigin.getNom() != null) {
			System.out.print(promotionOrigin.getNom());
		}
		nomInput = scanner.nextLine();

		PromotionDTO promotionOutput = null;
		if (promotionOrigin != null) {
			promotionOutput = promotionOrigin;
		} else {
			promotionOutput = new PromotionDTO();
		}
		promotionOutput.setNom("".equals(nomInput) ? promotionOutput.getNom() : nomInput);
		return promotionOutput;
	}


	/**
	 * @param personnes
	 */
	private void displayPromotionList(List<PromotionDTO> promotions) {
		Integer compteurLigne = Integer.valueOf(1);
		System.out.println("N° | Nom");
		for (PromotionDTO resultPromotion : promotions) {
			//construction chaine à afficher
			System.out.print(compteurLigne++);
			System.out.print(" | ");
			System.out.print(resultPromotion.getNom());
			System.out.println("");
		}
	}
	
}
