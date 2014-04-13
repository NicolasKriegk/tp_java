package fr.imie.poo.tp2;

// Essai de composition avec une liste de triangles 
//import java.util.ArrayList;
//import java.util.List;


//Utilisation version héritage de classe abstraite---------------------------------------------------------
//public class RegularPolygon extends Shape {

//Utilisation version interface---------------------------------------------------------
public class RegularPolygon implements Shape {

	//Attributs
	private float radius, lenght;
	private int nSide;
	private Triangle triang;
	
	// Essai de composition avec une liste de triangles 
//	private List<Triangle> triangles;
	

	//Constructeur
	public RegularPolygon(float radius, int nSide) {
		if ((radius <= 0f) || (nSide <= 0f)) {
			throw new IllegalArgumentException("Les valeurs doivent etre positives.");
		}
		if (nSide < 3) {
			throw new IllegalArgumentException("Le polygone doit avoir au moins 3 côtés.");
		}
		this.radius = radius;
		this.nSide = nSide;
		lenght = (float) (2 * radius * Math.sin(Math.PI / nSide));

		// Essai de composition avec une liste de triangles 
//		triangles = new ArrayList<Triangle>(nSide);
//		for (int i = 0; i < nSide; i++){
//			triangles.add(new Triangle(radius, radius, lenght));
//		}
			
	}

	//Methodes abstraites héritées
	public float area() {
		if (triang == null) { // exemple de lazy initialisation (une création au 1er appel, puis bypass)
			triang = new Triangle(radius, radius, lenght);
		}
		return nSide * triang.area();

		// Essai de composition avec une liste de triangles 
//		float result = 0;
//		for (Triangle t : triangles) {
//			result += t.area();
//		}
	
	}

	public float perimeter() {
		return nSide * lenght;
	}

	public String getTitle() {
		return "Polygone régulier à " + nSide + " côtés";
	}

}

