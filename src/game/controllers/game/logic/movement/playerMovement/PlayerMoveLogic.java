package game.controllers.game.logic.movement.playerMovement;

import game.controllers.game.GameStateManager;
import game.controllers.game.logic.movement.CoordinateCalculator;
import game.model.CaveSystem;
import game.model.entities.Player;
import game.utils.Coordinates;
import game.utils.Direction;

public class PlayerMoveLogic {
	private GameStateManager gameStateManager = GameStateManager.getInstance();

	public void handleMove(Direction direction) {
		// get target coordinates
		Player player = gameStateManager.getPlayer();
		Coordinates currentCoordinates = player.getCoordinates();
		Coordinates targetCoordinates = CoordinateCalculator.calculateTargetCoordinates(currentCoordinates, direction);

		// check if move is valid
		CaveSystem caves = gameStateManager.getCaves();

		if (caves.isSolid(targetCoordinates)) {
			return;
		}

		// TODO: check tile content

		// all good, set new coordinates
		player.setCoordinates(targetCoordinates);
	}
}
