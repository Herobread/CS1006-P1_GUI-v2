import utils.Coordinates;

class Main {
	public static void main(String[] args) {
		CaveSystem caves = new CaveSystem(0, 0);

		Cave cave = new Cave(false);

		caves.setCave(cave, new Coordinates(0, 0));

		Entity entity = new Entity("Test");

		caves.addEntity(entity, new Coordinates(0, 0));

	}
}