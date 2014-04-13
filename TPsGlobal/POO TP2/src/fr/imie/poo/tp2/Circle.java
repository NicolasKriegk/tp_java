package fr.imie.poo.tp2;

//Utilisation version héritage de classe abstraite---------------------------------------------------------
//public class Circle extends Shape {

//Utilisation version interface---------------------------------------------------------
public class Circle implements Shape {

	//Attributs
	private float radius;
	
	//Constructeur
	public Circle(float radius) {
		if (radius <= 0f) {
			throw new IllegalArgumentException("Les valeurs doivent etre positives.");
		}
		this.radius = radius;
		
	}

	public float area() {

		return (float) (Math.PI * (Math.pow((double) radius,2)));

	}

	public float perimeter() {

		return (float) (Math.PI* radius * 2);

	}

	public String getTitle() {
		return "Cercle";
	}

}
