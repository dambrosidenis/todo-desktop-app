package todoapp.todo;

// import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.*;

import todoapp.exceptions.EmptyFieldException;

class ToDoListTest {

    private ToDoList tdl;

    /**
     * Method to test the addToDo method.
     * @param title: the title for the new ToDo.
     */
    @ParameterizedTest
    @MethodSource("stringProvider")
    @DisplayName("Testing addToDo method with both empty and filled title")
    @Tag("ToDoList")
    void addToDoTesting(String title) {
        tdl = new ToDoList();

        if (title.isEmpty()) {
            assertThrows(EmptyFieldException.class, () -> tdl.addToDo(title, ""));
        } else {
            try {
                tdl.addToDo(title, "");
            } catch (EmptyFieldException efe) {
                fail("Should not be thrown!");
            }

            titleTest(title);
            descriptionTest("");
        }
    }

    /**
     * Method to test the removeToDo method.
     * @param index: the index of the ToDo to remove.
     */
    @ParameterizedTest
    @MethodSource("indexProvider")
    @DisplayName("Testing removeToDo method with index < 0, index > size and correct indexes")
    @Tag("ToDoList")
    void removeToDoTesting(int index) {
        tdl = new ToDoList();

        try {
            tdl.addToDo("Test1", "");
        } catch (EmptyFieldException efe) { }

        if (index < 0 || index >= 1) {
            assertThrows(IndexOutOfBoundsException.class, () -> tdl.removeToDo(index));
        } else {
            tdl.removeToDo(index);
            assertEquals(0, tdl.size());
        }
    }

    /**
     * Method to test the modifyToDoTitle method.
     * @param index: the index of the ToDo to modify.
     * @param newTitle: the new title for the selected ToDo.
     */
    @ParameterizedTest
    @MethodSource("indexAndStringProvider")
    @DisplayName("Testing modifyToDoTitle method with both index correct and not, and with both empty title and not")
    @Tag("ToDoList")
    void modifyToDoTitleTesting(int index, String newTitle) {
        tdl = new ToDoList();

        try {
            tdl.addToDo("Test", "");
        } catch (EmptyFieldException efe) { }

        if (index < 0 || index >= 1) {
            assertThrows(IndexOutOfBoundsException.class, () -> tdl.modifyToDoTitle(index, newTitle));
        } else if (newTitle.isEmpty()) {
            assertThrows(EmptyFieldException.class, () -> tdl.modifyToDoTitle(index, newTitle));
        } else {
            try {
                tdl.modifyToDoTitle(index, newTitle);
            } catch (EmptyFieldException efe) {
                fail("Should not be thrown!");
            }
            titleTest(newTitle);
        }
    }

    /**
     * Method to test the addToDoTag method.
     * @param index: the index of the ToDo to modify.
     * @param newTag: the new tag to add.
     */
    @ParameterizedTest
    @MethodSource("indexAndStringProvider")
    @DisplayName("Testing addToDoTag method with both index correct and not, and with both empty tag and not")
    @Tag("ToDoList")
    @Disabled("Disabled because there isn't a method to get the tag of a ToDo")
    void addToDoTagTesting(int index, String newTag) {
        tdl = new ToDoList();

        try {
            tdl.addToDo("Test", "");
        } catch (EmptyFieldException efe) { }

        if (index < 0 || index > 1) {
            assertThrows(IndexOutOfBoundsException.class, () -> tdl.addToDoTag(index, newTag));
        } else if (newTag.isEmpty()) {
            assertThrows(EmptyFieldException.class, () -> tdl.addToDoTag(index, newTag));
        } else {
            try {
                tdl.addToDoTag(index, newTag);
            } catch (EmptyFieldException efe) {
                fail("Should not be thrown!");
            }

        }
    }

    /**
     * Return a stream of default data to use for test.
     * @return a stream of couple (int, string).
     */
    private static Stream<Arguments> indexAndStringProvider() {
        return Stream.of(
            Arguments.of(-1, ""),
            Arguments.of(0, ""),
            Arguments.of(0, "New Test"),
            Arguments.of(1, "Test")
        );
    }

    /**
     * Return a stream of default data to use for test.
     * @return a stream of indexes.
     */
    private static IntStream indexProvider() {
        return IntStream.range(-1, 2);
    }

    /**
     * Return a stream of default data to use for test.
     * @return a stream of strings.
     */
    private static Stream<String> stringProvider() {
        return Stream.of("", "Test");
    }

    /**
     * Method to test the title of a ToDo comparing it with the one passed.
     * @param title: the title to compare with.
     */
    private void titleTest(String title) {
        String[] todoData = tdl.getToDoData(0);
        assertEquals(title, todoData[0]);
    }

    /**
     * Method to test the description of a ToDo comparing it with the one passed.
     * @param description: the description to compare with.
     */
    private void descriptionTest(String description) {
        String[] todoData = tdl.getToDoData(0);
        assertEquals(description, todoData[1]);
    }

}   // ToDoListTest class
