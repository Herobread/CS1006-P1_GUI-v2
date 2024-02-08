package htw.controllers.game.logic.movement.playerMovement;

import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.CoordinateCalculator;
import htw.model.CaveSystem;
import htw.model.entities.Player;
import htw.utils.Coordinates;
import htw.utils.Direction;

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
