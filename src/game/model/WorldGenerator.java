package game.model;

import java.util.Random;
import java.util.Vector;

import game.utils.Coordinates;

import java.util.ArrayList;

public class WorldGenerator {
	private int width;
	private int height;
	private boolean[][] tiles;
	private int iterations = 2;
	final private float SOLID_PROBABILITY = 0.9f; // 0.38f
	final private float MIN_NEIGHBOURS = 4;
	final private float MAX_NEIGHBOURS = 4;

	public WorldGenerator(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new boolean[height][width];
	}

	public CaveSystem generateCellularAutomataCaves(CaveSystem caves) {
		Random random = new Random();

		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				boolean isSolid = random.nextFloat() < SOLID_PROBABILITY ? true : false;

				tiles[y][x] = isSolid;
			}
		}

		for (int i = 0; i < iterations; i++) {
			iterate();
		}

		printTiles();

		// find separated caves:
		int[][] separatedIslands = floodFill(tiles);

		for (int i = 0; i < separatedIslands.length; i++) {
			for (int j = 0; j < separatedIslands[i].length; j++) {
				System.out.print(separatedIslands[i][j]);
			}
			System.out.println();
		}

		// join caves
		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				Coordinates currentCoordinates = new Coordinates(x, y);
				ArrayList<Coordinates> coordinates = getNeighbouringCoordinates(x, y);

				if (coordinates == null) {
					continue;
				}

				caves.setCave(new Cave(), currentCoordinates);

				for (Coordinates coordinate : coordinates) {
					caves.addDirectredConnection(currentCoordinates, coordinate);
				}
			}
		}

		return caves;
	}

	// draws L shaped line on bitmap
	public void drawLine(Coordinates start, Coordinates end) {
		int x1 = start.getX();
		int y1 = start.getY();
		int x2 = end.getX();
		int y2 = end.getY();

		int directionX = (x2 - x1) > 0 ? 1 : -1;
		int directionY = (y2 - y1) > 0 ? 1 : -1;

		int currentX = x1;
		int currentY = y1;

		tiles[currentY][currentX] = false;

		while (currentX != x2) {
			currentX += directionX;
			tiles[currentY][currentX] = false;
		}

		while (currentY != y2) {
			currentY += directionY;
			tiles[currentY][currentX] = false;
		}
	}

	private int[][] floodFill(boolean[][] tiles) {
		if (tiles == null || tiles.length == 0 || tiles[0].length == 0) {
			return new int[0][0];
		}

		int rows = tiles.length;
		int cols = tiles[0].length;
		int[][] result = new int[rows][cols];
		int islandCount = 0;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (!tiles[i][j] && result[i][j] == 0) {
					islandCount++;
					floodFillHelper(tiles, result, i, j, islandCount);
				}
			}
		}

		return result;
	}

	private void floodFillHelper(boolean[][] tiles, int[][] result, int row, int col, int islandCount) {
		if (row < 0 || row >= tiles.length || col < 0 || col >= tiles[0].length || tiles[row][col]
				|| result[row][col] > 0) {
			return;
		}

		result[row][col] = islandCount;

		// Recursive flood fill in 4 directions
		floodFillHelper(tiles, result, row + 1, col, islandCount); // Down
		floodFillHelper(tiles, result, row - 1, col, islandCount); // Up
		floodFillHelper(tiles, result, row, col + 1, islandCount); // Right
		floodFillHelper(tiles, result, row, col - 1, islandCount); // Left
	}

	private ArrayList<Coordinates> getNeighbouringCoordinates(int x, int y) {
		ArrayList<Coordinates> neighbors = new ArrayList<>();

		// Exclude the tile itself if it is solid
		if (isSolid(x, y)) {
			return null;
		}

		// Add up neighbor if not solid
		if (y > 0 && !isSolid(x, y - 1)) {
			neighbors.add(new Coordinates(x, y - 1));
		}

		// Add right neighbor if not solid
		if (x < width - 1 && !isSolid(x + 1, y)) {
			neighbors.add(new Coordinates(x + 1, y));
		}

		// Add down neighbor if not solid
		if (y < height - 1 && !isSolid(x, y + 1)) {
			neighbors.add(new Coordinates(x, y + 1));
		}

		// Add left neighbor if not solid
		if (x > 0 && !isSolid(x - 1, y)) {
			neighbors.add(new Coordinates(x - 1, y));
		}

		return neighbors;
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

				if (neighbours > MIN_NEIGHBOURS) {
					newTiles[y][x] = true;
				} else if (neighbours < MAX_NEIGHBOURS) {
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
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return true;
		}

		return tiles[y][x];
	}
}
