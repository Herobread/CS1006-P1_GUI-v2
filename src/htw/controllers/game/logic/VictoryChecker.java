package htw.controllers.game.logic;

import htw.controllers.game.GameStateManager;
import htw.controllers.game.GameStatus;
import htw.model.entities.Player;

public class VictoryChecker {
	public static void checkVictory() {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		Player player = gameStateManager.getPlayer();

		int wumpusesKilled = player.getWumpusesShot();
		int totalWumpuses = gameStateManager.getTotalAmountOfWumpuses();

		if (totalWumpuses == wumpusesKilled) {
			gameStateManager.setGameStatus(GameStatus.WIN);
		}
	}
}
