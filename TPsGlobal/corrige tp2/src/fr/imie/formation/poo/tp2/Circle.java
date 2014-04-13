package fr.imie.formation.poo.tp2;

public class Circle implements Shape {
	
	private float radius;
	
	public Circle(float radius) {
		if (radius < 0) {
			throw new IllegalArgumentException("Le rayon doit tre positif !");
		}
		this.radius = radius;
	}

	public float area() {
		return (float) (Math.PI * radius * radius);
	}

	public float perimeter() {
		return (float) (2 * Math.PI * radius);
	}
	
	public String getTitle() {
		return "cercle";
	}

}
