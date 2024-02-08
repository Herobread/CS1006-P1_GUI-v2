package htw.view.pages.dialogue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import htw.controllers.dialogue.Dialogue;
import htw.controllers.dialogue.DialogueManager;
import htw.controllers.view.ViewBase;
import htw.controllers.view.ViewManager;
import htw.view.Renderer;

public class DialogueView extends ViewBase {
	// private GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();
	private DialogueManager dialogueManager = DialogueManager.getInstance();

	public DialogueView() {
		super("Dialogue");
	}

	// button action listeneres
	private ActionListener nextButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			if (dialogueManager.isEmpty()) {
				viewManager.switchToGameplay();
				return;
			}

			renderView();
		}
	};

	// rendering
	@Override
	public void renderView() {
		renderer.clear();

		Dialogue dialogue = dialogueManager.getNextDialogue();

		String text = dialogue.getText();
		String texture = dialogue.getTexture();

		if (texture != null) {
			renderer.drawTexture(texture, 192, 175, 128, 128);
		}
		renderer.drawText(text, 113, 317, 12, Color.WHITE);

		renderer.drawTexture("next", 432, 432, 72, 72);
		renderer.drawClickArea(432, 432, 72, 72, nextButtonActionListener);

		renderer.draw();
	}

	// logic
	@Override
	public void update() {
	}

	@Override
	public void run() {
		update();
		renderView();
	}
}
