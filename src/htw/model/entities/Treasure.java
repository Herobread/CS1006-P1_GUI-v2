package htw.model.entities;

import java.util.Random;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.model.caves.Cave;
import htw.model.caves.CaveSystem;
import htw.utils.Coordinates;

public class Treasure extends Entity {
	private static final Random RANDOM = new Random();
	private int reward = 0;

	public Treasure() {
		super("treasure", "treasure");
		reward = generateRandomReward();
		setHazard(false);
		setShootable(false);
	}

	private int generateRandomReward() {
		// Generate a random reward between 1 and 100 (inclusive)
		return RANDOM.nextInt(50) + 50;
	}

	@Override
	public void interact() {
		GameStateManager gameStateManager = GameStateManager.getInstance();
		CaveSystem caves = gameStateManager.getCaves();
		Player player = gameStateManager.getPlayer();
		DialogueManager dialogueManager = DialogueManager.getInstance();
		Coordinates currentCoordinates = player.getCoordinates();

		player.addGold(reward);

		dialogueManager.addDialogue("You found a treasure!", "treasure");
		dialogueManager.addDialogue("There was " + reward + " golden coins!", "treasure");
		dialogueManager.addDialogue("You now have " + player.getGold() + " golden coins!", "player");

		Cave cave = caves.getCave(currentCoordinates);

		cave.removeEntity(this);
	}
}
