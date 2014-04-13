package fr.imie.formation.poo.tp2;

public class ShapeTest {
	
	public static void printAreaOf(Shape shape) {
		System.out.format("Aire du %s : %f%n", shape.getTitle(), shape.area());
	}
	
	public static void printPerimeterOf(Shape shape) {
		System.out.format("Périmètre : %f%n", shape.perimeter());
	}

	public static void main(String[] args) {
		Shape shape = new Rectangle(10, 20);
		ShapeTest.printAreaOf(shape);
		try {
			shape = new Circle(-10);
		} catch (IllegalArgumentException e) {
			System.out.println("L'utilisateur a entré n'importe quoi !");
		}
		ShapeTest.printAreaOf(shape);
		shape = new Square(10);
		ShapeTest.printAreaOf(shape);
		shape = new Triangle(10, 10, 10);
		ShapeTest.printAreaOf(shape);
		shape = new RegularPolygon(1f, 1000);
		ShapeTest.printAreaOf(shape);
	}

}
