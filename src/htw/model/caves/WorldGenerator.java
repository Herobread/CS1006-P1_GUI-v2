package htw.model.caves;

import java.util.Random;

import htw.controllers.game.GameStateManager;
import htw.model.entities.Arrow;
import htw.model.entities.Bat;
import htw.model.entities.Entity;
import htw.model.entities.Pit;
import htw.model.entities.Player;
import htw.model.entities.Treasure;
import htw.model.entities.Wumpus;
import htw.utils.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldGenerator {
	private GameStateManager gameStateManager = GameStateManager.getInstance();
	private int width;
	private int height;
	private boolean[][] tiles;
	private int iterations = 2;
	final private float SOLID_PROBABILITY = 0.45f;
	final private float MIN_NEIGHBOURS = 4;
	final private float MAX_NEIGHBOURS = 4;

	String[] decorationTextureNames = { "mushroom-brown", "mushroom-red", "broken-arrow", "broken-bow", "rock-1",
			"rock-2" };

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

		// locations for random debree inside the cave
		int[][] decorationSpawnCoordinatePairs = {
				{ 192, 96 }, { 265, 80 }, { 132, 180 }, { 110, 238 }, { 168, 243 },
				{ 364, 176 }, { 344, 224 }, { 399, 238 }, { 344, 283 }, { 156, 316 },
				{ 228, 331 }, { 293, 326 }, { 192, 384 }, { 263, 385 }
		};

		// Create list of Coordinates
		List<Coordinates> decorationSpawnCoordinates = new ArrayList<>();

		// Add coordinates from the array to the list
		for (int[] pair : decorationSpawnCoordinatePairs) {
			decorationSpawnCoordinates.add(new Coordinates(pair[0], pair[1]));
		}

		// finalize caves by creating connections on actual graph instead of just bitmap
		for (int y = 0; y < height; y += 1) {
			for (int x = 0; x < width; x += 1) {
				Coordinates currentCoordinates = new Coordinates(x, y);
				ArrayList<Coordinates> coordinates = getNeighbouringCoordinates(x, y);

				if (coordinates == null) {
					continue;
				}

				Cave cave = new Cave();

				int totalNeighbours = coordinates.size();

				if (totalNeighbours > 3) {
					int randomLocationId = random.nextInt(decorationSpawnCoordinates.size());
					int randomTextureId = random.nextInt(decorationTextureNames.length);

					Coordinates randomLocation = decorationSpawnCoordinates.get(randomLocationId);
					String randomTextureName = decorationTextureNames[randomTextureId];

					Decoration decoration = new Decoration(randomLocation, randomTextureName);
					cave.addDecoration(decoration);
				}

				caves.setCave(cave, currentCoordinates);

				for (Coordinates coordinate : coordinates) {
					caves.addDirectredConnection(currentCoordinates, coordinate);
				}
			}
		}

		// select locations for entities
		// entities can only spawn when there are 4 locations
		List<Coordinates> potentialSpawnCoordinates = new ArrayList<>();

		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				if (countAccessibleNeighbours(x, y) >= 4) {
					potentialSpawnCoordinates.add(new Coordinates(x, y));
				}
			}
		}

		// randomize
		Collections.shuffle(potentialSpawnCoordinates);

		int spawnableArea = potentialSpawnCoordinates.size();

		int coordinateId = 0;

		int AMOUNT_OF_BATS = spawnableArea / 15;
		int AMOUNT_OF_WUMPUSES = 2;
		int AMOUNT_OF_PITS = spawnableArea / 50;
		int AMOUNT_OF_TREASURES = spawnableArea / 50;
		int AMOUNT_OF_ARROWS = spawnableArea / 20;

		gameStateManager.setAmountOfBats(AMOUNT_OF_BATS);
		gameStateManager.setAmountOfWumpuses(AMOUNT_OF_WUMPUSES);
		gameStateManager.setAmountOfPits(AMOUNT_OF_PITS);
		gameStateManager.setAmountOfTreasures(AMOUNT_OF_TREASURES);
		gameStateManager.setAmountOfArrows(AMOUNT_OF_ARROWS);

		Map<String, List<Coordinates>> entities = new HashMap<>();

		entities.put("wumpus", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_WUMPUSES; i++) {
			entities.get("wumpus").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		entities.put("pit", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_PITS; i++) {
			entities.get("pit").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		entities.put("bat", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_BATS; i++) {
			entities.get("bat").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		entities.put("treasure", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_TREASURES; i++) {
			entities.get("treasure").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		entities.put("arrow", new ArrayList<>());
		for (int i = 0; i < AMOUNT_OF_ARROWS; i++) {
			entities.get("arrow").add(potentialSpawnCoordinates.get(coordinateId));
			coordinateId += 1;
		}

		Player player = gameStateManager.getPlayer();

		player.setCoordinates(potentialSpawnCoordinates.get(coordinateId));
		coordinateId += 1;

		for (String entityName : entities.keySet()) {
			List<Coordinates> entitySpawnLocations = entities.get(entityName);

			for (Coordinates entitySpawnLocation : entitySpawnLocations) {
				Entity entity = null;

				switch (entityName) {
					case "bat":
						entity = new Bat();
						break;
					case "wumpus":
						entity = new Wumpus();
						break;
					case "pit":
						entity = new Pit();
						break;
					case "arrow":
						entity = new Arrow();
						break;
					case "treasure":
						entity = new Treasure();
						break;
					default:
						entity = new Entity(entityName, entityName);
						break;
				}

				caves.addEntity(entity, entitySpawnLocation);
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
		ArrayList<Coordinates> neighbours = getNeighbouringCoordinates(targetX, targetY);

		if (neighbours == null) {
			return 0;
		}

		return neighbours.size();
	}

	private boolean isSolid(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return true;
		}

		return tiles[y][x];
	}
}
