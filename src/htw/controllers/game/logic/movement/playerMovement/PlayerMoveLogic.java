package htw.controllers.game.logic.movement.playerMovement;

import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.CoordinateCalculator;
import htw.controllers.game.logic.playerActions.InteractWithEntity;
import htw.model.caves.CaveSystem;
import htw.model.entities.Player;
import htw.model.map.ExploredMap;
import htw.model.map.ExploredMap.TileState;
import htw.utils.Coordinates;
import htw.utils.Direction;

public class PlayerMoveLogic {
	private GameStateManager gameStateManager = GameStateManager.getInstance();

	public void handleMove(Direction direction) {
		// get target coordinates
		Player player = gameStateManager.getPlayer();
		ExploredMap exploredMap = gameStateManager.getExploredMap();
		Coordinates currentCoordinates = player.getCoordinates();
		Coordinates targetCoordinates = CoordinateCalculator.calculateTargetCoordinates(currentCoordinates, direction);

		// check if move is valid
		CaveSystem caves = gameStateManager.getCaves();

		if (caves.isSolid(targetCoordinates)) {
			return;
		}

		// TODO: check tile content
		InteractWithEntity.interact(caves, targetCoordinates);

		// all good, set new coordinates
		player.setCoordinates(targetCoordinates);
		exploredMap.markTile(TileState.CAVE, targetCoordinates);
	}
}
