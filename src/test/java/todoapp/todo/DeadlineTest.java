package todoapp.todo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import todoapp.todo.Attribute.Color;
import todoapp.todo.exceptions.*;

class DeadlineTest {

	private Deadline d;

    /**
     * Method to test the constructor method.
     * @param date: the date for the new Deadline.
	 * @param color: the color for the new Deadline.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#deadlineCreationParametersProvider")
    @DisplayName("Testing the constructor method with both valid and non valid dates and colors")
    @Tag("Deadline")
    void constructorDeadlineTesting(LocalDateTime date, Color color) {
        if (date == null) {
			assertThrows(NullPointerException.class, () -> d = new Deadline(date));
		} else if (date.isBefore(LocalDateTime.now())) {
            assertThrows(PastDateException.class, () -> d = new Deadline(date));
        } else {
			if (color == null) {
				assertThrows(NullPointerException.class, () -> d = new Deadline(date, color));
			} else {
				try {
					d = new Deadline(date, color);
				} catch (Exception e) {
					fail("Should not be thrown!");
				}
				assertEquals(date.toString(), d.getText());
				assertEquals(color, d.getColor());
			}
        }
	}

	/**
     * Method to test the changeDate method.
     * @param date: the new date for the Deadline.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#dateProvider")
    @DisplayName("Testing the changeDate method with both valid and non valid dates")
    @Tag("Deadline")
    void changeDateDeadlineTesting(LocalDateTime date) {
		try {
			d = new Deadline(LocalDateTime.now().plusDays(1));
		} catch (Exception e) {
			fail("Should not be thrown!");
		}
		
		if (date == null) {
			assertThrows(NullPointerException.class, () -> d.changeDate(date));
		} else if (date.isBefore(LocalDateTime.now())) {
            assertThrows(PastDateException.class, () -> d.changeDate(date));
        } else {	
			try {
				d.changeDate(date);
				assertEquals(date.toString(), d.getText());
			} catch (Exception e) {
				fail("Should not be thrown!");
			}
        }
	}

	/**
     * Method to test the changeColor method.
     * @param color: the new color for the Deadline.
     */
    @ParameterizedTest
    @MethodSource("todoapp.todo.SourceArguments#colorProvider")
    @DisplayName("Testing the changeColor method with both null and valid color")
    @Tag("Deadline")
    void changeColorDeadlineTesting(Color color) {
		try {
			d = new Deadline(LocalDateTime.now().plusDays(1), Color.BLACK);
		} catch (Exception e) {
			fail("Should not be thrown!");
		}

        if (color == null) {
            assertThrows(NullPointerException.class, () -> d.changeColor(color));
        } else {
			d.changeColor(color);
			assertEquals(color, d.getColor());
        }
	}
}
