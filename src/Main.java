class Main {
	public static void main(String[] args) {
		CaveSystem caves = new CaveSystem(0, 0);

		Cave cave = new Cave(false);
		Entity entity = new Entity("Test");
		cave.addEntity(entity);
	}
}