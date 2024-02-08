package game.controllers.game.logic.initialization;

import game.controllers.game.GameStateManager;
import game.model.CaveSystem;
import game.model.WorldGenerator;
import game.model.entities.Player;

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
