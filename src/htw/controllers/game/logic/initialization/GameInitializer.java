package htw.controllers.game.logic.initialization;

import htw.controllers.game.GameStateManager;
import htw.controllers.game.GameStatus;
import htw.model.caves.CaveSystem;
import htw.model.caves.WorldGenerator;
import htw.model.entities.Player;
import htw.model.map.ExploredMap;
import htw.model.map.ExploredMap.TileState;

/**
 * The GameInitializer class is responsible for initializing the game state and
 * its components.
 * It sets up the player, caves, explored map, and other necessary variables to
 * start the game.
 */
public class GameInitializer {
	/**
	 * Initializes the game state by creating a player, generating caves, and
	 * setting up the explored map.
	 */
	public static void initialize() {
		GameStateManager gameStateManager = GameStateManager.getInstance();

		gameStateManager.setGameStatus(GameStatus.PLAYING);

		Player player = new Player(0, 0);
		gameStateManager.setPlayer(player);

		final int WORLD_SIZE = 16;

		// Create caves using cellular automata algorithm
		CaveSystem caves = new CaveSystem(WORLD_SIZE, WORLD_SIZE);
		WorldGenerator generator = new WorldGenerator(WORLD_SIZE, WORLD_SIZE);
		generator.generateCellularAutomataCaves(caves);
		gameStateManager.setCaves(caves);

		ExploredMap exploredMap = new ExploredMap(WORLD_SIZE, WORLD_SIZE);
		// Mark the player's initial position on the map
		exploredMap.markTile(TileState.CAVE, player.getCoordinates());
		gameStateManager.setExploredMap(exploredMap);
	}
}
