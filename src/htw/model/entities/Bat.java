package htw.model.entities;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.CoordinateCalculator;
import htw.controllers.game.logic.movement.RandomPlace;
import htw.controllers.game.logic.movement.playerMovement.PlayerMoveLogic;
import htw.controllers.game.logic.playerActions.interact.InteractWithEntity;
import htw.model.caves.CaveSystem;
import htw.utils.Coordinates;

public class Bat extends Entity {
	public Bat() {
		super("bat", "bat");
		setHazard(true);
		setShootable(true);
		setRemoveOnShot(true);
	}

	@Override
	public void interact() {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		CaveSystem caves = gameStateManager.getCaves();
		Player player = gameStateManager.getPlayer();

		DialogueManager dialogueManager = DialogueManager.getInstance();

		Coordinates playerCoordinates = player.getCoordinates();
		Coordinates randomCoordinates = RandomPlace.find(caves);
		String distanceExplanation = CoordinateCalculator.calculateDistanceString(playerCoordinates, randomCoordinates);
		String batDialogue = "The bat picked you up and flies "
				+ distanceExplanation + "!";

		dialogueManager.addDialogue(batDialogue, textureName);

		PlayerMoveLogic.handleMove(randomCoordinates);
		InteractWithEntity.interact(caves, player.getCoordinates());
	}

	@Override
	public void onShot() {
		DialogueManager dialogueManager = DialogueManager.getInstance();

		dialogueManager.addDialogue("You killed a bat.", "bat");
	}
}
