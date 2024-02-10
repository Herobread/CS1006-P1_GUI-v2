package htw.controllers.game.logic.movement.playerMovement;

import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.CoordinateCalculator;
import htw.controllers.game.logic.playerActions.Senses;
import htw.controllers.game.logic.playerActions.interact.InteractWithEntity;
import htw.model.caves.CaveSystem;
import htw.model.entities.Player;
import htw.model.map.ExploredMap;
import htw.model.map.ExploredMap.TileState;
import htw.utils.Coordinates;
import htw.utils.Direction;

/**
 * Provides methods for handling player movement logic.
 */
public class PlayerMoveLogic {

	/**
	 * Handles the player's movement in the specified direction.
	 *
	 * @param direction The direction in which the player wants to move.
	 */
	public static void handleMove(Direction direction) {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		Player player = gameStateManager.getPlayer();
		Coordinates currentCoordinates = player.getCoordinates();
		Coordinates targetCoordinates = CoordinateCalculator.calculateTargetCoordinates(currentCoordinates, direction);

		handleMove(targetCoordinates);
	}

	/**
	 * Handles the player's movement to the specified target coordinates.
	 *
	 * @param targetCoordinates The target coordinates to move the player to.
	 */
	public static void handleMove(Coordinates targetCoordinates) {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		Player player = gameStateManager.getPlayer();
		CaveSystem caves = gameStateManager.getCaves();

		if (!isValidMove(caves, targetCoordinates)) {
			return; // Cancel move
		}

		markTileOnMap(caves, targetCoordinates);

		player.setCoordinates(targetCoordinates);
		InteractWithEntity.interact(caves, targetCoordinates);
	}

	/**
	 * Checks if the move to the specified target coordinates is valid.
	 *
	 * @param caves             The cave system.
	 * @param targetCoordinates The target coordinates to move to.
	 * @return True if the move is valid, false otherwise.
	 */
	private static boolean isValidMove(CaveSystem caves, Coordinates targetCoordinates) {
		return !caves.isSolid(targetCoordinates);
	}

	/**
	 * Marks the tile on the explored map based on the player's new position.
	 *
	 * @param caves             The cave system.
	 * @param targetCoordinates The target coordinates where the player is moving
	 *                          to.
	 */
	public static void markTileOnMap(CaveSystem caves, Coordinates targetCoordinates) {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		ExploredMap exploredMap = gameStateManager.getExploredMap();
		String senses = Senses.checkNeighbours(caves, targetCoordinates);

		if (senses.length() > 0) {
			exploredMap.markTile(TileState.HAZARD, targetCoordinates);
		} else {
			exploredMap.markTile(TileState.CAVE, targetCoordinates);
		}
	}
}
