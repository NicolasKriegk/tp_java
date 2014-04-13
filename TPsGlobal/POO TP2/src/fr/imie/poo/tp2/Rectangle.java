package fr.imie.poo.tp2;

//Utilisation version héritage de classe abstraite---------------------------------------------------------
//public class Rectangle extends Shape {

//Utilisation version interface---------------------------------------------------------
public class Rectangle implements Shape {

	//Attributs
	private float width;
	private float height;
	
	//Constructeur
	public Rectangle(float width, float height) {
		if ((width <= 0f) || (height <= 0f)) {
			throw new IllegalArgumentException("Les valeurs doivent etre positives.");
		}
		this.width = width;
		this.height = height;
		
	}

	//Methodes abstraites héritées
	public float area() {
		
		return width * height;
		
	}

	public float perimeter() {
		
		return 2 * ( width + height );
		
	}

	public String getTitle() {
		return "Rectangle";
	}

}
