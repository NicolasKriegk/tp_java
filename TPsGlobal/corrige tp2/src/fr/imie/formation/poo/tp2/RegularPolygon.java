package fr.imie.formation.poo.tp2;

public class RegularPolygon implements Shape {
	
	private float sideLength, radius;
	private int sides;
	private Triangle triangle;
	
	public RegularPolygon(float r, int n) {
		sides = n;
		radius = r;
		sideLength = (float) (2 * r * Math.sin(Math.PI / n));
	}
	
	public RegularPolygon(int n, float s) {
		sides = n;
		sideLength = s;
		radius = (float) (s / (2 * (Math.sin(Math.PI / n))));
	}

	public String getTitle() {
		return "polygone régulier";
	}

	public float area() {
		if (triangle == null) {
			triangle = new Triangle(radius, radius, sideLength);
		}
		return sides * triangle.area();
	}

	public float perimeter() {
		return sides * sideLength;
	}
	
}
