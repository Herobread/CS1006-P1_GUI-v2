import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Coordinates;

public class CaveSystem {
	private int width;
	private int height;
	private Cave[][] caves;
	private Map<Coordinates, List<Coordinates>> connections;

	public CaveSystem(int width, int height) {
		this.width = width;
		this.height = height;
		this.caves = new Cave[height][width];
		this.connections = new HashMap<Coordinates, List<Coordinates>>() {
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
	public List<Coordinates> getCaveConnections(Coordinates coordinates) {
		return connections.get(coordinates);
	}
	public Coordinates[] getConnectionKeySet(){
		return connections.keySet().toArray(new Coordinates[connections.keySet().size()]);
	}
	
	// create
	public void createUndirectedConnection(Coordinates coordinates1, Coordinates coordinates2) {
		createDirectredConnection(coordinates1, coordinates2);
		createDirectredConnection(coordinates2, coordinates1);
	}

	public void createDirectredConnection(Coordinates coordinates1, Coordinates coordinates2) {
		if (coordinates1.equals(coordinates2)) {
			return;
		}

		List<Coordinates> cave1Connections = getCaveConnections(coordinates1);
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
	// public void generateCaveSystem() {
	// // use some algorithm
	// }

	// extra:
	// public void isTherePath() {
	// // check if there is a path
	// }
}
