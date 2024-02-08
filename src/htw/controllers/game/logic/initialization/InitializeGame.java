package htw.controllers.game.logic.initialization;

import htw.controllers.game.GameStateManager;
import htw.model.CaveSystem;
import htw.model.WorldGenerator;
import htw.model.entities.Player;

public class InitializeGame {
	public static void initialize() {
		GameStateManager gameStateManager = GameStateManager.getInstance();

		// initialise game variables
		// create player instance
		Player player = new Player(0, 0);
		gameStateManager.setPlayer(player);

		// create caves
		CaveSystem caves = new CaveSystem(20, 20);
		WorldGenerator generator = new WorldGenerator(20, 20);
		generator.generateCellularAutomataCaves(caves);

		gameStateManager.setCaves(caves);
	}
}
