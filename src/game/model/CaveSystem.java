package game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

import game.model.entities.Entity;
import game.utils.Coordinates;

public class CaveSystem {
	/**
	 * The width of the cave system.
	 */
	private int width;

	/**
	 * The height of the cave system.
	 */
	private int height;

	/**
	 * 2D array representing the caves in the system.
	 */
	private Cave[][] caves;

	/**
	 * A map containing the connections between cave coordinates.
	 */
	private Map<Coordinates, HashSet<Coordinates>> connections;

	public CaveSystem(int width, int height) {
		this.width = width;
		this.height = height;
		this.caves = new Cave[height][width];
		this.connections = new HashMap<Coordinates, HashSet<Coordinates>>() {
		};
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Retrieves the connections of a cave at the specified coordinates.
	 *
	 * @param coordinates The coordinates of the cave.
	 * @return Connected caves' coordinates, or an empty list if none exist.
	 */
	public HashSet<Coordinates> getCaveConnections(Coordinates coordinates) {
		return connections.get(coordinates);
	}

	public Coordinates[] getConnectionKeySet() {
		return connections.keySet().toArray(new Coordinates[connections.keySet().size()]);
	}

	/**
	 * This method returns a string representing the directions of connected caves
	 * based on the provided coordinates.
	 * The order of directions in the result string is: up, right, down, left.
	 * (urdl)
	 * 
	 * @param x The coordinates of the current cave.
	 * @param y The coordinates of the current cave.
	 * @return A string representing the directions of connected caves. For example,
	 *         the string "rdl" = right, down and left.
	 */
	public String getConnectionsString(int x, int y) {
		return getConnectionsString(new Coordinates(x, y));
	}

	/**
	 * This method returns a string representing the directions of connected caves
	 * based on the provided coordinates.
	 * The order of directions in the result string is: up, right, down, left.
	 * (urdl)
	 *
	 * @param currentCaveCoordinates The coordinates of the current cave.
	 * @return A string representing the directions of connected caves. For example,
	 *         the string "rdl" = right, down and left.
	 */
	public String getConnectionsString(Coordinates currentCaveCoordinates) {
		String res = "";
		HashSet<Coordinates> connections = getCaveConnections(currentCaveCoordinates);

		if (connections == null) {
			return "";
		}

		boolean isUp = false;
		boolean isRight = false;
		boolean isDown = false;
		boolean isLeft = false;

		for (Coordinates connectedCaveCoordinates : connections) {
			int x1 = connectedCaveCoordinates.getX();
			int y1 = connectedCaveCoordinates.getY();

			int x2 = currentCaveCoordinates.getX();
			int y2 = currentCaveCoordinates.getY();

			if (x1 == x2 - 1) {
				isLeft = true;
			} else if (x1 == x2 + 1) {
				isRight = true;
			} else if (y1 == y2 + 1) {
				isDown = true;
			} else if (y1 == y2 - 1) {
				isUp = true;
			}
		}

		// order result in shape: urdl
		if (isUp) {
			res += "u";
		}
		if (isRight) {
			res += "r";
		}
		if (isDown) {
			res += "d";
		}
		if (isLeft) {
			res += "l";
		}

		return res;
	}

	/**
	 * Retrieves the connections of a cave at the specified coordinates.
	 *
	 * @param x The x-coordinate of the cave.
	 * @param y The y-coordinate of the cave.
	 * @return Connected caves' coordinates, or an empty list if none exist.
	 */
	public HashSet<Coordinates> getCaveConnections(int x, int y) {
		return getCaveConnections(new Coordinates(x, y));
	}

	/**
	 * Adds an undirected connection between two caves.
	 * 
	 * One way connection
	 *
	 * @param coordinates1 Coordinates of the first cave.
	 * @param coordinates2 Coordinates of the second cave.
	 */
	public void addUndirectedConnection(Coordinates coordinates1, Coordinates coordinates2) {
		addDirectredConnection(coordinates1, coordinates2);
		addDirectredConnection(coordinates2, coordinates1);
	}

	/**
	 * Adds an directed connection between two caves.
	 *
	 * 2-way connection
	 * 
	 * @param coordinates1 Coordinates of the first cave.
	 * @param coordinates2 Coordinates of the second cave.
	 */
	public void addDirectredConnection(Coordinates coordinates1, Coordinates coordinates2) {
		if (coordinates1.equals(coordinates2)) {
			return;
		}

		HashSet<Coordinates> cave1Connections = getCaveConnections(coordinates1);

		if (cave1Connections == null) {
			cave1Connections = new HashSet<>();
		}

		cave1Connections.add(coordinates2);
		connections.put(coordinates1, cave1Connections);
	}

	/**
	 * Retrieves the entities present in the cave at the specified coordinates.
	 *
	 * @param coordinates The coordinates of the cave.
	 * @return ArrayList of entities in the cave.
	 */
	public ArrayList<Entity> getCaveEntities(Coordinates coordinates) {
		return getCaveEntities(coordinates.getX(), coordinates.getY());
	}

	/**
	 * Retrieves the entities present in the cave at the specified coordinates.
	 *
	 * @param x The x-coordinate of the cave.
	 * @param y The y-coordinate of the cave.
	 * @return ArrayList of entities in the cave.
	 */
	public ArrayList<Entity> getCaveEntities(int x, int y) {
		if (getCave(x, y) == null) {
			return null;
		}

		return getCave(x, y).getEntities();
	}

	/**
	 * Adds an entity to the cave at the specified coordinates.
	 *
	 * @param entity      The entity to be added.
	 * @param coordinates The coordinates of the cave.
	 */
	public void addEntity(Entity entity, Coordinates coordinates) {
		addEntity(entity, coordinates.getX(), coordinates.getY());
	}

	/**
	 * Adds an entity to the cave at the specified coordinates.
	 *
	 * @param entity The entity to be added.
	 * @param x      The x-coordinate of the cave.
	 * @param y      The y-coordinate of the cave.
	 */
	public void addEntity(Entity entity, int x, int y) {
		Cave cave = getCave(x, y);

		if (cave == null) {
			throw new Error("Cave " + x + ", " + y + " is not defined,");
		}

		cave.addEntity(entity);
	}

	/**
	 * Sets the properties of a cave at the specified coordinates.
	 *
	 * @param cave        The cave to be set.
	 * @param coordinates The coordinates of the cave.
	 */
	public void setCave(Cave cave, Coordinates coordinates) {
		setCave(cave, coordinates.getX(), coordinates.getY());
	}

	/**
	 * Sets the properties of a cave at the specified coordinates.
	 *
	 * @param cave The cave to be set.
	 * @param x    The x-coordinate of the cave.
	 * @param y    The y-coordinate of the cave.
	 */
	public void setCave(Cave cave, int x, int y) {
		caves[y][x] = cave;
	}

	public boolean isSolid(Coordinates coordinates) {
		return isSolid(coordinates.getX(), coordinates.getY());
	}

	public boolean isSolid(int x, int y) {
		return getCave(x, y) == null;
	}

	public Cave getCave(Coordinates coordinates) {
		return getCave(coordinates.getX(), coordinates.getY());
	}

	public Cave getCave(int x, int y) {
		return caves[y][x];
	}

	public Cave[][] getCaves() {
		return caves;
	}

	public void setCaves(Cave[][] caves) {
		this.caves = caves;
	}

	// cave initialisation:
	public void initialiseCaveSystem() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Cave currentCave = new Cave();
				Coordinates currentCoordinates = new Coordinates(j, i);

				setCave(currentCave, currentCoordinates);
			}
		}
	}
}
