package htw.model.entities;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.RandomPlace;
import htw.controllers.game.logic.movement.playerMovement.PlayerMoveLogic;
import htw.controllers.game.logic.playerActions.InteractWithEntity;
import htw.model.caves.CaveSystem;
import htw.utils.Coordinates;

public class Bat extends Entity {
	public Bat() {
		super("bat", "bat", true);
	}

	@Override
	public void interact() {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		CaveSystem caves = gameStateManager.getCaves();
		Player player = gameStateManager.getPlayer();

		DialogueManager dialogueManager = DialogueManager.getInstance();

		Coordinates randomPlace = RandomPlace.find(caves);

		dialogueManager.addDialogue("The bat pick you up and flies to some cave!", textureName);

		PlayerMoveLogic.handleMove(randomPlace);

		System.out.println(player.hashCode());
		InteractWithEntity.interact(caves, player.getCoordinates());
	}
}
