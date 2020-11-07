package todoapp.todo;

// import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import todoapp.exceptions.EmptyFieldException;

public class ToDoListTest {

    private ToDoList tdl;

    @ParameterizedTest
    @ValueSource(strings = {"", "Test"})
    @DisplayName("Testing addToDo method with both empty and filled title")
    @Tag("ToDoList")
    public void addToDoTesting(String title) {
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

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2})
    @DisplayName("Testing removeToDo method with index < 0, index > size and correct indexes")
    @Tag("ToDoList")
    public void removeToDoTesting(int index) {
        tdl = new ToDoList();

        try {
            tdl.addToDo("Test1", "");
            tdl.addToDo("Test2", "");
        } catch (EmptyFieldException efe) { }

        if (index < 0 || index >= 2) {
            assertThrows(IndexOutOfBoundsException.class, () -> tdl.removeToDo(index));
        } else {
            tdl.removeToDo(index);
            assertEquals(1, tdl.size());
        }
    }

    @ParameterizedTest
    @CsvSource({"-1,''", "0,''", "0,'New Title'", "1,'Title"})
    @DisplayName("Testing modifyToDoTitle method with both index correct and not, and with both empty title and not")
    @Tag("ToDoList")
    public void modifyToDoTitleTesting(int index, String newTitle) {
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

    private void titleTest(String title) {
        String[] todoData = tdl.getToDoData(0);
        assertEquals(title, todoData[0]);
    }

    private void descriptionTest(String description) {
        String[] todoData = tdl.getToDoData(0);
        assertEquals(description, todoData[1]);
    }

}
