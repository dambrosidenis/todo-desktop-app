package app.src.todo;

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
     * MODIFY loadedToDos by deleting a given ToDo instance.
     * @param deletedToDo is the reference to the instance that has to be removed. REQUIRED a valid instance contained in loadedToDos.
     */
    void removeToDo(ToDo deletedToDo){
        loadedToDos.remove(deletedToDo);
    }

    /**
     * MODIFY a ToDo by changing its title.
     * @param modifiedToDo is the reference to the instance that has to be modified. REQUIRED a valid instance contained in loadedToDos.
     * @param newTitle is the modified title of the todo. REQUIRED a not empty String.
     */
    void modifyToDoTitle(ToDo modifiedToDo, String newTitle){
        modifiedToDo.setTitle(newTitle);
    }

    /**
     * MODIFY a ToDo by changing its description.
     * @param modifiedToDo is the reference to the instance that has to be modified. REQUIRED a valid instance contained in loadedToDos.
     * @param newDescription is the modified description of the todo. REQUIRED a not empty String.
     */
    void modifyToDoDescription(ToDo modifiedToDo, String newDescription){
        modifiedToDo.setDescription(newDescription);
    }

    /**
     * MODIFY a ToDo by adding a tag to its list.
     * @param modifiedToDo is the reference to the instance that has to be modified. REQUIRED a valid instance contained in loadedToDos.
     * @param newTag is the new tag of the todo.
     */
    void addToDoTag(ToDo modifiedToDo, String newTag) {
        modifiedToDo.addTag(newTag);
    }

    /**
     * MODIFY a ToDo by deleting a tag from its list.
     * @param modifiedToDo is the reference to the instance that has to be modified. REQUIRED a valid instance contained in loadedToDos.
     * @param deletedTag is the tag of the todo that has to be deleted.
     */
    void deleteToDoTag(ToDo modifiedToDo, String deletedTag) {
        modifiedToDo.deleteTag(deletedTag);
    }
    
}   // class ToDoList
