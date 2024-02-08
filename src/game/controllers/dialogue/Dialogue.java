package game.controllers.dialogue;

public class Dialogue {
	private String text;
	private String texture;

	public Dialogue(String text, String texture) {
		this.text = text;
		this.texture = texture;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}
}
