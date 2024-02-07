package game.model;

import java.util.Random;

import game.utils.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldGenerator {
	private int width;
	private int height;
	private boolean[][] tiles;
	private int iterations = 2;
	final private float SOLID_PROBABILITY = 0.45f;
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

		// find separated caves:
		int[][] separatedCavesArray = floodFill(tiles);

		// store separated caves as a map (caveID - List of coordinates)
		Map<Integer, List<Coordinates>> separatedCavesMap = getDisconnectedCavesCoordinates(separatedCavesArray);

		// select random point on each island
		List<Coordinates> randomPoints = new ArrayList<>();

		for (int caveId : separatedCavesMap.keySet()) {
			List<Coordinates> caveCoordinatesList = separatedCavesMap.get(caveId);
			randomPoints.add(caveCoordinatesList.get(random.nextInt(caveCoordinatesList.size())));
		}

		// join separated islands
		drawLines(randomPoints);

		// select locations for entities
		// hazards can only spawn when there are 4 locations
		List<Coordinates> potentialSpawnCoordinates = new ArrayList<>();

		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {

				System.out.print(tiles[i][j] ? "S" : countAccessibleNeighbours(j, i));

				if (countAccessibleNeighbours(i, j) >= 4) {
					potentialSpawnCoordinates.add(new Coordinates(j, i));
				}
			}
			System.out.println();
		}

		// randomize
		Collections.shuffle(potentialSpawnCoordinates);

		int coordinateId = 0;

		int AMOUNT_OF_BATS = 4; // Adjust these values accordingly
		int AMOUNT_OF_WUMPUSES = 2;
		int AMOUNT_OF_PITS = 4;
		int AMOUNT_OF_TREASURES = 2;
		int AMOUNT_OF_ARROWS = 5;

		Map<String, List<Coordinates>> hazards = new HashMap<>();

		hazards.put("wumpus", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_WUMPUSES; i++) {
			hazards.get("wumpus").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		hazards.put("pit", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_PITS; i++) {
			hazards.get("pit").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		hazards.put("bats", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_BATS; i++) {
			hazards.get("bats").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		hazards.put("treasure", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_TREASURES; i++) {
			hazards.get("treasure").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		hazards.put("arrows", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_ARROWS; i++) {
			hazards.get("arrows").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		// Print out the map
		for (Map.Entry<String, List<Coordinates>> entry : hazards.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

		// finalize caves by creating connections on actual graph instead of just bitmap
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

	private void drawLines(List<Coordinates> points) {
		for (int i = 0; i < points.size() - 1; i++) {
			drawLine(points.get(i), points.get(i + 1));
		}
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

	private Map<Integer, List<Coordinates>> getDisconnectedCavesCoordinates(int[][] disconnectedCavesArray) {
		Map<Integer, List<Coordinates>> disconnectedCavesMap = new HashMap<>();
		int islandNumber = 1;

		int rows = disconnectedCavesArray.length;
		int cols = disconnectedCavesArray[0].length;

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				islandNumber = disconnectedCavesArray[y][x];

				// skip walls
				if (islandNumber == 0) {
					continue;
				}
				Coordinates coordinates = new Coordinates(x, y);

				disconnectedCavesMap
						.computeIfAbsent(islandNumber, k -> new ArrayList<>())
						.add(coordinates);

			}
		}

		return disconnectedCavesMap;
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

	private int countAccessibleNeighbours(int targetX, int targetY) {
		int total = 0;

		// Define directions (up, down, left, right)
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		// Iterate over all directions
		for (int i = 0; i < 4; i++) {
			int newX = targetX + dx[i];
			int newY = targetY + dy[i];

			// Check if the new position is within the grid bounds and if it's not solid
			if (!isSolid(newX, newY)) {
				total++;
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
