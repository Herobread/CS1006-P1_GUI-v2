package htw.controllers.game.logic.initialization;

import htw.controllers.game.GameStateManager;
import htw.model.caves.CaveSystem;
import htw.model.caves.WorldGenerator;
import htw.model.entities.Player;
import htw.model.map.ExploredMap;
import htw.model.map.ExploredMap.TileState;

public class GameInitializer {
	public static void initialize() {
		GameStateManager gameStateManager = GameStateManager.getInstance();

		// initialise game variables
		// create player instance
		Player player = new Player(0, 0);
		gameStateManager.setPlayer(player);

		final int WORLD_SIZE = 16;

		// create caves
		CaveSystem caves = new CaveSystem(WORLD_SIZE, WORLD_SIZE);
		WorldGenerator generator = new WorldGenerator(WORLD_SIZE, WORLD_SIZE);
		generator.generateCellularAutomataCaves(caves);
		gameStateManager.setCaves(caves);

		// map
		ExploredMap exploredMap = new ExploredMap(WORLD_SIZE, WORLD_SIZE);
		exploredMap.markTile(TileState.CAVE, player.getCoordinates());
		gameStateManager.setExploredMap(exploredMap);
	}
}
