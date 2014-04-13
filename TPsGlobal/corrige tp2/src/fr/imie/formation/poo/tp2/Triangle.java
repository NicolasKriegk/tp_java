package fr.imie.formation.poo.tp2;

public class Triangle implements Shape {
	
	private float side1, side2, side3;
	
	public Triangle(float side1, float side2, float side3) {
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	public String getTitle() {
		return "triangle";
	}

	public float area() {
		float s = 0.5f * (side1 + side2 + side3);
		float result = (float) Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
		return result;
	}

	public float perimeter() {
		return side1 + side2 + side3;
	}

}
