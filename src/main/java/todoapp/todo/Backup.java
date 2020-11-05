package todoapp.todo;

import java.io.IOException;
import todoapp.exceptions.BackupFailedException;

class Backup {

    /**
     * Save the data into the file myBackup.bak.
     * @param currentToDoList valid instance of class ToDoList.
     * @throws BackupFailedException when the backup of the ToDoList fail.
     */
    static void backupData(ToDoList currentToDoList) throws BackupFailedException {

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
            throw new BackupFailedException(ioe.getMessage());
        }
        
    }

    /**
     * RETURN a JSON object formatted as String containing the attributes of a ToDo.
     * @param instanceData is a string array containing the information about the ToDo. Requires to have at least 3 elements.
     * @return the encoded string.
     */
    private static String encodeToDoToJSON(String[] instanceData) {

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
    private static String encodeTags(String[] instanceData) {

        if (instanceData == null) {
            throw new IllegalArgumentException("The ToDos' string representation must be at least empty.");
        }

        String encodedTags = "\t\"tags\": [ ";
        for (int i = 3; i < instanceData.length; i++) {
            encodedTags = encodedTags + "\"" + instanceData[i] + "\" ";
        }
        return encodedTags + "]\n";
    }
}
