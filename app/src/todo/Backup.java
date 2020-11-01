 

class Backup {

    /**
     * RETURN a JSON object formatted as String containing the instances of a ToDoList
     * @param todo valid instance of class ToDoList
     * @return the encoded string
     */
    static void backupData(ToDoList currentToDoList) {
        String dataReadyToBackup = "{\n";
        int listLength = currentToDoList.size();
        for (int i = 0; i < listLength; i++) {
            dataReadyToBackup = dataReadyToBackup + encodeToDoToJSON(currentToDoList.getToDoData(i));
        }
        dataReadyToBackup = dataReadyToBackup + "\n}";
        FileIO fileHandler = new FileIO("myBackup.bak");
        fileHandler.create();
        fileHandler.save(dataReadyToBackup);
    }

    /**
     * RETURN a JSON object formatted as String containing the attributes of a ToDo
     * @param instanceData is a string array containing the information about the ToDo. Requires to have at least 3 elements.
     * @return the encoded string
     */
    private static String encodeToDoToJSON(String[] instanceData) {
        String encodedToDo = "{\n" + "\t\"title\":\"" + instanceData[0] +"\",\n"
                            + "\t\"description\":\"" + instanceData[1] + "\",\n"
                            + "\t\"date\":\"" + instanceData[2] + "\",\n"
                            + encodeTags(instanceData) + "}";
        return encodedToDo;
    }

    /**
     * RETURN a JSON array formatted as String containing the tags of a ToDo
     * @param todo valid instance of class ToDo
     * @return the encoded string
     */
    private static String encodeTags(String[] instanceData) {
        String encodedTags = "\t\"tags\": [ ";
        for (int i = 3; i < instanceData.length; i++) {
            encodedTags = encodedTags + "\"" + instanceData[i] + "\" ";
        }
        return encodedTags + "]\n";
    }
}
