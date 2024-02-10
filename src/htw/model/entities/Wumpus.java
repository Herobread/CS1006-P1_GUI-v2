package htw.model.entities;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.GameStatus;

public class Wumpus extends Entity {
	public Wumpus() {
		super("wumpus", "wumpus-teeth");
		setHazard(true);
		setShootable(true);
	}

	@Override
	public void interact() {
		DialogueManager dialogueManager = DialogueManager.getInstance();
		GameStateManager gameStateManager = GameStateManager.getInstance();

		gameStateManager.setGameStatus(GameStatus.FAIL);

		dialogueManager.addDialogue("As you cautiously step into the cave...", "player");
		dialogueManager.addDialogue("...a sense of impending doom washes over you...", "player");
		dialogueManager.addDialogue("The Wumpus lunges, devouring you whole.", textureName);
	}

	@Override
	public void onShot() {
		DialogueManager dialogueManager = DialogueManager.getInstance();
		dialogueManager.addDialogue("Wumpus eliminated!", textureName);
	}
}
