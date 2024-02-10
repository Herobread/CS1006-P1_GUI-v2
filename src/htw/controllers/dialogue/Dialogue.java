package htw.controllers.dialogue;

/**
 * The Dialogue class represents a piece of dialogue in the game, consisting of
 * text and an optional texture.
 */
public class Dialogue {
	private String text;
	private String texture;

	/**
	 * Constructs a new Dialogue object with the specified text and texture.
	 * 
	 * @param text    The text of the dialogue.
	 * @param texture The texture associated with the dialogue.
	 */
	public Dialogue(String text, String texture) {
		this.text = text;
		this.texture = texture;
	}

	/**
	 * Retrieves the text of the dialogue.
	 * 
	 * @return The text of the dialogue.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text of the dialogue.
	 * 
	 * @param text The text of the dialogue to set.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Retrieves the texture associated with the dialogue.
	 * 
	 * @return The texture associated with the dialogue.
	 */
	public String getTexture() {
		return texture;
	}

	/**
	 * Sets the texture associated with the dialogue.
	 * 
	 * @param texture The texture to set for the dialogue.
	 */
	public void setTexture(String texture) {
		this.texture = texture;
	}
}
