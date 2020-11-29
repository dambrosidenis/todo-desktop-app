package todoapp.todo;

import todoapp.exceptions.EmptyFieldException;

/**
 * This class provide an ADT for the Tag object. A Tag object is 
 * characterized by a name and a color. The object is mutable.
 * ADT Invariants: the Tag object must have a non-empty name and a color.
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
	 * - a String variable that represent the name
	 * - a Color variable that represent the color
	 * 
	 * INVARIANT: the name must be not null and not empty. The color must be 
	 * not null
	 */
	private String name;
	private Color color;
	
	/**
	 * Constructor that specify the name for the tag. The color is setted to 
	 * the predefined one (blue).
	 * @param name: the name of the Tag. REQUIRED to be not null and not empty.
	 * @throws EmptyFieldException when the name is null or empty.
	 */
	public Tag(String name) throws EmptyFieldException {
		if (name == null || name.isEmpty()) {
			throw new EmptyFieldException("A Tag must have a name.", "name", "A valid non-empty string.");
		}

		this.name = name;
		this.color = Color.BLUE;
	}

	/**
	 * Constructor that specify the name for the tag and its color.
	 * @param name: the name of the Tag. REQUIRED to be not null and not empty.
	 * @param color: the color of the tag. REQUIRED to be not null.
	 * @throws EmptyFieldException when the name is null or empty, or when the 
	 * color is null.
	 */
	public Tag(String name, Color color) throws EmptyFieldException {
		if (name == null || name.isEmpty()) {
			throw new EmptyFieldException("A Tag must have a name.", "name", "A valid non-empty string.");
		} else if (color == null) {
			throw new EmptyFieldException("A Tag must have a color.", "color", "A valid color.");
		}

		this.name = name;
		this.color = color;
	}

	/**
	 * Copy constructor that creates a perfect copy of the Tag instance passed.
	 * @param t: the Tag to copy. REQUIRED not null.
	 * @throws EmptyFieldException when t is null.
	 */
	public Tag(Tag t) throws EmptyFieldException  {
		if (t == null) {
			throw new EmptyFieldException("A Tag cannot be null.", "t", "A valid Tag instance.");
		}

		this.name = t.name;
		this.color = t.color;
	}

	/**
     * RETURN the name of the tag.
     * @return the name of the tag as String.
     */
	public String getName() {
		return name;
	}

	/**
     * RETURN the color of the tag.
     * @return the color of the tag.
     */
	public Color getColor() {
		return color;
	}

	/**
	 * MODIFY the name of the tag, changing it with a new one.
	 * @param newName: the new name of the tag. REQUIRED to be not null and not 
	 * empty.
	 */
	void changeName(String newName) {

		assert (newName != null && !newName.isEmpty()): "A Tag must have a name.";

		name = newName;
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
	 * @return true if obj and this have same name and color, false 
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
		if (this.name.length() != t.name.length()) {
			return false;
		}

		if (this.name.compareTo(t.name) != 0) {
			return false;
		} 

		if (this.color != t.color) {
			return false;
		}

		return true;
	}

}	// class Tag
