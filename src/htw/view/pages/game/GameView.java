package htw.view.pages.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import htw.controllers.dialogue.DialogueManager;
import htw.controllers.game.GameStateManager;
import htw.controllers.game.logic.movement.playerMovement.PlayerMoveLogic;
import htw.controllers.game.logic.playerActions.Senses;
import htw.controllers.view.ViewBase;
import htw.controllers.view.ViewManager;
import htw.model.caves.CaveSystem;
import htw.model.caves.Decoration;
import htw.model.entities.Player;
import htw.utils.Coordinates;
import htw.utils.Direction;
import htw.view.Renderer;

public class GameView extends ViewBase {
	private htw.controllers.game.GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();
	private DialogueManager dialogueManager = DialogueManager.getInstance();

	public GameView() {
		super("Gameplay");
	}

	private ActionListener dialogueButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			// add placeholder dialogues
			dialogueManager.addDialogue("1. Bat Bat Bat Bat Bat", "bat");
			dialogueManager.addDialogue("2. Bat Bat Bat Bat Bat", "bat");
			dialogueManager.addDialogue("3. Wumpus eats you", "wumpus");
			viewManager.switchToDialogue();
		}
	};

	private ActionListener mapButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			viewManager.switchToMap();
		}
	};

	private ActionListener shootUpButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
		}
	};

	private ActionListener walkUpButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic.handleMove(Direction.UP);

			update();
		}
	};

	private ActionListener shootDownButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
		}
	};

	private ActionListener walkDownButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic.handleMove(Direction.DOWN);

			update();
		}
	};

	private ActionListener shootLeftButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
		}
	};

	private ActionListener walkLeftButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic.handleMove(Direction.LEFT);

			update();
		}
	};

	private ActionListener shootRightButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
		}
	};

	private ActionListener walkRightButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic.handleMove(Direction.RIGHT);

			update();
		}
	};

	@Override
	public void renderView() {

		renderer.clear();

		CaveSystem caves = gameStateManager.getCaves();
		Player player = gameStateManager.getPlayer();
		Coordinates playerCoordinates = player.getCoordinates();
		String currentCaveTexture = "cave-" + caves.getConnectionsString(playerCoordinates);
		String senses = Senses.checkNeighbours(caves, playerCoordinates);

		System.out.println("[game main] " + playerCoordinates);
		System.out.println(player.hashCode());

		renderer.drawTexture(currentCaveTexture, 0, 0, 512, 512);

		// player shadow
		renderer.drawTexture("shadow", 176, 273, 128, 64, 0.5f);

		ArrayList<Decoration> decorations = caves.getCave(playerCoordinates).getDecorations();

		for (Decoration decoration : decorations) {
			renderer.drawTexture(decoration.getTextureName(), decoration.getX(), decoration.getY(),
					decoration.getWidth(), decoration.getHeight());
		}

		// buttons up:
		renderer.drawTexture("shoot", 176, 8, 72, 72);
		renderer.drawClickAreaUnstable(176, 8, 72, 72,
				shootUpButtonActionListener);

		renderer.drawTexture("walk-up", 256, 8, 72, 72);
		renderer.drawClickAreaUnstable(256, 8, 72, 72, walkUpButtonActionListener);

		// buttons down:
		renderer.drawTexture("shoot", 176, 432, 72, 72);
		renderer.drawClickAreaUnstable(176, 432, 72, 72, shootDownButtonActionListener);

		renderer.drawTexture("walk-down", 256, 432, 72, 72);
		renderer.drawClickAreaUnstable(256, 432, 72, 72, walkDownButtonActionListener);

		// buttons left:
		renderer.drawTexture("shoot", 8, 176, 72, 72);
		renderer.drawClickAreaUnstable(8, 176, 72, 72, shootLeftButtonActionListener);

		renderer.drawTexture("walk-left", 8, 256, 72, 72);
		renderer.drawClickAreaUnstable(8, 256, 72, 72, walkLeftButtonActionListener);

		// buttons right:
		renderer.drawTexture("shoot", 432, 176, 72, 72);
		renderer.drawClickAreaUnstable(432, 176, 72, 72, shootRightButtonActionListener);

		renderer.drawTexture("walk-right", 432, 256, 72, 72);
		renderer.drawClickAreaUnstable(432, 256, 72, 72, walkRightButtonActionListener);

		// info:
		if (senses.length() != 0) {
			String hazardsDialogueTexture = "dialogue-" + senses;
			renderer.drawTexture(hazardsDialogueTexture, 208, 144, 144, 64);
		}

		// map button
		renderer.drawTexture("map", 8, 8, 72, 72);
		renderer.drawClickAreaUnstable(8, 8, 72, 72, mapButtonActionListener);

		renderer.drawTexture("player", 192, 192, 128, 128);
		renderer.drawClickAreaUnstable(192, 192, 128, 128, dialogueButtonActionListener);

		renderer.draw();
	}

	@Override
	public void update() {
		if (!dialogueManager.isEmpty()) {

			viewManager.switchToDialogue();
			return;
		}

		renderView();

		// Provide the implementation for updating the game model in the view
	}

	@Override
	public void run() {
		renderView();
		update();
	}
}
