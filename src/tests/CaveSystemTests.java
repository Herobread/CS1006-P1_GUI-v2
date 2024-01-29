package tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import game.CaveSystem;
import game.utils.Coordinates;

public class CaveSystemTests {
	@Test
	public void getConnectionsStringUpTest() {
		CaveSystem cave = new CaveSystem(10, 10);

		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 0));

		assertEquals("check connections string", "u", cave.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringDownTest() {
		CaveSystem cave = new CaveSystem(10, 10);

		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 2));

		assertEquals("check connections string", "d", cave.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringLeftTest() {
		CaveSystem cave = new CaveSystem(10, 10);

		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(0, 1));

		assertEquals("check connections string", "l", cave.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringRightTest() {
		CaveSystem cave = new CaveSystem(10, 10);

		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(2, 1));

		assertEquals("check connections string", "r", cave.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringFourTest() {
		CaveSystem cave = new CaveSystem(10, 10);

		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(2, 1));
		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(0, 1));
		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 2));
		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 0));

		assertEquals("check connections string", "dlru", cave.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringTwoTest() {
		CaveSystem cave = new CaveSystem(10, 10);

		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(2, 1));
		cave.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 0));

		assertEquals("check connections string", "ru", cave.getConnectionsString(1, 1));
	}
}
