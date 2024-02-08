package game.view.pages.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.controllers.dialogue.DialogueManager;
import game.controllers.game.GameStateManager;
import game.controllers.game.logic.movement.playerMovement.PlayerMoveLogic;
import game.controllers.view.ViewBase;
import game.controllers.view.ViewManager;
import game.model.CaveSystem;
import game.model.entities.Player;
import game.utils.Coordinates;
import game.utils.Direction;
import game.view.Renderer;

public class GameView extends ViewBase {
	private game.controllers.game.GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();
	private DialogueManager dialogueQueueManager = DialogueManager.getInstance();

	public GameView() {
		super("Gameplay");
	}

	private ActionListener dialogueButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			// add placeholder dialogues
			dialogueQueueManager.addDialogue("1. Bat Bat Bat Bat Bat", "bat");
			dialogueQueueManager.addDialogue("2. Bat Bat Bat Bat Bat", "bat");
			dialogueQueueManager.addDialogue("3. Wumpus eats you", "wumpus");
			viewManager.switchToDialogue();
		}
	};

	private ActionListener mapButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Map button pressed");
			viewManager.switchToMap();
		}
	};

	private ActionListener shootUpButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Shoot up button pressed");
		}
	};

	private ActionListener walkUpButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic playerMoveLogic = new PlayerMoveLogic();

			playerMoveLogic.handleMove(Direction.UP);

			renderView();
		}
	};

	private ActionListener shootDownButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Shoot down button pressed");
		}
	};

	private ActionListener walkDownButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic playerMoveLogic = new PlayerMoveLogic();

			playerMoveLogic.handleMove(Direction.DOWN);

			renderView();
		}
	};

	private ActionListener shootLeftButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Shoot left button pressed");
		}
	};

	private ActionListener walkLeftButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic playerMoveLogic = new PlayerMoveLogic();

			playerMoveLogic.handleMove(Direction.LEFT);

			renderView();
		}
	};

	private ActionListener shootRightButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Shoot Right button pressed");
		}
	};

	private ActionListener walkRightButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PlayerMoveLogic playerMoveLogic = new PlayerMoveLogic();

			playerMoveLogic.handleMove(Direction.RIGHT);

			renderView();
		}
	};

	@Override
	public void renderView() {
		System.out.println("[Game view] Rendering GameView");

		renderer.clear();

		CaveSystem caves = gameStateManager.getCaves();

		Player player = gameStateManager.getPlayer();
		Coordinates playerCoordinates = player.getCoordinates();
		String currentCaveTexture = "cave-" + caves.getConnectionsString(playerCoordinates);

		renderer.drawTexture(currentCaveTexture, 0, 0, 512, 512);

		// buttons up:
		renderer.drawTexture("shoot", 176, 8, 72, 72);
		renderer.drawClickAreaUnstable(176, 8, 72, 72, shootUpButtonActionListener);

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
		renderer.drawTexture("dialogue-bs", 208, 144, 144, 64);

		// map button
		renderer.drawTexture("map", 8, 8, 72, 72);
		renderer.drawClickAreaUnstable(8, 8, 72, 72, mapButtonActionListener);

		// main
		renderer.drawTexture("shadow", 176, 273, 128, 64);
		renderer.drawTexture("player", 192, 192, 128, 128);
		renderer.drawClickAreaUnstable(192, 192, 128, 128, dialogueButtonActionListener);

		renderer.draw();
	}

	@Override
	public void update() {
		// Provide the implementation for updating the game model in the view
		System.out.println("[Game view] Updating model in GameView");
	}

	@Override
	public void run() {
		renderView();
		update();
	}
}
