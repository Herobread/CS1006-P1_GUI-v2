package game;

import game.controllers.state.dialogue.Dialogue;
import game.controllers.state.dialogue.DialogueQueueManager;

public class DialogueTestMain {
	public static void main(String[] args) {
		DialogueQueueManager dialogueQueueManager = DialogueQueueManager.getInstance();
		dialogueQueueManager.addDialogue("1. Bat Bat Bat Bat Bat", "bat");
		dialogueQueueManager.addDialogue("2. Bat Bat Bat Bat Bat");
		dialogueQueueManager.addDialogue("3. Wumpus");

		while (!dialogueQueueManager.isEmpty()) {
			Dialogue dialogue = dialogueQueueManager.getNextDialogue();

			System.out.println(dialogue.getText() + " texture =" + dialogue.getTexture());
		}

		System.out.println("done");
	}
}
