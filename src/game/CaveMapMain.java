package game;

public class CaveMapMain {
	public static void main(String[] args) {
		final int SIZE = 16;
		final int TEXTURE_SIZE = 64;
		CaveSystem caves = new CaveSystem(SIZE, SIZE);
		Renderer renderer = new Renderer();
		WorldGenerator generator = new WorldGenerator(SIZE, SIZE);

		caves.initialiseCaveSystem();

		caves.setCaves(generator.generateCellularAutomataCaves(caves));

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				String texture = "cave-" + caves.getConnectionsString(j, i);
				renderer.drawText(j + " - " + i, j * TEXTURE_SIZE, i * TEXTURE_SIZE, 23);
				renderer.drawTexture(texture, j * TEXTURE_SIZE, i * TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
			}
		}

		renderer.draw();
	}
}
