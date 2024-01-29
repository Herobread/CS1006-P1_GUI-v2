import java.util.ArrayList;
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

	public void addCaveEntity(Entity entity, Coordinates coordinates) {
		addCaveEntity(entity, coordinates.getX(), coordinates.getY());
	}

	// cave info:
	public void addCaveEntity(Entity entity, int x, int y) {
		getCave(x, y).addEntity(entity);
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

	// extra:
	// public void isTherePath() {
	// // check if there is a path
	// }
}
