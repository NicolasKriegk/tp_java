package fr.imie.formation.poo.tp2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void testArea() {
		Rectangle rectangle = new Rectangle(10f, 20f);
		assertEquals(200f, rectangle.area(), 0.001f);
		
		rectangle = new Rectangle(20f, 10f);
		assertEquals(200f, rectangle.area(), 0.001f);
		
		rectangle = new Rectangle(0f, 10f);
		assertEquals(0f, rectangle.area(), 0.001f);
	}
	
	@Test
	public void testPerimeter() {
		Rectangle rectangle = new Rectangle(10f, 20f);
		assertEquals(60f, rectangle.perimeter(), 0.001f);
		
		rectangle = new Rectangle(20f, 10f);
		assertEquals(60f, rectangle.perimeter(), 0.001f);
		
		rectangle = new Rectangle(0f, 10f);
		assertEquals(20f, rectangle.perimeter(), 0.001f);
	}
	
}
