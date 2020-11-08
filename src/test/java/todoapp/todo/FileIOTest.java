package todoapp.todo;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class FileIOTest {
    
    @TempDir
    Path tempDir;

    private FileIO file;

    /**
     * Method to test the create method.
     */
    @Test
    @DisplayName("Testing create method using tempDir temporary directory")
    @Tag("FileIO")
    void createTesting() {
        file = new FileIO(tempDir.resolve("Test.txt").toString());

        try {
            file.create();
        } catch (IOException ioe) {
            fail("Should not be thrown!", ioe);
        }

        assertEquals(true, file.alreadyExist());
    }
    
    /**
     * Method to test the save method.
     */
    @Test
    @DisplayName("Testing save method using tempDir temporary directory")
    @Tag("FileIO")
    void saveTesting() {
        boolean result;
        List<String> read = null;
        file = new FileIO(tempDir.resolve("Test.txt").toString());

        try {
            result = file.save("Test data");
            assertEquals(false, result);
        } catch (IOException ioe) {
            fail("Should not be thrown!", ioe);
        }

        try {
            file.create();
            result = file.save("Test data");
            assertEquals(true, result);
            read = Files.readAllLines(tempDir.resolve("Test.txt"));
            assertEquals(List.of("Test data"), read);
        } catch (IOException ioe) {
            fail("Should not be thrown!", ioe);
        }
        
    }

    /**
     * Method to test the load method.
     */
    @Test
    @DisplayName("Testing load method using tempDir temporary directory")
    @Tag("FileIO")
    void loadTesting() {
        String result = null;
        file = new FileIO(tempDir.resolve("Test.txt").toString());

        try {
            result = file.load();
            assertEquals(null, result);
        } catch (IOException ioe) {
            fail("Should not be thrown!", ioe);
        }

        try {
            file.create();
            file.save("Test data");
            result = file.load();
            assertEquals("Test data", result);
        } catch (IOException ioe) {
            fail("Should not be thrown!", ioe);
        }
    }

}
