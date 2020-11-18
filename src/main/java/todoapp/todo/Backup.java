package todoapp.todo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import todoapp.exceptions.BackupFailedException;

public class Backup {

    ToDoList currentToDoList;
    String pathToBackup;

    public Backup(ToDoList currentToDoList, String pathToBackup) {
        this.currentToDoList = currentToDoList;
        this.pathToBackup = pathToBackup;
    }

    /**
     * Save the data into the file myBackup.bak.
     * @param currentToDoList valid instance of class ToDoList.
     * @throws BackupFailedException when the backup of the ToDoList fail.
     */
    void backupData() throws BackupFailedException {

        if (currentToDoList == null) {
            throw new IllegalArgumentException("The ToDoList have to be at least empty.");
        }

        StringBuilder dataReadyToBackup = new StringBuilder("{\n");
        int listLength = currentToDoList.size();
        for (int i = 0; i < listLength; i++) {
            dataReadyToBackup.append(dataReadyToBackup + encodeToDoToJSON(currentToDoList.getToDoData(i)));
        }
        dataReadyToBackup.append("\n}");

        FileIO fileHandler = new FileIO("myBackup.bak");

        try {
            fileHandler.create();
            fileHandler.save(dataReadyToBackup.toString());
        } catch (IOException ioe) {
            throw new BackupFailedException(ioe.getMessage(), ioe);
        }
        
    }

    /**
     * RETURN a JSON object formatted as String containing the attributes of a ToDo.
     * @param instanceData is a string array containing the information about the ToDo. Requires to have at least 3 elements.
     * @return the encoded string.
     */
    private String encodeToDoToJSON(String[] instanceData) {

        if (instanceData == null || instanceData.length < 3) {
            throw new IllegalArgumentException("The ToDos' string representation must have at least 3 elements.");
        }

        return "{\n" + "\t\"title\":\"" + instanceData[0] + "\",\n"
                            + "\t\"description\":\"" + instanceData[1] + "\",\n"
                            + "\t\"date\":\"" + instanceData[2] + "\",\n"
                            + encodeTags(instanceData) + "}";
    }

    /**
     * RETURN a JSON array formatted as String containing the tags of a ToDo.
     * @param todo valid instance of class ToDo.
     * @return the encoded string.
     */
    private String encodeTags(String[] instanceData) {

        if (instanceData == null) {
            throw new IllegalArgumentException("The ToDos' string representation must be at least empty.");
        }

        String encodedTags = "\t\"tags\": [ ";
        for (int i = 3; i < instanceData.length; i++) {
            encodedTags = encodedTags + "\"" + instanceData[i] + "\" ";
        }
        return encodedTags + "]\n";
    }


    /**
     * RETURN a ToDoList composed of the Objects found in the backup file
     * @return a valid ToDoList
     * @throws FileNotFoundException if it doesn't file any backup file
     * @throws IllegalBackupException if the backup file is corrupted
     */
    private void retrieveBackup() {}

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        ToDo myTodo = new ToDo("Titolo", "Descrizione", Arrays.asList("tag1", "tag2"));
        try {
            objectMapper.writeValue(new File("laMiaProva.json"), myTodo);
            System.out.println("FATTO!");
        } catch(JsonProcessingException jpe) {
            System.out.println("FUCK");
            jpe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("SUPER FUCK");
            ioe.printStackTrace();
        }
    }
}
