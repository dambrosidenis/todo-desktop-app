package todoapp.todo;

import lombok.EqualsAndHashCode;

/**
 * This class provide an ADT for a Tag. A Tag object is a simple Attribute 
 * characterized by a text and a color. The object is mutable.
 */
@EqualsAndHashCode
public class Tag implements Attribute {

	/**
	 * The object Tag implementation is done with:
	 * - a String variable that represent the text
	 * - a Color variable that represent the color
	 * 
	 * INVARIANT: the text must be not null and not empty. The color must be 
	 * not null.
	 */
	private String text;
	private Color color;
	
	private static final String TEXT_EXCEPTION = "A Tag must have a text.";
	
	/**
	 * Constructor that specify the text for the tag. The color is setted to 
	 * the predefined one (blue).
	 * @param text: the text of the Tag. Required to be not null and not empty.
	 * @throws NullPointerException when the text is null.
	 * @throws EmptyFieldException when the text is empty.
	 */
	public Tag(String text) throws EmptyFieldException {
		if (text == null) {
			throw new NullPointerException();
		} else if (text.isEmpty()) {
			throw new EmptyFieldException(TEXT_EXCEPTION, "text", "A non-empty string.");
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
			throw new EmptyFieldException(TEXT_EXCEPTION, "text", "A non-empty string.");
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
	 * @return the text that is contained inside this tag.
	 */
	@Override
	public String getText() {
		return this.text;
	}

	/**
	 * @return the color associated with this tag.
	 */
	@Override
	public Color getColor() {
		return this.color;
	}

	/**
	 * Modify the text of the tag, changing it with a new one.
	 * @param newText: the new text of the tag. Required to be not null and not empty.
	 * @throws NullPointerException if newText is null.
	 * @throws EmptyFieldException if newText is an empty string.
	 */
	public void changeText(String newText) throws EmptyFieldException {

		if (newText == null) {
			throw new NullPointerException();
		} else if (newText.isEmpty()) {
			throw new EmptyFieldException(TEXT_EXCEPTION, "newText", "A non-empty string.");
		}

		this.text = newText;
	}

	/**
	 * Change the color associated with this tag with a new one.
	 * @param newColor: the new color for the tag. Required to be not null.
	 * @throws NullPointerException if newColor is null.
	 */
	@Override
	public void changeColor(Color newColor) {

		if (newColor == null) {
			throw new NullPointerException();
		}

		this.color = newColor;
	}

}	// class Tag
