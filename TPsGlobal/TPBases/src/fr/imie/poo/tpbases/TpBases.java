package fr.imie.poo.tpbases;

import java.util.Scanner;

public class TpBases {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//Lecture saisie clavier
		Scanner scan = new Scanner(System.in);

//		// 1er exercice
//		int age;
//		System.out.format("Saisir l'age");
//	    age = scan.nextInt();
//		
//		if (age >= 6 & age <= 7) {
//			System.out.format("Categorie: Poussin");
//		} else if (age >= 8 & age <= 9) {
//			System.out.format("Categorie: Pupille");
//		} else if (age >= 10 & age <= 11) {
//			System.out.format("Categorie: Minime");
//		} else if (age >= 12) {
//			System.out.format("Categorie: Cadet");
//		}
		
		
		
//		// 2eme exercice
//		int nombre;
//		System.out.format("Saisir une valeur%n");
//		nombre = scan.nextInt();
//		
//		//Detection parite nombre
//		System.out.format("Le nombre est %s%n",(nombre % 2 == 0 ? "pair" : "impair"));
		
		
		
//		//3eme exercice
//		System.out.format("Saisir une cha�ne de 3 � 10 caract�res%n");
//		String chaine = scan.nextLine();
//		
//		//D�tection longueur chaine
//		boolean ok = chaine.length() > 3 & chaine.length() < 10 ;
//		System.out.format("%s%n",(ok ? "OK" : "Boulet"));
		
		
		
//		//4eme exercice
//		System.out.format("Saisir un nombre de photocopies%n");
//		int nbPhotocop = scan.nextInt();
//		
//		//Calcul montant facture
//		int i = 1;
//		double montant = 0;
//		while (i <= nbPhotocop) {
//			if (i <= 10) {
//				montant += 0.1;
//			} else if (i <= 30) {
//				montant += 0.08;
//			} else {
//				montant += 0.05;
//			}
//			i++;
//		}
//		System.out.format("Le prix total est de : %f%n",montant);
		

		
//		//5eme exercice
//		System.out.format("Saisir un nombre:%n");
//		int nombre = scan.nextInt();
//		//Calcul de la somme des nombres jusqu'a "nombre"
//		int calcul = 0;
//		for (int j = 0; j <= nombre; j++) {
//			calcul += j;
//		}
//		System.out.format("Somme des entiers jusqu'a %d => %d%n", nombre, calcul);

		
		
//		//6eme exercice
//		System.out.format("Saisir un nombre:%n");
//		int nombre = scan.nextInt();
//		//Calcul de la factorielle des nombres jusqu'a "nombre"
//		int calcul = 1;
//		for (int j = 1; j <= nombre; j++) {
//			calcul *= j;
//		}
//		System.out.format("Factorielle %d! => %d%n", nombre, calcul);
//		
//		avec methode recursive factorielle
//		System.out.format("Factorielle %d! => %d%n", nombre, Fact.fact(nombre));
		

//		//7eme exercice
/*
*  calcule le carre de chaque nombre de 0 a 5
	et le range dans un tableau a l'indice correspondant
	puis affiche toutes les valeurs du tableau
on peut simplifier en integrant l'affichage dans la boucle de calcul (voire en se passant de tableau)
*/		

		

//		//8eme exercice
//		//affichage de 0 a 100
//		//Boucle for
//		for (int j = 0; j <= 100; j++) {
//			System.out.format("For: %d%n", j);
//		}
//		//Boucle while
//		int j = 0;
//		while (j <= 100) {
//			System.out.format("While: %d%n", j);
//			j++;
//		}
//		//Boucle do while
//		j = 0;
//		do {
//			System.out.format("Do While: %d%n", j);
//			j++;
//		}while (j <= 100);


		
//		//9eme exercice
//		System.out.format("Saisir une chaine:%n");
//		String chaine = scan.nextLine();
//		int longChaine = chaine.length();
//		
//		//Stockage lettres de la chaine dans un tableau
//		char tabChar[] = chaine.toCharArray(); 
//		//Affichage a l'envers de la chaine saisie
//		for (int j = tabChar.length-1; j >= 0; j--) {
//			System.out.format("%s", tabChar[j] );
//		}
//		System.out.format("%n");

		
		
//		//10eme exercice
//		System.out.format("Saisir un verbe du 1er groupe:%n");
//		String chaine = scan.nextLine();
//		int longChaine = chaine.length();
//		
//		//Conjugaison imparfait
//		String racine = chaine.substring(0, longChaine-2);
//		System.out.format("Conjugaison a l'imparfait de %s:%n", chaine);
//		System.out.format("Je %seais%n", racine);
//		System.out.format("Tu %seais%n", racine);
//		System.out.format("Il %seait%n", racine);
//		System.out.format("Nous %sions%n", racine);
//		System.out.format("Vous %siez%n", racine);
//		System.out.format("Ils %seaient%n", racine);
		
		
		
//		//11eme exercice
//		int tab[] = new int[3]; 
//		//saisie des valeurs du tableau et preparation de la moyenne
//		double moyenne = 0.0, somme = 0.0; 
//		for (int j = 0; j <= tab.length-1; j++) {
//			System.out.format("Saisir un nombre:%n");
//			tab[j] = scan.nextInt();
//			somme += tab[j];
//		}
//		//affichage de la moyenne des nombres du tableau
//		System.out.format("Moyenne des %d valeurs du tableau %f%n", tab.length, somme / tab.length);


		
//		//12eme exercice
//		int tab[] = {1,2,3,4,5}; 
//		int valTemp = 0;
//		int tabIndMax = tab.length-1;
//		//saisie des valeurs du tableau et preparation de la moyenne
//		for (int j = 0; j <= tabIndMax; j++) {
//			if (j == 0) {
//				//Sauvegarde valeur pour premiere permutation (a inserer a la fin du traitement)
//				valTemp = tab[j];
//				//Permutation
//				tab[j] = tab[j+1];
//			} else if  (j == tabIndMax) {
//				//Derniere ecriture, restitution valeur sauvegardee
//				tab[tabIndMax] = valTemp;
//			} else {
//				//Permutation
//				tab[j] = tab[j+1];
//			}
//		}
//		//affichage du tableau permute
//		for (int j = 0; j <= tabIndMax; j++) {
//			System.out.format("tab[%d] %d%n", j, tab[j]);
//		}

		
		
//		//13eme exercice
//		int nombre;
//		System.out.format("Entrez le nombre voulu:%n");
//		nombre = scan.nextInt();
//		for (int i = 0; i < nombre; i++) {
//			for (int j = 0; j <= i; j++) {
//				System.out.format("*");
//			}
//			System.out.format("%n");
//		}

		
		
//		//14eme exercice
//		int tab[] = {15,15,14,2,9}; 
//		int maxTab = tab[0], minTab = tab[0];
//		int tabIndMax = tab.length-1;
//		//saisie des valeurs du tableau et preparation de la moyenne
//		for (int j = 0; j <= tabIndMax; j++) {
//			if (maxTab <= tab[j]) {
//				//Sauvegarde valeur max
//				maxTab = tab[j];
//			}
//			if (minTab >= tab[j]) {
//				//Sauvegarde valeur min
//				minTab = tab[j];
//			}
//		}
//		//affichage de l'ecart max dans le tableau
//		System.out.format("Ecart max: %d%n", Math.abs(maxTab-minTab));


		
//		//15eme exercice
//		int nombre;
//		System.out.format("Entrez le nombre voulu:%n");
//		nombre = scan.nextInt();
//		for (int i = 0; i <= 10; i++) {
//			System.out.format("%d x %d = %d%n", nombre, i, nombre * i);
//		}

		

//		//16eme exercice
/* affiche 0 pair
 * 3 multiple de 3
 * 9 multiple de 3
 * 15 multiple de 3
 * 20 multiple de 5
 * sors de l'execution		
 */
//		//verif:
//		int n = 0;
//		do {
//		if (n % 2 == 0) {
//		System.out.println(n + " est pair");
//		n += 3;
//		continue;
//		}
//		if (n % 3 == 0) {
//		System.out.println(n + " est multiple de 3");
//		n += 5;
//		}
//		if (n % 5 == 0) {
//		System.out.println(n + " est multiple de 5");
//		break;
//		}
//		n += 1;
//		} while (true);
		
		
//		//17eme exercice
/* affiche A : n = 6
 * affiche B : n = 10
 * affiche C : n = 10
 * affiche D : n = 15
 * affiche E : n = 21
*/
//		//verif:
//		int n, p;
//		n = 0;
//		while (n <= 5) {
//		n++;
//		}
//		System.out.println("A : n = " + n);
//		n = p = 0;
//		while (n <= 8) {
//		n += p++;
//		}
//		System.out.println("B : n = " + n);
//		n = p = 0;
//		while (n <= 8) {
//		n += ++p;
//		}
//		System.out.println("C : n = " + n);
//		n = p = 0;
//		while (p <= 5) {
//		n += p++;
//		}
//		System.out.println("D : n = " + n);
//		n = p = 0;
//		while (p <= 5) {
//		n += ++p;
//		}
//		System.out.println("E : n = " + n);
		
		
		
//		//18eme exercice
		int nombre;
		System.out.format("Entrez le nombre voulu:%n");
		nombre = scan.nextInt();
		System.out.format("Entrez le caractere voulu:%n");
		char car = scan.next().t;
//		nbSpace = nombre
		for (int i = 0; i < nombre; i++) {
			for (int j = 0; j <= i; j++) {
				for (int j2 = 0; j2 < i-j; j2++) {
					System.out.format(" ");
				}
				System.out.format("%s", car);
			}
			System.out.format("%n");
		}

		
		

		
		
		
		
		scan.close();
		
	}

}
