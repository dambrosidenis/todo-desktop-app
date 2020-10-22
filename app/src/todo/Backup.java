package app.src.todo;

class Backup {

    /**
     * RETURN a JSON object formatted as String containing the instances of a ToDoList
     * @param todo valid instance of class ToDoList
     * @return the encoded string
     */
    static String backupData(ToDoList currentToDoList) {
        String dataReadyToBackup = "{\n";
        int listLength = currentToDoList.size();
        ToDo instance;
        for (int i = 0; i < listLength; i++) {
            instance = currentToDoList.getToDo(i);
            dataReadyToBackup = dataReadyToBackup + encodeToDoToJSON(instance);
        }
        return dataReadyToBackup + "}";

    }

    /**
     * RETURN a JSON object formatted as String containing the attributes of a ToDo
     * @param todo valid instance of class ToDo
     * @return the encoded string
     */
    private static String encodeToDoToJSON(ToDo instance) {
        String encodedToDo = "{\n" + "\t\"title\":\"" + instance.getTitle() +"\",\n" + "\t\"description\":\"" + instance.getDescription() +"\",\n" + encodeTags(instance) + "}";
        return encodedToDo;
    }

    /**
     * RETURN a JSON array formatted as String containing the tags of a ToDo
     * @param todo valid instance of class ToDo
     * @return the encoded string
     */
    private static String encodeTags(ToDo todo) {
        String encodedTags = "\t\"tags\": [ ";
        int listLength = todo.getTags().size();
        for (int i = 0; i < listLength; i++) {
            encodedTags = encodedTags + "\"" + todo.getTags().get(i) + "\" ";
        }
        return encodedTags + "]\n";
    }
}
