package todoapp.todo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import todoapp.todo.exceptions.*;

class ToDoListTest {

	private ToDoList tdl;

    /**
     * Method to test the addToDo method.
     * @param td: the new instance of ToDo to add.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoProvider")
    @DisplayName("Testing addToDo method")
	@Tag("ToDoList")
    void addToDoTesting(ToDo td) {
        tdl = new ToDoList();

        if (td == null) {
            assertThrows(NullPointerException.class, () -> tdl.addToDo(td));
        } else {
			assertEquals(false, tdl.contains(td));
            try {
                assertEquals(true, tdl.addToDo(td));
            } catch (Exception e) {
                fail("Should not be thrown!");
            }
			assertEquals(true, tdl.contains(td));
        }
	}

    /**
     * Method to test the removeToDo method.
     * @param td: the new instance of ToDo to remove.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoProvider")
    @DisplayName("Testing removeToDo method")
	@Tag("ToDoList")
    void removeToDoTesting(ToDo td) {
		tdl = new ToDoList();

        if (td == null) {
            assertThrows(NullPointerException.class, () -> tdl.removeToDo(td));
        } else {
			tdl.addToDo(td);
            assertEquals(true, tdl.removeToDo(td));
            assertEquals(false, tdl.removeToDo(td));
        }
    }

    /**
     * Method to test the modifyToDoTitle method.
     * @param td: the ToDo to modify.
     * @param newTitle: the new title for the selected ToDo.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoAndStringProvider")
    @DisplayName("Testing modifyToDoTitle method")
	@Tag("ToDoList")
    void modifyToDoTitleTesting(ToDo td, String newTitle) {
        tdl = new ToDoList();

        if (td == null || newTitle == null) {
            assertThrows(NullPointerException.class, () -> tdl.modifyToDoTitle(td, newTitle));
        } else if (newTitle.isEmpty()) {
            assertThrows(EmptyFieldException.class, () -> tdl.modifyToDoTitle(td, newTitle));
        } else {
			tdl.addToDo(td);
            try {
				ToDo mod = tdl.modifyToDoTitle(td, newTitle);
				assertNotEquals(null, mod);
				assertEquals(true, mod.getTitle().compareTo(newTitle) == 0);
				if (td.getTitle().compareTo(newTitle) != 0) {
					assertEquals(false, tdl.contains(td));
				}
            } catch (Exception e) {
                fail("Should not be thrown!");
            }
        }
	}
	
	/**
     * Method to test the modifyToDoDescription method.
     * @param td: the ToDo to modify.
     * @param newDescr: the new description for the selected ToDo.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoAndStringProvider")
    @DisplayName("Testing modifyToDoDescription method")
	@Tag("ToDoList")
    void modifyToDoDescriptionTesting(ToDo td, String newDescr) {
        tdl = new ToDoList();

        if (td == null) {
            assertThrows(NullPointerException.class, () -> tdl.modifyToDoTitle(td, newDescr));
        } else {
			tdl.addToDo(td);
			ToDo mod = null;
            try {
				mod = tdl.modifyToDoDescription(td, newDescr);
			} catch (Exception e) {
                fail("Should not be thrown!");
            }
			assertNotEquals(null, mod);
			if (newDescr != null) {
				assertEquals(true, mod.getDescription().compareTo(newDescr) == 0);
			} else {
				assertEquals(null, mod.getDescription());
			}
			if (!td.equals(mod)) {
				assertEquals(false, tdl.contains(td));
			}
        }
    }

    /**
     * Method to test the addToDoTag method.
     * @param td: the ToDo to modify.
     * @param newTag: the new tag to add.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoAndTagProvider")
    @DisplayName("Testing addToDoTag method")
    @Tag("ToDoList")
    void addToDoTagTesting(ToDo td, todoapp.todo.Tag newTag) {
        tdl = new ToDoList();

        if (td == null || newTag == null) {
            assertThrows(NullPointerException.class, () -> tdl.addToDoTag(td, newTag));
        } else {
			tdl.addToDo(td);
			ToDo mod = null;
            try {
				mod = tdl.addToDoTag(td, newTag);
			} catch (Exception e) {
                fail("Should not be thrown!");
            }
			assertNotEquals(null, mod);
			assertEquals(true, mod.getTags().contains(newTag));
			if (!td.equals(mod)) {
				assertEquals(false, tdl.contains(td));
			}
        }
	}
	
	/**
     * Method to test the deleteToDoTag method.
     * @param td: the ToDo to modify.
     * @param tag: the tag to remove.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#todoAndTagProvider")
    @DisplayName("Testing deleteToDoTag method")
    @Tag("ToDoList")
    void deleteToDoTagTesting(ToDo td, todoapp.todo.Tag tag) {
        tdl = new ToDoList();

        if (td == null || tag == null) {
            assertThrows(NullPointerException.class, () -> tdl.deleteToDoTag(td, tag));
        } else {
			tdl.addToDo(td);
			ToDo mod = null;
            try {
				mod = tdl.deleteToDoTag(td, tag);
			} catch (Exception e) {
                fail("Should not be thrown!");
            }
			assertNotEquals(null, mod);
			if (td.getTags().contains(tag)) {
				assertEquals(false, mod.getTags().contains(tag));
			} else {
				assertEquals(true, td.equals(mod));
			}
        }
    }

}   // ToDoListTest class
