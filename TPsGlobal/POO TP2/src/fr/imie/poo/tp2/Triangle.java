package fr.imie.poo.tp2;

//Utilisation version héritage de classe abstraite---------------------------------------------------------
//public class Triangle extends Shape {

//Utilisation version interface---------------------------------------------------------
public class Triangle implements Shape {

	//Attributs
	private float side1;
	private float side2;
	private float side3;
	
	//Constructeur
	public Triangle(float side1, float side2, float side3) {
		if ((side1 <= 0f) || (side2 <= 0f) || (side3 <= 0f)) {
			throw new IllegalArgumentException("Les valeurs doivent etre positives.");
		}
		if ((side1 + side2 <= side3) || (side1 + side3 <= side2) || (side2 + side3 <= side1)) {
			throw new IllegalArgumentException("La somme des 2 plus petits côtés doit être strictement supérieure à la longueur du 3ème.");
		}
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
		
	}

	//Methodes abstraites héritées
	public float area() {
		
		float tmp_fl = ( side1 + side2 + side3 ) / 2;
		float a = tmp_fl * ( tmp_fl - side1 );
		a = a * (tmp_fl - side2 ) ;
		a = a * (tmp_fl - side3 ) ;
		return (float) Math.sqrt(a);

	}

	public float perimeter() {
		
		return (side1 + side2 + side3 );
		
	}

	public String getTitle() {
		return "Triangle";
	}

}
