package todoapp.todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import todoapp.exceptions.BackupFailedException;
import todoapp.exceptions.EmptyFieldException;

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

        int listLength = currentToDoList.size();
        StringBuilder dataReadyToBackup = new StringBuilder(String.valueOf(listLength) + '\n');

        Iterator<ToDo> toDoIterator = currentToDoList.iterator();
        ToDo backupToDo;
        while(toDoIterator.hasNext()) {

            backupToDo = toDoIterator.next();
            dataReadyToBackup.append( sanificate(backupToDo.getData()[0], false) + '\n' );
            dataReadyToBackup.append( sanificate(backupToDo.getData()[1], false) + '\n' );
            dataReadyToBackup.append( sanificate(backupToDo.getData()[2], false) + '\n' );

            for (int i = 3; i < backupToDo.getData().length; i++) {
                dataReadyToBackup.append( sanificate(backupToDo.getData()[i], false) + ", " );
            }
            dataReadyToBackup.append('\n');
        }

        FileIO fileHandler = new FileIO("myBackup.bak");

        try {
            fileHandler.create();
            fileHandler.save(dataReadyToBackup.toString());
        } catch (IOException ioe) {
            throw new BackupFailedException(ioe.getMessage(), ioe);
        }
        
    }

   


    /**
     * RETURN a ToDoList composed of the Objects found in the backup file
     * If an EmptyFieldException is catch it returns NULL.
     * 
     * @return a valid ToDoList or null if
     * @throws  IOException if it doesn't find any backup file or the file is
     *          is corrupted
     */
    private ToDoList retrieveBackup() throws IOException {

        try {

            ToDoList retrievedToDoList = new ToDoList();
            FileIO fileHandler = new FileIO("myBackup.bak");
            String[] backupDataFromFile = fileHandler.load().split("\\r?\\n");
            int numberOfToDos = Integer.valueOf(backupDataFromFile[0]);
            int i = 1;
            for (int iterator = 0; iterator < numberOfToDos; iterator++) {
                retrievedToDoList.addToDo(  sanificate(backupDataFromFile[i], true),
                                            sanificate(backupDataFromFile[i+1], true),
                                            sanificate(backupDataFromFile[i+3], true),
                                            new ArrayList<String>(Arrays.asList(sanificate(backupDataFromFile[i+4], true).split(", "))));
                i = i + 4;
            }
            return retrievedToDoList;

        } catch (IOException ioe) {
            throw ioe;
        } catch (EmptyFieldException efe) {
            return null;
        }
    }

    /**
     * Sanificates a String, either substituting each character '\n'
     * present in dirtyString with character 0 in case of encoding 
     * or vice-versa in case of decoding. Returns an empty String if
     * either dirtyString or encoding is NULL.
     * 
     * @param dirtyString the String to sanificate
     * @param   encoding the direction of encoding:
     *          true = encoding,
     *          false = decoding;
     * @return the sanificated String
     */
    private String sanificate (String dirtyString, boolean encoding) {
        
        try {
            if (encoding) {
                return dirtyString.replace('\n', (char) 0);
            } else {
                return dirtyString.replace((char) 0, '\n');
            }
        } catch (NullPointerException npe) {
            return new String();
        }
    }
    
} //class Backup

