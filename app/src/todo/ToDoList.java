import java.util.Collection;
import java.util.ArrayList;

class ToDoList {

    private ArrayList<ToDo> loadedToDos;

    ToDoList() {
        this.loadedToDos = new ArrayList<ToDo>();
    }

    /**
     * MODIFY loadedToDos by adding a new ToDo instance.
     * @param toDoTitle is the title of the ToDo. REQUIRED not empty.
     * @param toDoDescription is the description of the ToDo. At least it has to be an empty String.
     * @param toDoTags is the list of tags of the ToDo. At least it has to be empty.
     */
    void addToDo(String toDoTitle, String toDoDescription, Collection<String> toDoTags){
        loadedToDos.add(new ToDo(toDoTitle, toDoDescription, toDoTags));
    }
    
    /**
     * MODIFY loadedToDos by adding a new ToDo instance.
     * @param toDoTitle is the title of the ToDo. REQUIRED not empty.
     * @param toDoDescription is the description of the ToDo. At least it has to be an empty String.
     */
    void addToDo(String toDoTitle, String toDoDescription){
        loadedToDos.add(new ToDo(toDoTitle, toDoDescription));
    }

    /**
     * MODIFY loadedToDos by deleting a given ToDo instance.
     * @param deletedToDo is the index of the instance that has to be removed. REQUIRED a valid instance contained in loadedToDos.
     */
    void removeToDo(int deletedToDoIndex){
        ToDo deletedToDo = recogniseToDo(deletedToDoIndex);
        loadedToDos.remove(deletedToDo);
    }

    /**
     * MODIFY a ToDo by changing its title.
     * @param modifiedToDo is the index of the instance that has to be modified. REQUIRED a valid instance contained in loadedToDos.
     * @param newTitle is the modified title of the todo. REQUIRED a not empty String.
     */
    void modifyToDoTitle(int modifiedToDoIndex, String newTitle){
        ToDo modifiedToDo = recogniseToDo(modifiedToDoIndex);
        modifiedToDo.setTitle(newTitle);
    }

    /**
     * MODIFY a ToDo by changing its description.
     * @param modifiedToDo is the index of the instance that has to be modified. REQUIRED a valid instance contained in loadedToDos.
     * @param newDescription is the modified description of the todo. REQUIRED a not empty String.
     */
    void modifyToDoDescription(int modifiedToDoIndex, String newDescription){
        ToDo modifiedToDo = recogniseToDo(modifiedToDoIndex);
        modifiedToDo.setDescription(newDescription);
    }

    /**
     * MODIFY a ToDo by adding a tag to its list.
     * @param modifiedToDo is the intex of the instance that has to be modified. REQUIRED a valid instance contained in loadedToDos.
     * @param newTag is the new tag of the todo.
     */
    void addToDoTag(int modifiedToDoIndex, String newTag) {
        ToDo modifiedToDo = recogniseToDo(modifiedToDoIndex);
        modifiedToDo.addTag(newTag);
    }

    /**
     * MODIFY a ToDo by deleting a tag from its list.
     * @param modifiedToDo is the index of the instance that has to be modified. REQUIRED a valid instance contained in loadedToDos.
     * @param deletedTag is the tag of the todo that has to be deleted.
     */
    void deleteToDoTag(int modifiedToDoIndex, String deletedTag) {
        ToDo modifiedToDo = recogniseToDo(modifiedToDoIndex);
        modifiedToDo.deleteTag(deletedTag);
    }
    
    /**
     * RETURN a ToDo based on its index in loadedToDos
     * @param toDoIndex is the index of the desired ToDo.
     * @return is the reference to the ToDo.
     */
    private ToDo recogniseToDo(int toDoIndex) {
        return loadedToDos.get(toDoIndex);
    }
    
    /**
     * RETURN all data about a ToDo.
     * @param spotlightedToDoIndex is the index of the desired ToDo.
     * @return a string array containing, in order: the title, the description, the date of creation and the tags of the ToDo.
     */
    String[] getToDoData(int spotlightedToDoIndex) {
        ToDo spotlightedToDo = recogniseToDo(spotlightedToDoIndex);
        return spotlightedToDo.getData();
    }
    
    
    /**
     * RETURN the number of elements contained in loadedToDos.
     * @return is an integer equivalent to loadedToDos' size.
     */
    int size() {
        return loadedToDos.size();
    }
}   // class ToDoList
