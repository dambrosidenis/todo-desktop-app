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
     * @param todoTitle: the title for the new ToDo.
	 * @param todoDescription: the description for the new Tag.
	 * @param tag: the Tag for the new ToDo.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoCreationParametersProvider")
    @DisplayName("Testing the constructor method")
    @Tag("ToDo")
    void constructorToDoTesting(String todoTitle, String todoDescription, todoapp.todo.Tag tag){
		if (todoTitle == null || tag == null) {
			assertThrows(NullPointerException.class, () ->  td = new ToDo(todoTitle, todoDescription, tag));
		} else if (todoTitle.isEmpty()) {
            assertThrows(EmptyFieldException.class, () ->  td = new ToDo(todoTitle, todoDescription, tag));
        } else {
			try {
				td = new ToDo(todoTitle, todoDescription, tag);
				todoapp.todo.Tag test = new todoapp.todo.Tag(tag);
				assertEquals(todoTitle, td.getTitle());
				assertEquals(todoDescription, td.getDescription());
				assertEquals(true, td.getTags().contains(test));
			} catch (Exception e) {
				fail("Should not be thrown!");
			}
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
		} catch (Exception e) {
			fail("Should not be thrown!");
		}

		if (newTitle == null || newTitle.isEmpty()) {
			assertThrows(AssertionError.class, () -> td.setTitle(newTitle));
		} else {
			td.setTitle(newTitle);
			assertEquals(newTitle, td.getTitle());
		}
	}

	/**
     * Method to test the addTag method.
     * @param tag: the new Tag to add.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#tagProvider")
    @DisplayName("Testing the addTag method")
    @Tag("ToDo")
	void addTagTest(todoapp.todo.Tag tag) {
		try {
			td = new ToDo("New Todo", null, new todoapp.todo.Tag("Test"));
			if (tag == null) {
				assertThrows(AssertionError.class, () -> td.addTag(tag));
			} else {
				todoapp.todo.Tag test = new todoapp.todo.Tag(tag);
				
				assertEquals(false, td.getTags().contains(test));
				assertEquals(true, td.addTag(tag));
				assertEquals(true, td.getTags().contains(test));
			}
		} catch (Exception e) {
			fail("Should not be thrown!");
		}
	}

	/**
     * Method to test the deleteTag method.
     * @param tag: the Tag to delete.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#tagProvider")
    @DisplayName("Testing the deleteTag method")
    @Tag("ToDo")
	void deleteTagTest(todoapp.todo.Tag tag) {
		try {
			td = new ToDo("New Todo", null, new todoapp.todo.Tag("Test"));
		} catch (Exception e) {
			fail("Should not be thrown!");
		}

		if (tag == null) {
			assertThrows(AssertionError.class, () -> td.deleteTag(tag));
		} else {
			todoapp.todo.Tag test = null;
			try {
				test = new todoapp.todo.Tag(tag);
			} catch (Exception e) {
				fail("Should not be thrown!");
			}
			assertEquals(false, td.getTags().contains(test));
			assertEquals(false, td.deleteTag(tag));
			assertEquals(false, td.getTags().contains(test));
		}
	}

	/**
     * Method to test the equals method.
	 * @param todoTitle: the name of the ToDo to compare.
     * @param todoDescription: the description of the ToDo to compare.
	 * @param tag: the Tag to compare.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoCreationParametersProvider")
    @DisplayName("Testing the equals method with a predefined test ToDo")
    @Tag("ToDo")
	void equalsTesting(String todoTitle, String todoDescription, todoapp.todo.Tag tag) {
		ToDo test = null;
		try {
			test = new ToDo("Test", "", new todoapp.todo.Tag("Test"));
		} catch (Exception e) {
			fail("Should not be thrown!");
		}
		if (todoTitle == null || todoTitle.isEmpty()) {
			assertEquals(false, test.equals(null));
		} else {
			try {
				if (tag == null) {
					td = new ToDo(todoTitle, todoDescription);
				} else {
					td = new ToDo(todoTitle, todoDescription, tag);
				}
			} catch (Exception e) {
				fail("Should not be thrown!");
			}
			if (todoTitle.compareTo("Test") == 0 && todoDescription != null && todoDescription.isEmpty() && tag != null && tag.getColor() == Color.BLUE) {
				assertEquals(true, test.equals(td));
			} else {
				assertEquals(false, test.equals(td));
			}
		}
	}
}
