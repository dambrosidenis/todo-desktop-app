package todoapp.todo;

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
     * @return true if the file is successfully created, false otherwise.
     * @throws IOException when an error occur while creating the file, in this case nothing will be created.
     */
    public boolean create() throws IOException {
        boolean result = false;

        try {
            result = file.createNewFile();
        } catch (IOException ioe) {
            throw new IOException("An error occurred while creating the file", ioe);
        }

        return result;
    }

    /**
     * Write data to the file if it exists.
     * @param data: data to save.
     * @return true if the data was correctly saved, false otherwise.
     * @throws IOException when an error occur while writing the data, in this case nothing will be saved into the file.
     */
    public boolean save(String data) throws IOException {
        if (this.alreadyExist()) {
            FileWriter fileOut = null;
            try {
                fileOut = new FileWriter(file);
                fileOut.write(data);
            } catch (IOException ioe) {
                throw new IOException("An error occurred while writing the file", ioe);
            } finally {
                if (fileOut != null) {
                    fileOut.close();
                }
            }

            if (fileOut != null) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Read the data from the file if it exists.
     * @return the data read from the file if it exists, return null otherwise.
     * @throws IOException when an error occur while reading the data, in this case nothing will be read by the file.
     */
    public String load() throws IOException {
        StringBuilder dataRead = null;

        if (this.alreadyExist()) {
            Scanner fileIn = null;
            try {
                fileIn = new Scanner(file);
                dataRead = new StringBuilder();
                while (fileIn.hasNextLine()) {
                    dataRead.append(fileIn.nextLine());
                }
            }
            catch (IOException ioe) {
                throw new IOException("An error occurred while reading the file", ioe);
            } finally {
                if (fileIn != null) {
                    fileIn.close();
                }
            }
        }
        if (dataRead != null) {
            return dataRead.toString();
        }
        return null;
    }
}
