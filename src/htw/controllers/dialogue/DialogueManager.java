package htw.controllers.dialogue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The DialogueManager class manages a queue of dialogues to be displayed in the
 * game.
 */
public class DialogueManager {
	private static DialogueManager instance;
	private Queue<Dialogue> dialogueQueue;

	// Private constructor to prevent instantiation from outside
	private DialogueManager() {
		dialogueQueue = new LinkedList<>();
	}

	/**
	 * Returns the singleton instance of the DialogueManager.
	 * 
	 * @return The singleton instance of the DialogueManager.
	 */
	public static DialogueManager getInstance() {
		if (instance == null) {
			instance = new DialogueManager();
		}
		return instance;
	}

	/**
	 * Adds a dialogue with the specified text to the dialogue queue.
	 * 
	 * @param text The text of the dialogue.
	 */
	public void addDialogue(String text) {
		addDialogue(text, null);
	}

	/**
	 * Adds a dialogue with the specified text and texture to the dialogue queue.
	 * 
	 * @param text    The text of the dialogue.
	 * @param texture The texture associated with the dialogue.
	 */
	public void addDialogue(String text, String texture) {
		dialogueQueue.add(new Dialogue(text, texture));
	}

	/**
	 * Checks if the dialogue queue is empty.
	 * 
	 * @return true if the dialogue queue is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return dialogueQueue.isEmpty();
	}

	/**
	 * Retrieves and removes the next dialogue from the queue.
	 * 
	 * @return The next dialogue in the queue.
	 */
	public Dialogue getNextDialogue() {
		return dialogueQueue.poll();
	}
}
