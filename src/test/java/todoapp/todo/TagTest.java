package todoapp.todo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import todoapp.exceptions.EmptyFieldException;
import todoapp.todo.Tag.Color;

class TagTest {

	private todoapp.todo.Tag t;

    /**
     * Method to test the constructor method.
     * @param text: the text for the new Tag.
	 * @param color: the color for the new Tag.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#tagCreationParametersProvider")
    @DisplayName("Testing the constructor method with both empty and filled text and color")
    @Tag("Tag")
    void constructorTagTesting(String text, Color color) {
        if (text == null) {
			assertThrows(NullPointerException.class, () -> t = new todoapp.todo.Tag(text));
		} else if (text.isEmpty()) {
            assertThrows(EmptyFieldException.class, () -> t = new todoapp.todo.Tag(text));
        } else {
			if (color == null) {
				assertThrows(NullPointerException.class, () -> t = new todoapp.todo.Tag(text, color));
			} else {
				try {
					t = new todoapp.todo.Tag(text, color);
				} catch (Exception e) {
					fail("Should not be thrown!");
				}
				assertEquals(text, t.getText());
				assertEquals(color, t.getColor());
			}
        }
	}

	/**
     * Method to test the changeText method.
     * @param text: the new text for the Tag.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#stringProvider")
    @DisplayName("Testing the changeText method with both empty and filled text")
    @Tag("Tag")
    void changeTextTagTesting(String text) {
		try {
			t = new todoapp.todo.Tag("Before");
		} catch (Exception e) {
			fail("Should not be thrown!");
		}

        if (text == null || text.isEmpty()) {
            assertThrows(AssertionError.class, () -> t.changeText(text));
        } else {	
			t.changeText(text);
			assertEquals(text, t.getText());
        }
	}

	/**
     * Method to test the changeColor method.
     * @param color: the new color for the Tag.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#colorProvider")
    @DisplayName("Testing the changeColor method with both null and valid color")
    @Tag("Tag")
    void changeColorTagTesting(Color color) {
		try {
			t = new todoapp.todo.Tag("Test", Color.BLACK);
		} catch (Exception e) {
			fail("Should not be thrown!");
		}

        if (color == null) {
            assertThrows(AssertionError.class, () -> t.changeColor(color));
        } else {
			t.changeColor(color);
			assertEquals(color, t.getColor());
        }
	}

	/**
     * Method to test the equals method.
	 * @param tag: the Tag instance to test.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#tagProvider")
    @DisplayName("Testing the equals method with a predefined test Tag")
    @Tag("Tag")
	void equalsTesting(todoapp.todo.Tag tag) {
		todoapp.todo.Tag test = null;
		try {
			test = new todoapp.todo.Tag("Test", Color.RED);
			if (tag == null) {
				assertEquals(false, test.equals(tag));
			} else {
				if (tag.getColor() != Color.RED) {
					assertEquals(false, test.equals(tag));
				} else {
					assertEquals(true, test.equals(tag));
				}
			}
		} catch (Exception e) {
			fail("Should not be thrown!");
		}
	}
}
