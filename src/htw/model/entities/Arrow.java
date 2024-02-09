package htw.model.entities;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.model.caves.Cave;
import htw.model.caves.CaveSystem;
import htw.utils.Coordinates;

public class Arrow extends Entity {
	public Arrow() {
		super("arrow", "arrow");
		setHazard(false);
		setShootable(false);
	}

	@Override
	public void interact() {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		DialogueManager dialogueManager = DialogueManager.getInstance();

		CaveSystem caveSystem = gameStateManager.getCaves();
		Player player = gameStateManager.getPlayer();

		Coordinates currentCoordinates = player.getCoordinates();
		Cave cave = caveSystem.getCave(currentCoordinates);

		player.setArrows(player.getArrows() + 1);

		dialogueManager.addDialogue("You found an arrow!", textureName);
		dialogueManager.addDialogue("You have " + player.getArrows() + " arrows.", textureName);

		cave.removeEntity(this);
		// this = null;
	}
}
