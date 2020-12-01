package todoapp.todo;

import todoapp.exceptions.EmptyFieldException;

/**
 * This class provide an ADT for the Tag object. A Tag object is 
 * characterized by a text and a color. The object is mutable.
 * ADT Invariants: the Tag object must have a non-empty text and a color.
 */
public class Tag {

	/**
	 * All the colors a tag can assume.
	 */
	public enum Color {
		RED, BLUE, YELLOW, GREEN, WHITE, BLACK, GRAY, PURPLE, BROWN, ORANGE
	}

	/**
	 * The object Tag implementation is done with:
	 * - a String variable that represent the text
	 * - a Color variable that represent the color
	 * 
	 * INVARIANT: the text must be not null and not empty. The color must be 
	 * not null
	 */
	private String text;
	private Color color;
	
	/**
	 * Constructor that specify the text for the tag. The color is setted to 
	 * the predefined one (blue).
	 * @param text: the text of the Tag. REQUIRED to be not null and not empty.
	 * @throws NullPointerException when the text is null.
	 * @throws EmptyFieldException when the text is empty.
	 */
	public Tag(String text) throws EmptyFieldException {
		if (text == null) {
			throw new NullPointerException();
		} else if (text.isEmpty()) {
			throw new EmptyFieldException("A Tag must have a text.", "text", "A non-empty string.");
		}

		this.text = text;
		this.color = Color.BLUE;
	}

	/**
	 * Constructor that specify the text for the tag and its color.
	 * @param text: the text of the Tag. REQUIRED to be not null and not empty.
	 * @param color: the color of the tag. REQUIRED to be not null.
	 * @throws NullPointerException when the text or the color is null.
	 * @throws EmptyFieldException when the text is null or empty.
	 */
	public Tag(String text, Color color) throws EmptyFieldException {
		if (text == null || color == null){
			throw new NullPointerException();
		} else if (text.isEmpty()) {
			throw new EmptyFieldException("A Tag must have a text.", "text", "A non-empty string.");
		}

		this.text = text;
		this.color = color;
	}

	/**
	 * Copy constructor that creates a perfect copy of the Tag instance passed.
	 * @param t: the Tag to copy. REQUIRED not null.
	 * @throws NullPointerException when t is null.
	 */
	public Tag(Tag t) {
		if (t == null) {
			throw new NullPointerException();
		}

		this.text = t.text;
		this.color = t.color;
	}

	/**
     * RETURN the text of the tag.
     * @return the text of the tag as String.
     */
	public String getText() {
		return text;
	}

	/**
     * RETURN the color of the tag.
     * @return the color of the tag.
     */
	public Color getColor() {
		return color;
	}

	/**
	 * MODIFY the text of the tag, changing it with a new one.
	 * @param newText: the new text of the tag. REQUIRED to be not null and not 
	 * empty.
	 */
	void changeText(String newText) {

		assert (newText != null && !newText.isEmpty()): "A Tag must have a text.";

		text = newText;
	}

	/**
	 * MODIFY the color of the tag, changing it with a new one.
	 * @param newColor: the new color of the tag. REQUIRED to be not null.
	 */
	void changeColor(Color newColor) {

		assert (newColor != null): "A Tag must have a color.";

		color = newColor;
	}

	/**
	 * Implement a comparison bethween two instances of Tag.
	 * @param obj: the object to compare with.
	 * @return true if obj and this have same text and color, false 
	 * otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != Tag.class) {
			return false;
		}

		Tag t = (Tag)obj;
		if (this.text.length() != t.text.length()) {
			return false;
		}

		if (this.text.compareTo(t.text) != 0) {
			return false;
		} 

		if (this.color != t.color) {
			return false;
		}

		return true;
	}

}	// class Tag
