package htw.model.entities;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.GameStatus;

public class Wumpus extends Entity {
	public Wumpus() {
		super("wumpus", "wumpus-teeth");
		setHazard(true);
		setShootable(true);
		setRemoveOnShot(true);
	}

	@Override
	public void interact() {
		DialogueManager dialogueManager = DialogueManager.getInstance();
		GameStateManager gameStateManager = GameStateManager.getInstance();

		gameStateManager.setGameStatus(GameStatus.FAIL);

		dialogueManager.addDialogue("As you step into the cave...", "player");
		dialogueManager.addDialogue("a feeling of doom hits you...", "player");
		dialogueManager.addDialogue("The Wumpus lunges", textureName);
		dialogueManager.addDialogue("devouring you whole.", textureName);
	}

	@Override
	public void onShot() {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		Player player = gameStateManager.getPlayer();
		player.addWumpusShot(1);

		DialogueManager dialogueManager = DialogueManager.getInstance();
		dialogueManager.addDialogue("Wumpus eliminated!", textureName);

	}
}
