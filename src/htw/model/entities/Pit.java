package htw.model.entities;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.GameStatus;

/**
 * The Pit class represents a hazard in the game environment.
 * It is a type of Entity that can be interacted with.
 */
public class Pit extends Entity {

	/**
	 * Constructs a new Pit object.
	 */
	public Pit() {
		super("pit", "pit");
		setShootable(true);
		setHazard(true);
		setRemoveOnShot(false);
	}

	/**
	 * Performs an interaction with the pit.
	 * Triggers dialogue and sets game status to fail.
	 */
	@Override
	public void interact() {
		DialogueManager dialogueManager = DialogueManager.getInstance();
		GameStateManager gameStateManager = GameStateManager.getInstance();

		dialogueManager.addDialogue("As you venture deeper into the cave...", "player");
		dialogueManager.addDialogue("You stumble upon a hidden pit!", "pit");
		dialogueManager.addDialogue("A moment of panic sets in as you.", "pit");
		dialogueManager.addDialogue("You've fallen into the pit!", "pit");

		gameStateManager.setGameStatus(GameStatus.FAIL);
	}

	/**
	 * Handles the action when the pit is shot.
	 * Triggers dialogue indicating the arrow's silent descent.
	 */
	@Override
	public void onShot() {
		DialogueManager dialogueManager = DialogueManager.getInstance();

		dialogueManager.addDialogue("The arrow disappears into the darkness without a sound.", "pit");
		dialogueManager.addDialogue("You couldn't hear the arrow fall.", "player");
	}
}
