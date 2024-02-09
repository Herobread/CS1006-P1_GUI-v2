package htw.controllers.game.logic.movement.playerMovement;

import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.CoordinateCalculator;
import htw.controllers.game.logic.playerActions.InteractWithEntity;
import htw.controllers.game.logic.playerActions.Senses;
import htw.model.caves.CaveSystem;
import htw.model.entities.Player;
import htw.model.map.ExploredMap;
import htw.model.map.ExploredMap.TileState;
import htw.utils.Coordinates;
import htw.utils.Direction;

public class PlayerMoveLogic {

	public static void handleMove(Direction direction) {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		Player player = gameStateManager.getPlayer();
		Coordinates currentCoordinates = player.getCoordinates();
		Coordinates targetCoordinates = CoordinateCalculator.calculateTargetCoordinates(currentCoordinates, direction);

		handleMove(targetCoordinates);
	}

	public static void handleMove(Coordinates targetCoordinates) {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		Player player = gameStateManager.getPlayer();
		ExploredMap exploredMap = gameStateManager.getExploredMap();
		CaveSystem caves = gameStateManager.getCaves();

		// check if move is valid
		if (caves.isSolid(targetCoordinates)) {
			return; // cancel move
		}

		String senses = Senses.checkNeighbours(caves, targetCoordinates);

		if (senses.length() > 0) {
			exploredMap.markTile(TileState.HAZARD, targetCoordinates);
		} else {
			exploredMap.markTile(TileState.CAVE, targetCoordinates);
		}

		// all good, set new coordinates
		player.setCoordinates(targetCoordinates);

		InteractWithEntity.interact(caves, targetCoordinates);
	}
}
