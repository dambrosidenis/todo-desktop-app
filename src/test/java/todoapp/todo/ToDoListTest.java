package todoapp.todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

import todoapp.exceptions.EmptyFieldException;

public class ToDoListTest {

    @Test
    @DisplayName("addToDo(\"Test\", \"\") = ToDo(name:\"Test\", description:\"\", tags:none)")
    public void testAddTodo() {
        ToDoList tdl = new ToDoList();

        try {
            tdl.addToDo("Test", "");
        } catch (EmptyFieldException efe) {
            fail("Should not be thrown!");
        }

        String[] todoData = tdl.getToDoData(0);
        assertEquals("Test", todoData[0]);
        assertEquals("", todoData[1]);
    }

}
