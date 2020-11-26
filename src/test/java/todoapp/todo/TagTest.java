package todoapp.todo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.*;

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
    @MethodSource("stringAndColorProvider")
    @DisplayName("Testing the constructor method with both empty and filled name and color")
    @Tag("Tag")
    void constructorTagTesting(String name, Color color) {
        if (name.isEmpty()) {
            assertThrows(EmptyFieldException.class, () ->  t = new todoapp.todo.Tag(name));
        } else {
			if (color == null) {
				assertThrows(EmptyFieldException.class, () ->  t = new todoapp.todo.Tag(name, color));
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
    @MethodSource("stringProvider")
    @DisplayName("Testing the changeName method with both empty and filled name")
    @Tag("Tag")
    void changeNameTagTesting(String name) {
		try {
			t = new todoapp.todo.Tag(name);
		} catch (EmptyFieldException efe) { }

        if (name.isEmpty()) {
            assertThrows(EmptyFieldException.class, () ->  t.changeName(name));
        } else {
			try {
				t.changeName(name);
			} catch (EmptyFieldException efe) {
				fail("Should not be thrown!");
			}
			assertEquals(name, t.getName());
        }
	}

	/**
     * Method to test the changeColor method.
     * @param color: the new color for the Tag.
     */
    @ParameterizedTest
    @MethodSource("colorProvider")
    @DisplayName("Testing the changeColor method with both null and valid color")
    @Tag("Tag")
    void changeColorTagTesting(Color color) {
		try {
			t = new todoapp.todo.Tag("Test", color);
		} catch (EmptyFieldException efe) { }

        if (color == null) {
            assertThrows(EmptyFieldException.class, () ->  t.changeColor(color));
        } else {
			try {
				t.changeColor(color);
			} catch (EmptyFieldException efe) {
				fail("Should not be thrown!");
			}
			assertEquals(color, t.getColor());
        }
	}

	/**
     * Return a stream of default data to use for test.
     * @return a stream of strings.
     */
    private static Stream<String> stringProvider() {
        return Stream.of("", "Test");
	}
	
	/**
     * Return a stream of default data to use for test.
     * @return a stream of color.
     */
    private static Stream<Color> colorProvider() {
        return Stream.of(null, Color.RED);
    }
	
	/**
     * Return a stream of default data to use for test.
     * @return a stream of couple (string, color).
     */
    private static Stream<Arguments> stringAndColorProvider() {
        return Stream.of(
            Arguments.of("", null),
            Arguments.of("Test", null),
            Arguments.of("Test", Color.GREEN),
            Arguments.of("", Color.GREEN)
        );
    }
	
}
