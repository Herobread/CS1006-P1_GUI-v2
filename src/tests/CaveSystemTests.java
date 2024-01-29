package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.*;

import game.Cave;
import game.CaveSystem;
import game.Entity;
import game.utils.Coordinates;

public class CaveSystemTests {
	@Test
	public void getConnectionsStringUpTest() {
		CaveSystem caves = new CaveSystem(10, 10);

		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 0));

		assertEquals("check connections string", "u", caves.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringDownTest() {
		CaveSystem caves = new CaveSystem(10, 10);

		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 2));

		assertEquals("check connections string", "d", caves.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringLeftTest() {
		CaveSystem caves = new CaveSystem(10, 10);

		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(0, 1));

		assertEquals("check connections string", "l", caves.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringRightTest() {
		CaveSystem caves = new CaveSystem(10, 10);

		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(2, 1));

		assertEquals("check connections string", "r", caves.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringFourTest() {
		CaveSystem caves = new CaveSystem(10, 10);

		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(2, 1));
		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(0, 1));
		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 2));
		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 0));

		assertEquals("check connections string", "dlru", caves.getConnectionsString(1, 1));
	}

	@Test
	public void getConnectionsStringTwoTest() {
		CaveSystem caves = new CaveSystem(10, 10);

		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(2, 1));
		caves.addUndirectedConnection(new Coordinates(1, 1), new Coordinates(1, 0));

		assertEquals("check connections string", "ru", caves.getConnectionsString(1, 1));
	}

	@Test
	public void entityTest() {
		CaveSystem caves = new CaveSystem(20, 20);

		caves.setCave(new Cave(false), new Coordinates(5, 3));
		caves.addEntity(new Entity("Wumpus"), new Coordinates(5, 3));

		ArrayList<Entity> entities = caves.getCaveEntities(new Coordinates(5, 3));

		assertEquals(entities.get(0).getName(), "Wumpus");
	}
}
