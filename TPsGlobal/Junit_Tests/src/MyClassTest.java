import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MyClassTest {

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

//	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testTrue() {
		MyClass classeATester = new MyClass();
		assertEquals(7, classeATester.add(4, 3));
	}

	@Test
	public void testFalse() {
		MyClass classeATester = new MyClass();
		long expected = (long) 8;
		assertEquals("Devrait renvoyer " + expected, expected, classeATester.add(4, 3));
	}

}
