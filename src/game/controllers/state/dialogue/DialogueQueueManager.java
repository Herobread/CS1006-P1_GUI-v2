package game.controllers.state.dialogue;

import java.util.LinkedList;
import java.util.Queue;

public class DialogueQueueManager {
	private static DialogueQueueManager instance;
	private Queue<Dialogue> dialogueQueue;

	// Private constructor to prevent instantiation from outside
	private DialogueQueueManager() {
		dialogueQueue = new LinkedList<>();
	}

	// Method to get the singleton instance
	public static DialogueQueueManager getInstance() {
		if (instance == null) {
			instance = new DialogueQueueManager();
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
