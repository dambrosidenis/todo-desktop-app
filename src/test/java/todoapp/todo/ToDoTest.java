package todoapp.todo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import todoapp.todo.Tag.Color;
import todoapp.exceptions.EmptyFieldException;

class ToDoTest {

	private ToDo td;

	/**
     * Method to test the constructor method.
     * @param name: the name for the new Tag.
	 * @param color: the color for the new Tag.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoCreationParametersProvider")
    @DisplayName("Testing the constructor method")
    @Tag("ToDo")
    void constructorTagTesting(String todoTitle, String todoDescription, String tagName, Color tagColor) {
        if (todoTitle == null || todoTitle.isEmpty()) {
            assertThrows(EmptyFieldException.class, () ->  td = new ToDo(todoTitle, todoDescription, tagName, tagColor));
        } else if (tagName == null || tagName.isEmpty()) {
			assertThrows(EmptyFieldException.class, () ->  td = new ToDo(todoTitle, todoDescription, tagName, tagColor));
		} else {
			try {
				td = new ToDo(todoTitle, todoDescription, tagName, tagColor);
			} catch (EmptyFieldException efe) {
				fail("Should not be thrown!");
			}

			todoapp.todo.Tag t;
			try {
				if (tagColor == null) {
					t = new todoapp.todo.Tag(tagName);
				} else {
					t = new todoapp.todo.Tag(tagName, tagColor);
				}
				assertEquals(todoTitle, td.getTitle());
				assertEquals(todoDescription, td.getDescription());
				assertEquals(true, td.getTags().contains(t));
			} catch (EmptyFieldException efe) {}
		}
	}

	/**
	 * Method to test the setTitle method.
	 * @param newTitle: the new title of the ToDo.
	 */
	@ParameterizedTest
	@MethodSource("todoapp.todo.SourceArguments#stringProvider")
	@DisplayName("Testing the setTitle method")
	@Tag("ToDo")
	void setTitleTest(String newTitle) {
		try {
			td = new ToDo("Before", "...");
		} catch (EmptyFieldException efe) {}

		if (newTitle == null || newTitle.isEmpty()) {
			assertThrows(AssertionError.class, () -> td.setTitle(newTitle));
		} else {
			td.setTitle(newTitle);
			assertEquals(newTitle, td.getTitle());
		}
	}

	/**
     * Method to test the addTag method.
     * @param name: the name for the new Tag.
	 * @param color: the color for the new Tag.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#tagCreationParametersProvider")
    @DisplayName("Testing the addTag method")
    @Tag("ToDo")
	void addTagTest(String tagName, Color tagColor) {
		try {
			td = new ToDo("New Todo", null, "Test", null);
		} catch (EmptyFieldException efe) {}

		if (tagName == null || tagName.isEmpty()) {
			assertThrows(AssertionError.class, () -> td.addTag(tagName, tagColor));
		} else if (tagColor == null) {
			todoapp.todo.Tag t = null;
			try {
				t = new todoapp.todo.Tag(tagName);
			} catch (EmptyFieldException efe) {}
			assertEquals(true, td.getTags().contains(t));
			assertEquals(false, td.addTag(tagName, tagColor));
			assertEquals(true, td.getTags().contains(t));
		} else {
			todoapp.todo.Tag t = null;
			try {
				t = new todoapp.todo.Tag(tagName, tagColor);
			} catch (EmptyFieldException efe) {}
			assertEquals(false, td.getTags().contains(t));
			assertEquals(true, td.addTag(tagName, tagColor));
			assertEquals(true, td.getTags().contains(t));
		}
	}

	/**
     * Method to test the deleteTag method.
     * @param name: the name for the Tag to remove.
	 * @param color: the color for the Tag to remove.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#tagCreationParametersProvider")
    @DisplayName("Testing the deleteTag method")
    @Tag("ToDo")
	void deleteTagTest(String tagName, Color tagColor) {
		try {
			td = new ToDo("New Todo", null, "Test", null);
		} catch (EmptyFieldException efe) {}

		if (tagName == null || tagName.isEmpty()) {
			assertThrows(AssertionError.class, () -> td.deleteTag(tagName, tagColor));
		} else if (tagColor == null) {
			todoapp.todo.Tag t = null;
			try {
				t = new todoapp.todo.Tag(tagName);
			} catch (EmptyFieldException efe) {}
			assertEquals(true, td.getTags().contains(t));
			assertEquals(true, td.deleteTag(tagName, tagColor));
			assertEquals(false, td.getTags().contains(t));
		} else {
			todoapp.todo.Tag t = null;
			try {
				t = new todoapp.todo.Tag(tagName, tagColor);
			} catch (EmptyFieldException efe) {}
			assertEquals(false, td.getTags().contains(t));
			assertEquals(false, td.deleteTag(tagName, tagColor));
			assertEquals(false, td.getTags().contains(t));
		}
	}

	/**
     * Method to test the equals method.
	 * @param name: the name of the Tag to compare.
     * @param color: the color of the Tag to compare.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoCreationParametersProvider")
    @DisplayName("Testing the equals method with a predefined test ToDo")
    @Tag("ToDo")
	void equalsTesting(String todoTitle, String todoDescription, String tagName, Color tagColor) {
		ToDo test = null;
		try {
			test = new ToDo("Test", "", "Test", null);
		} catch (EmptyFieldException efe) {}
		if (todoTitle == null || todoTitle.isEmpty()) {
			assertEquals(false, test.equals(null));
		} else {
			try {
				if (tagName == null) {
					td = new ToDo(todoTitle, todoDescription);
				} else {
					td = new ToDo(todoTitle, todoDescription, tagName, tagColor);
				}
			} catch (EmptyFieldException efe) {}
			if (todoTitle.compareTo("Test") == 0 && todoDescription != null && todoDescription.isEmpty() && tagName != null && tagName.compareTo("Test") == 0 && tagColor == null) {
				assertEquals(true, test.equals(td));
			} else {
				assertEquals(false, test.equals(td));
			}
		}
	}
}
