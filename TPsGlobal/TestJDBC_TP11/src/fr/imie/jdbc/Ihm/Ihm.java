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
import fr.imie.jdbc.InterfaceDAO.IPersonDAO;
import fr.imie.jdbc.InterfaceIhm.IIhm;
import fr.imie.jdbc.InterfaceIhm.IIhmCRUD;

public class Ihm implements IIhm{

	public void menu() throws PersistanceException{

		
		// formater pour affichage date
		String dateFormat = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

		int choix = -1;
		boolean stopApp = false;
		boolean retourMenu = false;
		Scanner scanner = new Scanner(System.in);

		IIhmCRUD ihmUser = new IhmUser(scanner,simpleDateFormat);
		IIhmCRUD ihmPromotion = new IhmPromotion(scanner);
		
		while(!stopApp){
			System.out.println("----------------------------------");
			System.out.println("Menu: (0 - Quitter)");
			System.out.println("1 - Personnes");
			System.out.println("2 - Promotions");
			choix = scanner.nextInt();
			scanner.nextLine();
			switch(choix){
			case 0:	//fin application
				stopApp = true;
				break;
			case 1://menu personne
				retourMenu = false;
				while(!retourMenu){
					System.out.println("----------------------------------");
					System.out.println("Menu Personne: (0 - Retour)");
					System.out.println("1 - Recherche");
					System.out.println("2 - Creation");
					System.out.println("3 - Mise a jour");
					System.out.println("4 - Suppression");
					choix = scanner.nextInt();
					scanner.nextLine();
					switch(choix){
					case 0:	//retour menu precedent
						retourMenu = true;
						break;
					case 1://recherche de personne
						ihmUser.search();
						break;
					case 2://creation de personne
						ihmUser.add();
						break;
					case 3://mise a jour de personne
						ihmUser.update();
						break;
					case 4://suppression de personne
						ihmUser.delete();
						break;
					default:
	//					stopApp = true;
						break;
					}
				}
				break;
			case 2://menu promotion
				retourMenu = false;
				while(!retourMenu){
					System.out.println("----------------------------------");
					System.out.println("Menu Promotion: (0 - Retour)");
					System.out.println("1 - Recherche");
					System.out.println("2 - Creation");
					System.out.println("3 - Mise a jour");
					System.out.println("4 - Suppression");
					choix = scanner.nextInt();
					scanner.nextLine();
					switch(choix){
					case 0:	//retour menu precedent
						retourMenu = true;
						break;
					case 1://recherche de personne
						ihmPromotion.search();
						break;
					case 2://creation de personne
						ihmPromotion.add();
						break;
					case 3://mise a jour de personne
						ihmPromotion.update();
						break;
					case 4://suppression de personne
						ihmPromotion.delete();
						break;
					default:
	//					stopApp = true;
						break;
					}
				}
				break;
			}
		}
		scanner.close();
	}
	
	


}
