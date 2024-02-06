package tests;

import static org.junit.Assert.assertEquals;
import game.model.*;
import org.junit.*;

public class CaveSystemTests {

	@Test
	public void dimensionsTest() {
		CaveSystem cs = new CaveSystem(20, 30);
		assertEquals(cs.getHeight(), 30);
		assertEquals(cs.getWidth(), 20);
	}

	@Test
	public void Test() {
		CaveSystem cs = new CaveSystem(20, 30);
		assertEquals(cs.getHeight(), 30);
		assertEquals(cs.getWidth(), 20);
	}
}
