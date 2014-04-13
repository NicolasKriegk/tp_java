package org.imie.tests;

import static org.junit.Assert.assertEquals;

import org.imie.junit.projet.Capteur;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CapteurTest {

	Capteur capt = new Capteur();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCapteur() throws Exception {
		//ecart nul, retour "OK"
		capt.set
		assertEquals("",capt.Ecart(1, 1, 3.6));
		//ecart "Faible"
		//ecart "Fort"
		//ecart par defaut "Fort"
		
	}

}
