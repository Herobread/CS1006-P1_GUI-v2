package htw.model.entities;

import htw.controllers.dialogue.DialogueManager;

public class Bat extends Entity {
	public Bat() {
		super("bat", "bat", true);
	}

	@Override
	public void interact() {
		DialogueManager dialogueManager = DialogueManager.getInstance();
		dialogueManager.addDialogue("Baaaaaaat dialogue", textureName);
	}
}
