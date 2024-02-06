package game;

import java.awt.Color;

import game.model.CaveSystem;
import game.model.WorldGenerator;
import game.view.Renderer;

public class CaveMapMain {
	public static void main(String[] args) {
		final int SIZE = 16;
		final int SCREEN_HEIGHT = 700;
		final int TEXTURE_SIZE = SCREEN_HEIGHT / SIZE;
		final int SCREEN_WIDTH = SIZE * TEXTURE_SIZE;

		CaveSystem caves = new CaveSystem(SIZE, SIZE);
		Renderer renderer = Renderer.getInstance();
		renderer.setDimensions(SCREEN_WIDTH, SCREEN_HEIGHT);

		WorldGenerator generator = new WorldGenerator(SIZE, SIZE);

		caves = generator.generateCellularAutomataCaves(caves);

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				String texture = "cave-" + caves.getConnectionsString(j, i);
				renderer.drawText(j + " - " + i, j * TEXTURE_SIZE, i * TEXTURE_SIZE, 12,Color.WHITE);
				renderer.drawTexture(texture, j * TEXTURE_SIZE, i * TEXTURE_SIZE, TEXTURE_SIZE, TEXTURE_SIZE);
			}
		}

		renderer.draw();
	}
}
