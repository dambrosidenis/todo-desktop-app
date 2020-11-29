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
     * @param name: the name for the new Tag.
	 * @param color: the color for the new Tag.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#tagCreationParametersProvider")
    @DisplayName("Testing the constructor method with both empty and filled name and color")
    @Tag("Tag")
    void constructorTagTesting(String name, Color color) {
        if (name == null || name.isEmpty()) {
            assertThrows(EmptyFieldException.class, () -> t = new todoapp.todo.Tag(name));
        } else {
			if (color == null) {
				assertThrows(EmptyFieldException.class, () -> t = new todoapp.todo.Tag(name, color));
			} else {
				try {
					t = new todoapp.todo.Tag(name, color);
				} catch (EmptyFieldException efe) {
					fail("Should not be thrown!");
				}
				assertEquals(name, t.getName());
				assertEquals(color, t.getColor());
			}
        }
	}

	/**
     * Method to test the changeName method.
     * @param name: the new name for the Tag.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#stringProvider")
    @DisplayName("Testing the changeName method with both empty and filled name")
    @Tag("Tag")
    void changeNameTagTesting(String name) {
		try {
			t = new todoapp.todo.Tag("Before");
		} catch (EmptyFieldException efe) { }

        if (name == null || name.isEmpty()) {
            assertThrows(AssertionError.class, () -> t.changeName(name));
        } else {	
			t.changeName(name);
			assertEquals(name, t.getName());
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
		} catch (EmptyFieldException efe) { }

        if (color == null) {
            assertThrows(AssertionError.class, () -> t.changeColor(color));
        } else {
			t.changeColor(color);
			assertEquals(color, t.getColor());
        }
	}

	/**
     * Method to test the equals method.
	 * @param name: the name of the Tag to compare.
     * @param color: the color of the Tag to compare.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#tagCreationParametersProvider")
    @DisplayName("Testing the equals method with a predefined test Tag")
    @Tag("Tag")
	void equalsTesting(String name, Color color) {
		todoapp.todo.Tag test = null;
		try {
			test = new todoapp.todo.Tag("Test", Color.RED);
		} catch (EmptyFieldException efe) {}
		if (name == null || name.isEmpty()) {
			assertEquals(false, test.equals(null));
		} else {
			try {
				t = new todoapp.todo.Tag(name, color);
				if (color == null) {
					assertEquals(false, test.equals(t));
				} else {
					assertEquals(true, test.equals(t));
				}
			} catch (EmptyFieldException efe) {}
		}
	}
}
