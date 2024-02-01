package game;

import java.util.Random;

public class WorldGenerator {
	private int width;
	private int height;
	private boolean[][] tiles;
	private int iterations = 2;

	public WorldGenerator(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new boolean[height][width];
	}

	public Cave[][] generateCellularAutomataCaves(CaveSystem caves) {
		Random random = new Random();

		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				boolean isSolid = random.nextFloat() < 0.38 ? true : false;

				tiles[y][x] = isSolid;
			}
		}

		printTiles();

		for (int i = 0; i < iterations; i++) {
			iterate();
		}

		printTiles();

		return caves.getCaves();
	}

	private void printTiles() {
		System.out.println("=============================================================================");
		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				System.out.print(
						isSolid(x, y) ? "X" : " ");
			}
			System.out.println();
		}
		System.out.println("=============================================================================");
	}

	private void iterate() {
		boolean[][] newTiles = new boolean[height][width];

		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				int neighbours = countNeighbours(x, y);
				if (neighbours > 4) {
					newTiles[y][x] = true;
				} else if (neighbours < 4) {
					newTiles[y][x] = false;
				}
			}
		}

		tiles = newTiles;
	}

	private int countNeighbours(int targetX, int targetY) {
		int total = 0;

		for (int y = -1; y <= 1; y += 1) {
			for (int x = -1; x <= 1; x += 1) {
				if (isSolid(x + targetX, y + targetY)) {
					total += 1;
				}
			}
		}

		return total;
	}

	private boolean isSolid(int x, int y) {
		if (x < 1 || x >= width - 1 || y < 1 || y >= height - 1) {
			return true;
		}

		return tiles[y][x];
	}
}
