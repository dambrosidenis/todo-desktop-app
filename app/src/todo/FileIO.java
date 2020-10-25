package app.src.todo;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

class FileIO {

    private File file;

    public FileIO(String pathName) {
        file = new File(pathName);
    }

    /**
     * Check whether the file already exists or not.
     * @return true if the file exists, false if it doesn't.
     */
    public boolean alreadyExist() {
        return file.exists();
    }

    /**
     * Create a new file if it does not already exist.
     */
    public void create() {
        try {
            if (!file.createNewFile()) {
                System.out.println("The file already exists.");
            }
        } catch (IOException e) {
            printError(e);
        }
    }

    /**
     * Write data to the file if it exists.
     * @param data: data to save.
     */
    public void save(String data) {
        if (file.exists()) {
            try {
                FileWriter fileOut = new FileWriter(file);
                fileOut.write(data);
                fileOut.close();
            } catch (IOException e) {
                printError(e);
            }
        }
    }

    /**
     * Read the data from the file if it exists.
     * @return the data read from the file.
     */
    public String load() {
        String dataRead = "";
        if (this.alreadyExist()) {
            try {
                Scanner fileIn = new Scanner(file);
                while (fileIn.hasNextLine()) {
                    dataRead += fileIn.nextLine();
                }
                fileIn.close();
            }
            catch (IOException e) {
                printError(e);
            }
        }
        return dataRead;
    }

    /**
     * Print an error to the console.
     * @param e: the exception thrown.
     */
    private void printError(IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
}
