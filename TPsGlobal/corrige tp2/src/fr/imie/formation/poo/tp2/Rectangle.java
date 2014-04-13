package fr.imie.formation.poo.tp2;

public class Rectangle implements Shape {
	
	private float width, height;
	
	public Rectangle(float w, float h) {
		width = w;
		height = h;
	}

	public float area() {
		return width * height;
	}

	public float perimeter() {
		return 2 * (width + height);
	}
	
	public String getTitle() {
		return "rectangle";
	}

}
