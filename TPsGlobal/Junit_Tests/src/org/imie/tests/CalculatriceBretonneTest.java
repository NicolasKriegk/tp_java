package org.imie.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import org.imie.junit.projet.CalculatriceBretonne;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatriceBretonneTest {

	CalculatriceBretonne calc = new CalculatriceBretonne();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Avant");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Après");
	}

	@Test
	public void testAdditionOK() throws Exception {
		//test normal
		assertEquals(7, calc.addition(4, 3));
		//test au moins 1 parametre < 0
		assertEquals(-1, calc.addition(4, -3));
		assertEquals(-1, calc.addition(-4, 3));
		assertEquals(-1, calc.addition(-4, -3));
	}

	@Test
	public void testMultiplication(){
		//test normal
		assertEquals(12, calc.multiplication(4, 3));
		//test au moins 1 parametre < 0
		assertEquals(-1, calc.multiplication(4, -3));
		assertEquals(-1, calc.multiplication(-4, 3));
		assertEquals(-1, calc.multiplication(-4, -3));
	}

	@Test
	public void testDivision() throws Exception {
		//test normal
		assertEquals(4, calc.division(8, 2));
		//test au moins 1 parametre < 0
		assertEquals(-1, calc.division(4, -3));
		assertEquals(-1, calc.division(-4, 3));
		assertEquals(-1, calc.division(-4, -3));
		//test division par 0
		assertEquals(0, calc.division(4, 0));
	}

}
