package fr.imie.poo.tp2;
import java.util.Scanner;


public class ShapeTest {

	//Methodes de classe
	private static void printAreaOf(Shape s) {

		System.out.format("L'aire de la forme \"%s\" est: %f.%n", s.getTitle(), s.area());
		
	}
	
	private static void printPerimeterOf(Shape s) {

		System.out.format("Le p�rim�tre de la forme \"%s\" est: %f.%n", s.getTitle(), s.perimeter());
		
	}
	
	
	//Point d'entr�e
	public static void main(String[] args) {
		
		//Attributs
		//Ouverture scanner
		Scanner scan = new Scanner(System.in);
		
		//Instanciation des formes et Affichage des caract�ristiques des formes
		//Rectangle
		Rectangle rect = new Rectangle(10.0f, 20.0f);
		ShapeTest.printAreaOf(rect);
		ShapeTest.printPerimeterOf(rect);
		//Cercle
		Circle circ = new Circle(10.0f);
		ShapeTest.printAreaOf(circ);
		ShapeTest.printPerimeterOf(circ);
		//Carr�
		Square sqr = new Square(1.0f);
		ShapeTest.printAreaOf(sqr);
		ShapeTest.printPerimeterOf(sqr);
		//Triangle
		Triangle triang = new Triangle(3f,4f,5f);
		ShapeTest.printAreaOf(triang);
		ShapeTest.printPerimeterOf(triang);
		//Polygone r�gulier n c�t�s
	    System.out.println("Saisie du nombre de cot�s du polygone:");
	    int nSide = scan.nextInt();
	    System.out.println("Saisie du rayon du polygone:");
	    float radius = scan.nextFloat();
		RegularPolygon poly_nSide=new RegularPolygon(radius, nSide);
		ShapeTest.printAreaOf(poly_nSide);
		ShapeTest.printPerimeterOf(poly_nSide);
		
		//Fermeture scanner
		scan.close();
		
	}

}
