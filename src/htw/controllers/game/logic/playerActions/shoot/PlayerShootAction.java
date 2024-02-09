package htw.controllers.game.logic.playerActions.shoot;

import java.util.ArrayList;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.CoordinateCalculator;
import htw.controllers.game.logic.movement.playerMovement.PlayerMoveLogic;
import htw.model.caves.Cave;
import htw.model.caves.CaveSystem;
import htw.model.entities.Entity;
import htw.model.entities.Player;
import htw.utils.Coordinates;
import htw.utils.Direction;

public class PlayerShootAction {
	public static void shoot(Direction direction) {
		DialogueManager dialogueManager = DialogueManager.getInstance();
		GameStateManager gameStateManager = GameStateManager.getInstance();
		Player player = gameStateManager.getPlayer();

		if (player.getArrows() < 1) {
			dialogueManager.addDialogue("Not enough arrows", "arrow");
			return;
		}

		player.useArrow();

		Coordinates target = CoordinateCalculator.calculateTargetCoordinates(player.getCoordinates(), direction);

		dialogueManager.addDialogue("Arrow flies into the cave next to you.", "arrow");

		Entity shotEntity = shoot(target);

		int arrowsLeft = player.getArrows();

		if (shotEntity == null) {
			dialogueManager.addDialogue("Miss.", "broken-arrow");
		}

		dialogueManager.addDialogue(arrowsLeft + " arrows left.", "arrow");
	}

	public static Entity shoot(Coordinates targetCoordinates) {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		CaveSystem caves = gameStateManager.getCaves();
		Player player = gameStateManager.getPlayer();
		ArrayList<Entity> targets = caves.getCaveEntities(targetCoordinates);

		if (targets == null) {
			return null;
		}

		// finds first shootable target and stops
		for (Entity target : targets) {
			if (target.isShootable()) {
				target.onShot();

				// remove entity
				Cave cave = caves.getCave(targetCoordinates);
				cave.removeEntity(target);

				// recheck tile on map
				PlayerMoveLogic.markTileOnMap(caves, player.getCoordinates());

				return target;
			}
		}

		return null;
	}
}
