package todoapp.todo;

/**
 * This interface provide an ADT for an Attribute. An Attribute is something 
 * that marks the object on which it is applied. An object can have multiple 
 * attributes. It is characterized by some text to distinguish it from others, 
 * and by a color.
 * 
 * Invariant:
 * - the text can't be null or an empty string
 * - it must have a color
 */
public interface Attribute {

	/**
	 * Those are the colors that an attribute can assume.
	 */
	public static enum Color {
		RED, BLUE, YELLOW, GREEN, WHITE, BLACK, GRAY, PURPLE, BROWN, ORANGE
	};

	/**
	 * @return the text that is contained inside this attribute.
	 */
	public String getText();

	/**
	 * @return the color associated with this attribute.
	 */
	public Color getColor();

	/**
	 * Change the color associated with this attribute with a new one.
	 * @param newColor: the new color for the attribute. Required to be not null.
	 * @throws NullPointerException if newColor is null.
	 */
	public void changeColor(Color newColor) throws NullPointerException;

}