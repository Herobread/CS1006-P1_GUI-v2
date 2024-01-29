package game;
// just testing cave system here, will be removed in final

import game.utils.Coordinates;

public class CaveTesting {
	public static void main(String[] args) {
		CaveSystem caves = new CaveSystem(20, 20);
		// caves.generateCaveSystem();

		// caves.addEntity(new Entity("Bat"), new Coordinates(19, 19));

		// ArrayList<Coordinates> connections = caves.getCaveConnections(new
		// Coordinates(0, 0));

		// System.out.println(connections.toString());

		// caves.printCaves();

		caves.addUndirectedConnection(new Coordinates(19, 19), new Coordinates(19, 18));

		System.out.println(
				caves.getConnectionsString(19, 19));
	}
}
