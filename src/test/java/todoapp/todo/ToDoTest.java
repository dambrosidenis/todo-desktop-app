package todoapp.todo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import todoapp.todo.exceptions.*;

class ToDoTest {

	private ToDo td;

	/**
	 * Creates a copy of the Attribute attr.
	 * @param attr: the attribute to copy. Required to be not null.
	 * @return the copy of attr.
	 */
	private Attribute copyAttribute(Attribute attr) {

		assert attr != null;

		Attribute res = null;

		if (attr instanceof todoapp.todo.Tag) {
			res =  new todoapp.todo.Tag((todoapp.todo.Tag) attr);
		} else if (attr instanceof Deadline) {
			res = new Deadline((Deadline) attr);
		}

		assert res != null;

		return res;
	}

	/**
     * Method to test the constructor method.
     * @param todoTitle: the title for the new ToDo.
	 * @param todoDescription: the description for the new Tag.
	 * @param attr: the Attribute for the new ToDo.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoCreationParametersProvider")
    @DisplayName("Testing the constructor method")
    @Tag("ToDo")
    void constructorToDoTesting(String todoTitle, String todoDescription, Attribute attr){
		if (todoTitle == null) {
			assertThrows(NullPointerException.class, () ->  td = new ToDo(todoTitle, todoDescription, attr));
		} else if (todoTitle.isEmpty()) {
            assertThrows(EmptyFieldException.class, () ->  td = new ToDo(todoTitle, todoDescription, attr));
        } else {
			try {
				td = new ToDo(todoTitle, todoDescription, attr);
				if (attr == null) {
					assertEquals(0, td.getAttributes().size());
				} else {
					Attribute test = copyAttribute(attr);
					assertEquals(true, td.getAttributes().contains(test));
				}
				assertEquals(todoTitle, td.getTitle());
				assertEquals(todoDescription, td.getDescription());
			} catch (Exception e) {
				fail("Should not be thrown!");
			}
		}
	}

	/**
	 * Method to test the changeTitle method.
	 * @param newTitle: the new title of the ToDo.
	 */
	@ParameterizedTest
	@MethodSource("todoapp.todo.SourceArguments#stringProvider")
	@DisplayName("Testing the changeTitle method")
	@Tag("ToDo")
	void changeTitleTest(String newTitle) {
		try {
			td = new ToDo("Before", "...");
		} catch (Exception e) {
			fail("Should not be thrown!");
		}

		if (newTitle == null) {
			assertThrows(NullPointerException.class, () -> td.changeTitle(newTitle));
		} else if (newTitle.isEmpty()) {
			assertThrows(EmptyFieldException.class, () -> td.changeTitle(newTitle));
		} else {
			try {
				td.changeTitle(newTitle);
				assertEquals(newTitle, td.getTitle());
			} catch (EmptyFieldException efe) {
				fail("Should not be thrown!");
			}
		}
	}

	/**
	 * Method to test the changeDescription method.
	 * @param newDescr: the new description of the ToDo.
	 */
	@ParameterizedTest
	@MethodSource("todoapp.todo.SourceArguments#stringProvider")
	@DisplayName("Testing the changeDescription method")
	@Tag("ToDo")
	void changeDescriptionTest(String newDescr) {
		try {
			td = new ToDo("Before", "...");
		} catch (Exception e) {
			fail("Should not be thrown!");
		}

		td.changeDescription(newDescr);
		assertEquals(newDescr, td.getDescription());
	}

	/**
     * Method to test the addAttribute method.
     * @param attr: the new Attribute to add.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#attributeProvider")
    @DisplayName("Testing the addAttribute method")
    @Tag("ToDo")
	void addAttributeTest(Attribute attr) {
		try {
			td = new ToDo("New Todo", null, new todoapp.todo.Tag("Test"));
		} catch (Exception e) {}
		if (attr == null) {
			assertThrows(NullPointerException.class, () -> td.addAttribute(attr));
		} else {
			if (attr instanceof todoapp.todo.Tag && attr.getText().compareTo("Test") == 0 && attr.getColor() == Attribute.Color.BLUE) {
				assertEquals(true, td.getAttributes().contains(attr));
				assertEquals(false, td.addAttribute(attr));
			} else {
				assertEquals(false, td.getAttributes().contains(attr));
				assertEquals(true, td.addAttribute(attr));
				assertEquals(true, td.getAttributes().contains(attr));
			}
		}
	}

	/**
     * Method to test the removeAttribute method.
     * @param attr: the Attribute to delete.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#attributeProvider")
    @DisplayName("Testing the removeAttribute method")
    @Tag("ToDo")
	void removeAttributeTest(Attribute attr) {
		try {
			td = new ToDo("New Todo", null, new todoapp.todo.Tag("Test"));
		} catch (Exception e) {}

		if (attr == null) {
			assertEquals(false, td.removeAttribute(attr));
		} else {
			Attribute test = null;
			try {
				test = copyAttribute(attr);
			} catch (Exception e) {}
			if (attr instanceof todoapp.todo.Tag && attr.getText().compareTo("Test") == 0 && attr.getColor() == Attribute.Color.BLUE) {
				assertEquals(true, td.getAttributes().contains(attr));
				assertEquals(true, td.removeAttribute(attr));
				assertEquals(false, td.getAttributes().contains(test));
			} else {
				assertEquals(false, td.getAttributes().contains(attr));
				assertEquals(false, td.removeAttribute(attr));
			}
		}
	}
}
