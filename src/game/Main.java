package game;

import game.controllers.state.GameStateManager;
import game.controllers.view.ViewManager;
import game.model.CaveSystem;
import game.model.WorldGenerator;

class Main {
	public static void main(String[] args) {
		ViewManager viewManager = ViewManager.getInstance();
		GameStateManager gameStateManager = GameStateManager.getInstance();

		// initialise game variables
		CaveSystem caves = new CaveSystem(20, 20);
		WorldGenerator generator = new WorldGenerator(20, 20);
		generator.generateCellularAutomataCaves(caves);

		gameStateManager.setCaves(caves);

		// viewManager.switchToMap();
		viewManager.switchToMainMenu();
		// viewManager.switchToDialogue();
		// viewManager.switchToGameplay();
	}
}