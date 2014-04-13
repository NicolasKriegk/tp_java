package fr.imie.poo.cours.regex;

import java.util.regex.*;

public class RegexTest {

	public static void main(String[] args) {
		Pattern pattern;
		Matcher matcher;
//		Pattern pattern = Pattern.compile("gabriel block");
//		Matcher matcher = pattern.matcher("Gabriel Block");

		//extension libreoffice
//		Pattern pattern = Pattern.compile("\\.od[pst]");
//		Matcher matcher = pattern.matcher(".odt");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher(".odp");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher(".ods");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher(".odf");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher(".edt");
//		System.out.println(matcher.matches());

		//chaine de - de 10 caracteres non vides
//		Pattern pattern = Pattern.compile(".{1,9}");
//		Matcher matcher = pattern.matcher("");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher("1234567890");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher("789tr");
//		System.out.println(matcher.matches());

		//code PIN entre 4 et 8 chiffres
//		Pattern pattern = Pattern.compile("[0-9]{4,8}");
//		Matcher matcher = pattern.matcher("aaaa");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher("0000");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher("aaaaaaaaa");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher("999999999");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher("99999999");
//		System.out.println(matcher.matches());
//		matcher = pattern.matcher("000");
//		System.out.println(matcher.matches());
		
		//Jim ou Joe
//		pattern = Pattern.compile("J((im)|(oe))");
//		matcher = pattern.matcher("oe");
//		System.out.println(matcher.matches());
		
		//Prenom
//		pattern = Pattern.compile("[A-Z][a-zï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½]+(-[A-Z][a-zï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½]+)*");
//		String essai[] = {"Nicolas","nicolas","Franï¿½ois","franï¿½ois","Jean-Marc","Jean-marc","jean-Marc","JM"
//				,"J-M","Nicolas07","1Nicolas"
//		};
//		for (int i = 0; i <= (essai.length - 1); i++ ){
//			matcher = pattern.matcher(essai[i]);
//			System.out.println(essai[i]+" --> "+matcher.matches());
//		}
		
		//Mot qui commence et finit par une voyelle
//		pattern = Pattern.compile("[aeiouyAEIOUY]([a-zA-Z]*[aeiouyAEIOUY])?");
//		String essaiVrai[] = {"AeioU","A","Y","Abi","Au","abcdE"};
//		String essaiFaux[] = {"Abcd","B","C","Bei"};
//		System.out.println("Normalement vrai:");
//		for (int i = 0; i <= (essaiVrai.length - 1); i++ ){
//			matcher = pattern.matcher(essaiVrai[i]);
//			System.out.println(essaiVrai[i]+" --> "+matcher.matches());
//		}
//		System.out.println("Normalement faux:");
//		for (int i = 0; i <= (essaiFaux.length - 1); i++ ){
//			matcher = pattern.matcher(essaiFaux[i]);
//			System.out.println(essaiFaux[i]+" --> "+matcher.matches());
//		}
		
		//Une heure au format 24h (ex:19h30)
//		pattern = Pattern.compile("(([0-1]?[0-9])|(2[0-3]))[hH][0-5][0-9]");
//		String essaiVrai[] = {"19h30","6h20","23h59","06h20"};
//		String essaiFaux[] = {"25h01","6h60","9h42am"};
//		Fonctions.TestPatternRegex(essaiVrai, true, pattern);
//		Fonctions.TestPatternRegex(essaiFaux, false, pattern);

		
		//TODO:
//		//Une heure au format anglais (ex:7h30am)
//		pattern = Pattern.compile("(([0-1]?[0-9])|(2[0-3]))[hH][0-5][0-9]");
//		String essaiVrai[] = {"19h30","6h20","23h59"};
//		String essaiFaux[] = {"25h01","6h60","9h42am"};
//		System.out.println("Normalement vrai:");
//		for (int i = 0; i <= (essaiVrai.length - 1); i++ ){
//			matcher = pattern.matcher(essaiVrai[i]);
//			System.out.println(essaiVrai[i]+" --> "+(matcher.matches() ? "OK" : "Ratï¿½!"));
//		}
//		System.out.println("Normalement faux:");
//		for (int i = 0; i <= (essaiFaux.length - 1); i++ ){
//			matcher = pattern.matcher(essaiFaux[i]);
//			System.out.println(essaiFaux[i]+" --> "+(!matcher.matches() ? "OK" : "Ratï¿½!"));
//		}
//		
		

		
		
//		//Numero secu
//		pattern = Pattern.compile(""
//				+ "([12]"
//				+ "[0-9]{2}"	// \d\d
//				+ "((0[1-9])|(1[0-2]))"
//				+ "(([1-9][0-9])|(0[1-9]))"	//( ([1-9]\d) | (0[1-9]) )
//				+ "[0-9]{6}"	// \d{6}
//				+ "(([1-8][0-9])|(0[1-9])|(9[0-7]))"
//				+ ")");
//		String essaiVrai[] = {"179044932803181"};
//		String essaiFaux[] = {"079000032803198"};
//		Fonctions.TestPatternRegex(essaiVrai, true, pattern);
//		Fonctions.TestPatternRegex(essaiFaux, false, pattern);


		//TODO
//		//plaques immatriculation
//		pattern = Pattern.compile("("
//				+ "[12]"
//				+ "[0-9]{2}"	// \d\d
//				+ "( (0[1-9]) | (1[0-2]) )"
//				+ "( ([1-9][0-9]) | (0[1-9]) )"	//( ([1-9]\d) | (0[1-9]) )
//				+ "[0-9]{6}"	// \d{6}
//				+ "( ([1-8][0-9]) | (0[1-9]) | (9[0-7]) )"
//				+ ")");
//		String essaiVrai[] = {"179044932803181"};
//		String essaiFaux[] = {"079000032803198"};
//		Fonctions.TestPatternRegex(essaiVrai, true, pattern);
//		Fonctions.TestPatternRegex(essaiFaux, false, pattern);
		
		
		//TODO
		//entier naturel positif
//		pattern = Pattern.compile("("
//				+ "([1-9]+[0-9]) | [\\d&&[^0]]"
//				+ ")");
//		String essaiVrai[] = {"1","11","111","9","9999"};
//		String essaiFaux[] = {"0","-1","00"};
//		Fonctions.TestPatternRegex(essaiVrai, true, pattern);
//		Fonctions.TestPatternRegex(essaiFaux, false, pattern);
		
		
		//TODO
		//entier naturel >=0
//		pattern = Pattern.compile("("
//				+ "[\\d&&[^0]] | ([1-9]+[0-9])"
//				+ ")");
//		String essaiVrai[] = {"1","11","111","9","9999"};
//		String essaiFaux[] = {"0","-1","00"};
//		Fonctions.TestPatternRegex(essaiVrai, true, pattern);
//		Fonctions.TestPatternRegex(essaiFaux, false, pattern);
		
		//TODO
		//nombre potentiellement signï¿½ ayant 1 ï¿½ 3 chiffres apres virgule ï¿½ condition que
		//dernier chiffre ne soit pas 0
//		pattern = Pattern.compile("("
////				+ "[+-]\\d+,(\\d{1,2})?(\\d&&[^0])"
///*correction*/	+ "[+-]?([1-9][0-9]*|0)([.,][0-9]{0,2}[1-9])?"
//				+ ")");
//		String essaiVrai[] = {"+1","+1,1","+0,001","-0,01","+9999999999999,2","+0"};
//		String essaiFaux[] = {"1,10","+0,010","+1,0","#1,01","+1,"};
//		Fonctions.TestPatternRegex(essaiVrai, true, pattern);
//		Fonctions.TestPatternRegex(essaiFaux, false, pattern);
		
		

		//TODO: à finir pour annees bissextiles
		//date au format francais
//		|([-/][-/]
		String mois28= "((1\\d)|(0[1-9])|(2[0-8]))";	//mois de 28 jours (01 a 28)
		String mois30= "(([12]\\d)|(0[1-9])|(30))";	//mois de 30 jours (01 a 30)
		String mois31= "(([12]\\d)|(0[1-9])|(3[01]))";	//mois de 31 jours (01 a 31)
		String plageMois28= "((0[1-9])|(1[0-2]))";	//tous les mois ont au moins 28 jours (01 a 12)
		String plageMois30= "((0([13-9]))|(1[0-2]))";	//tous les mois sauf fevrier ont 30 jours (01 a 12 -02)
		String plageMois31= "((0([13578]))|(1[02]))";	//mois de 31 jours (01, 03, 05, 07, 08, 10, 12)
		String plageAnnees= "(((19)|(20))\\d{2})";	//1900 a 2099
		pattern = Pattern.compile(""
				+ "((" 
					+mois28 + "[-/]" + plageMois28 
					+ ")|(" 
					+ mois30 + "[-/]" + plageMois30 
					+ ")|(" 
					+ mois31 + "[-/]" + plageMois31 
					+ "))"  
				+ "[-/]" 
				+ plageAnnees //mois de 28 jours
				+ "");
		String essaiVrai[] = {	"31/01/1900", "28/02/1900", "27/04/2000", "30/04/2000", "31/03/1900", 
								"30/05/1900", "31/05/1900", "30/06/2000", "31/07/1900", "31/08/1900", 
								"30/09/2099", "31/10/2099", "30/11/2099", "31/12/2099"};
		String essaiFaux[] = {	"29/02/2001", "31/02/2099", "31/04/2099", "31/06/2099", 
								"31/09/2099", "31/11/2099"};
		Fonctions.TestPatternRegex(essaiVrai, true, pattern);
		Fonctions.TestPatternRegex(essaiFaux, false, pattern);
//		String essaiVrai[] = new String[28];
//		String essaiFaux[] = {"00","29","30"};
//		for (int i=0;i<=27;i++){
//			essaiVrai[i] = Fonctions.NumToNbCharString(i + 1, 2);
//		}
		
		
		
		
	}

}
