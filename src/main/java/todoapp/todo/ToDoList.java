package todoapp.todo;

import todoapp.exceptions.EmptyFieldException;
import java.util.Collection;
import java.util.ArrayList;

class ToDoList {

    private ArrayList<ToDo> loadedToDos;
    private final String emptyTitleErrorString = "A ToDo title can't be empty";

    ToDoList() {
        this.loadedToDos = new ArrayList<ToDo>();
    }

    /**
     * MODIFY loadedToDos by adding a new ToDo instance.
     * @param toDoTitle is the title of the ToDo. Required not empty.
     * @param toDoDescription is the description of the ToDo. At least it has to be an empty String.
     * @param toDoTags is the list of tags of the ToDo. At least it has to be empty.
     * @throws EmptyStringException when the ToDo title is empty. In this case the ToDo wouldn't be added.
     */
    void addToDo(String toDoTitle, String toDoDescription, Collection<String> toDoTags) throws EmptyFieldException{

        if (toDoTitle == null || toDoTitle.isEmpty()) {
            throw new EmptyFieldException(emptyTitleErrorString);
        }

        loadedToDos.add(new ToDo(toDoTitle, toDoDescription, toDoTags));
    }
    
    /**
     * MODIFY loadedToDos by adding a new ToDo instance.
     * @param toDoTitle is the title of the ToDo. Required not empty.
     * @param toDoDescription is the description of the ToDo. At least it has to be an empty String.
     * @throws EmptyStringException when the ToDo title is empty. In this case the ToDo wouldn't be added.
     */
    void addToDo(String toDoTitle, String toDoDescription) throws EmptyFieldException {

        if (toDoTitle == null || toDoTitle.isEmpty()) {
            throw new EmptyFieldException(emptyTitleErrorString);
        }

        loadedToDos.add(new ToDo(toDoTitle, toDoDescription));
    }

    /**
     * MODIFY loadedToDos by deleting a given ToDo instance.
     * @param deletedToDo is the index of the instance that has to be removed. Required a valid instance contained in loadedToDos.
     */
    void removeToDo(int deletedToDoIndex) {

        if (deletedToDoIndex < 0 || deletedToDoIndex > loadedToDos.size()) {
            throw new IndexOutOfBoundsException();
        }

        ToDo deletedToDo = recogniseToDo(deletedToDoIndex);
        loadedToDos.remove(deletedToDo);
    }

    /**
     * MODIFY a ToDo by changing its title.
     * @param modifiedToDo is the index of the instance that has to be modified. Required a valid instance contained in loadedToDos.
     * @param newTitle is the modified title of the todo. Required a not empty String.
     * @throws EmptyFieldException when the ToDo title is empty. In this case the ToDo wouldn't be modified.
     */
    void modifyToDoTitle(int modifiedToDoIndex, String newTitle) throws EmptyFieldException {

        if (modifiedToDoIndex < 0 || modifiedToDoIndex > loadedToDos.size()) {
            throw new IndexOutOfBoundsException();
        } else if (newTitle == null || newTitle.isEmpty()) {
            throw new EmptyFieldException(emptyTitleErrorString);
        }

        ToDo modifiedToDo = recogniseToDo(modifiedToDoIndex);
        modifiedToDo.setTitle(newTitle);
    }

    /**
     * MODIFY a ToDo by changing its description.
     * @param modifiedToDo is the index of the instance that has to be modified. Required a valid instance contained in loadedToDos.
     * @param newDescription is the modified description of the todo.
     */
    void modifyToDoDescription(int modifiedToDoIndex, String newDescription){

        if (modifiedToDoIndex < 0 || modifiedToDoIndex > loadedToDos.size()) {
            throw new IndexOutOfBoundsException();
        }

        ToDo modifiedToDo = recogniseToDo(modifiedToDoIndex);
        modifiedToDo.setDescription(newDescription);
    }

    /**
     * MODIFY a ToDo by adding a tag to its list if not already in.
     * @param modifiedToDo is the intex of the instance that has to be modified. Required a valid instance contained in loadedToDos.
     * @param newTag is the new tag of the todo.
     * @throws EmptyFieldException when newTag is null or empty. In this case the tag wouldn't be added.
     */
    void addToDoTag(int modifiedToDoIndex, String newTag) throws EmptyFieldException {

        if (modifiedToDoIndex < 0 || modifiedToDoIndex > loadedToDos.size()) {
            throw new IndexOutOfBoundsException();
        } else if (newTag == null || newTag.isEmpty()) {
            throw new EmptyFieldException("A ToDo tag that is going to be added can't be empty");
        }

        ToDo modifiedToDo = recogniseToDo(modifiedToDoIndex);
        if (!modifiedToDo.getTags().contains(newTag)) {
            modifiedToDo.addTag(newTag);
        }
    }

    /**
     * MODIFY a ToDo by deleting a tag from its list if it already exists.
     * @param modifiedToDo is the index of the instance that has to be modified. Required a valid instance contained in loadedToDos.
     * @param deletedTag is the tag of the todo that has to be deleted.
     */
    void deleteToDoTag(int modifiedToDoIndex, String deletedTag) {

        if (modifiedToDoIndex < 0 || modifiedToDoIndex > loadedToDos.size()) {
            throw new IndexOutOfBoundsException();
        }

        ToDo modifiedToDo = recogniseToDo(modifiedToDoIndex);
        if (!modifiedToDo.getTags().contains(deletedTag)) {
            modifiedToDo.deleteTag(deletedTag);
        }
    }
    
    /**
     * Get a ToDo based on its index in loadedToDos
     * @param toDoIndex is the index of the desired ToDo.
     * @return the reference to the ToDo.
     */
    private ToDo recogniseToDo(int toDoIndex) {
        return loadedToDos.get(toDoIndex);
    }
    
    /**
     * Get all data about a ToDo.
     * @param toDoIndex is the index of the desired ToDo.
     * @return a string array containing, in order: the title, the description, the date of creation and the tags of the ToDo.
     */
    String[] getToDoData(int toDoIndex) {

        if (toDoIndex < 0 || toDoIndex > loadedToDos.size()) {
            throw new IndexOutOfBoundsException();
        }

        ToDo spotlightedToDo = recogniseToDo(toDoIndex);
        return spotlightedToDo.getData();
    }
    
    
    /**
     * Get the number of elements contained in loadedToDos.
     * @return the equivalent to loadedToDos' size.
     */
    int size() {
        return loadedToDos.size();
    }
}   // class ToDoList
