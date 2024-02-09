package htw.controllers.game.logic.playerActions.shoot;

import java.util.ArrayList;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.CoordinateCalculator;
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
		CaveSystem caves = gameStateManager.getCaves();

		if (player.getArrows() < 1) {
			dialogueManager.addDialogue("Not enough arrows", "arrow");
			return;
		}

		player.useArrow();

		Coordinates target = CoordinateCalculator.calculateTargetCoordinates(player.getCoordinates(), direction);

		dialogueManager.addDialogue("Arrow flies into the cave next to you.", "arrow");

		Entity shotEntity = shoot(caves, target);

		int arrowsLeft = player.getArrows();

		if (shotEntity == null) {
			dialogueManager.addDialogue("Miss.", "broken-arrow");
		}

		dialogueManager.addDialogue(arrowsLeft + " arrows left.", "arrow");
	}

	public static Entity shoot(CaveSystem caves, Coordinates targetCoordinates) {
		ArrayList<Entity> targets = caves.getCaveEntities(targetCoordinates);

		if (targets == null) {
			return null;
		}

		// finds first shootable target and stops
		for (Entity target : targets) {
			if (target.isShootable()) {
				target.onShot();

				return target;
			}
		}

		return null;
	}
}
