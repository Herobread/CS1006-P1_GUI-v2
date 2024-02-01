package game;

public class CaveMapMain {
	public static void main(String[] args) {
		final int SIZE = 50;
		final int TEXTURE_SIZE = 16;
		CaveSystem caves = new CaveSystem(SIZE, SIZE);
		Renderer renderer = new Renderer(1024, 900);
		WorldGenerator generator = new WorldGenerator(SIZE, SIZE);

		// caves.initialiseCaveSystem();

		caves = generator.generateCellularAutomataCaves(caves);

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				String texture = "cave-" + caves.getConnectionsString(j, i);
				// renderer.drawText(j + " - " + i, j * TEXTURE_SIZE, i * TEXTURE_SIZE, 12);
				renderer.drawTexture(texture, j * TEXTURE_SIZE, i * TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
			}
		}

		renderer.draw();
	}
}
