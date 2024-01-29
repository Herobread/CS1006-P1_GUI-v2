import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Coordinates;

public class CaveSystem {
	private int width;
	private int height;
	private Cave[][] caves;
	private Map<Coordinates, ArrayList<Coordinates>> connections;

	public CaveSystem(int width, int height) {
		this.width = width;
		this.height = height;
		this.caves = new Cave[height][width];
		this.connections = new HashMap<Coordinates, ArrayList<Coordinates>>() {
		};
	}

	// cave system info
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	// connections:
	// get
	public ArrayList<Coordinates> getCaveConnections(Coordinates coordinates) {
		return connections.get(coordinates);
	}
	public Coordinates[] getConnectionKeySet(){
		return connections.keySet().toArray(new Coordinates[connections.keySet().size()]);
	}
	
	// create
	
	public ArrayList<Coordinates> getCaveConnections(int x, int y) {
		return getCaveConnections(new Coordinates(x, y));
	}

	// add
	public void addUndirectedConnection(Coordinates coordinates1, Coordinates coordinates2) {
		addDirectredConnection(coordinates1, coordinates2);
		addDirectredConnection(coordinates2, coordinates1);
	}

	public void addDirectredConnection(Coordinates coordinates1, Coordinates coordinates2) {
		if (coordinates1.equals(coordinates2)) {
			return;
		}

		ArrayList<Coordinates> cave1Connections = getCaveConnections(coordinates1);

		if (cave1Connections == null) {
			cave1Connections = new ArrayList<>();
		}

		cave1Connections.add(coordinates2);
		connections.put(coordinates1, cave1Connections);
	}

	// entities:
	public List<Entity> getCaveEntities(Coordinates coordinates) {
		return getCaveEntities(coordinates.getX(), coordinates.getY());
	}

	public List<Entity> getCaveEntities(int x, int y) {
		return getCave(x, y).getEntities();
	}

	public void addEntity(Entity entity, Coordinates coordinates) {
		addEntity(entity, coordinates.getX(), coordinates.getY());
	}

	public void addEntity(Entity entity, int x, int y) {
		getCave(x, y).addEntity(entity);
	}

	// cave info:
	public void setCave(Cave cave, Coordinates coordinates) {
		setCave(cave, coordinates.getX(), coordinates.getY());
	}

	public void setCave(Cave cave, int x, int y) {
		caves[y][x] = cave;
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

	// cave generation:
	public void generateCaveSystem() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {

			}
		}
	}

	// debug
	public void printCaves() {
		System.out.println("Caves:");
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				ArrayList<Coordinates> connections = getCaveConnections(i, j);

				if (connections == null) {
					// System.out.println("no connections " + i + " " + j);
					continue;
				}

				System.out.println("Cave (" + i + ", " + j + ") is connected to " + connections.toString());
			}
		}
	}

	// extra:
	// public void isTherePath() {
	// // check if there is a path
	// }
}
