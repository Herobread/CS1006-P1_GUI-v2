package game.controllers.dialogue;

import java.util.LinkedList;
import java.util.Queue;

public class DialogueManager {
	private static DialogueManager instance;
	private Queue<Dialogue> dialogueQueue;

	// Private constructor to prevent instantiation from outside
	private DialogueManager() {
		dialogueQueue = new LinkedList<>();
	}

	// Method to get the singleton instance
	public static DialogueManager getInstance() {
		if (instance == null) {
			instance = new DialogueManager();
		}
		return instance;
	}

	public void addDialogue(String text) {
		addDialogue(text, null);
	}

	public void addDialogue(String text, String texture) {
		dialogueQueue.add(new Dialogue(text, texture));
	}

	public boolean isEmpty() {
		return dialogueQueue.size() == 0;
	}

	public Dialogue getNextDialogue() {
		return dialogueQueue.poll();
	}
}
