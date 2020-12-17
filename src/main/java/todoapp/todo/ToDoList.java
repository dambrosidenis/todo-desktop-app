package todoapp.todo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import todoapp.todo.exceptions.*;

/**
 * This class provide an ADT for a list of ToDo.
 * INVARIANT: the list doesn't contain duplicates.
 */
class ToDoList {

	/**
	 * The object ToDoList implementation is done with a HashMap with:
	 * - key: the Integer returned by the method ToDo.hashCode()
	 * - value: the ToDo instance
	 * INVARIANT: loadedToDos must be not null, must not contains null objects 
	 * or duplicates.
	 * NOTE: two ToDos with same title, description and tags aren't equal 
	 * because the creation time is never the same
	 */
    private Map<ToDo, ToDo> loadedToDos;

	/**
	 * Constructor that creates a new empty list.
	 */
    public ToDoList() {
		this.loadedToDos = new HashMap<ToDo, ToDo>();
	}
	
	/**
	 * Constructor that creates a new list with the ToDo specified.
	 * @param todo: todo to add to the new list. REQUIRED not null.
	 * @throws NullPointerException when todo is null.
	 */
    public ToDoList(ToDo todo) {
		if (todo == null) {
			throw new NullPointerException();
		}

		this.loadedToDos = new HashMap<ToDo, ToDo>();
		loadedToDos.put(todo, todo);
	}
	
	/**
	 * Copy constructor that creates a perfect copy of the ToDoList instance 
	 * passed.
	 * @param todolist: the ToDoList to copy. REQUIRED not null.
	 * @throws NullPointerException when todolist is null.
	 */
    public ToDoList(ToDoList todolist) {
		if (todolist == null) {
			throw new NullPointerException();
		}
		
		this.loadedToDos = new HashMap<ToDo, ToDo>();
		this.loadedToDos.putAll(todolist.loadedToDos);
	}
	
	/**
	 * Constructor that creates a new list from the Collection of ToDo 
	 * specified. If a ToDo is present more that once, only the first instance 
	 * is considered.
	 * @param todoColl: the ToDo Collection base for the new list. REQUIRED not 
	 * null.
	 * @throws NullPointerException when todoColl is null.
	 */
    public ToDoList(Collection<ToDo> todoColl) {
		if (todoColl == null) {
			throw new NullPointerException();
		}
		
		this.loadedToDos = new HashMap<ToDo, ToDo>();
		Iterator<ToDo> todoIt = todoColl.iterator();
		while (todoIt.hasNext()) {
			ToDo todo = new ToDo(todoIt.next());
			if (!this.loadedToDos.containsKey(todo)) {
				this.loadedToDos.put(todo, todo);
			}
		}
	}
	
	/**
     * Check whether the ToDo passed is present in the list or not.
     * @param todo: the ToDo instance to search in the list. REQUIRED to be not 
	 * null.
     * @return true if the ToDo is in, false otherwise.
     * @throws NullPointerException when todo is null.
     */
    public boolean contains(ToDo todo) {

        if (todo == null) {
            throw new NullPointerException();
        }

        return this.loadedToDos.containsKey(todo);
    }
	
	/**
     * MODIFY this by adding a new ToDo to the list (if todo not already exists)
     * @param newToDo: the ToDo instance to add to the list. REQUIRED to be not 
	 * null.
     * @return true if the ToDo is added, false if todo was already present in 
	 * the list.
     * @throws NullPointerException when todo is null. In this case nothing is 
	 * modified.
     */
    public boolean addToDo(ToDo newToDo) {

        if (newToDo == null) {
            throw new NullPointerException();
        }

        if (this.contains(newToDo)) {
			return false;
		} else {
			this.loadedToDos.put(newToDo, new ToDo(newToDo));
			return true;
		}
    }
	
	/**
     * MODIFY this by deleting the ToDo specified from the list (if todo 
	 * already exists in the list).
     * @param todo: instance of ToDo that has to be removed from the list. 
	 * REQUIRED to be not null.
	 * @return true if todo is correctly removed, false if todo wasn't already 
	 * present in the list.
	 * @throws NullPointerException if todo is null. In this case nothing is 
	 * modified.
     */
    public boolean removeToDo(ToDo todo) {

        if (todo == null) {
            throw new NullPointerException();
        }

		if (!this.contains(todo)) {
			return false;
		}

        return this.loadedToDos.remove(todo, todo);
    }
	
	/**
     * MODIFY this by changing the title of the ToDo specified (if todo already 
	 * exists in the list). The ToDo passed wouldn't be modified.
     * @param todo: instance of ToDo that has to be modified in the list. 
	 * REQUIRED to be not null.
     * @param newTitle: the new title. REQUIRED to be not null and not empty.
	 * @return a copy of the modified ToDo if the ToDo was present in the list 
	 * and was successfully modified, or null if todo is not present in the 
	 * list.
	 * @throws NullPointerException when newTitle or todo is null. In this case 
	 * nothing is modified.
     * @throws EmptyFieldException when newTitle is empty. In this case nothing 
	 * is modified.
     */
    public ToDo modifyToDoTitle(ToDo todo, String newTitle) throws EmptyFieldException {

        if (newTitle == null || todo == null) {
			throw new NullPointerException();
		} else if (newTitle.isEmpty()) {
            throw new EmptyFieldException("A ToDo title can't be empty.", "newTitle", "A non-empty string.");
        }

		if (!this.contains(todo)) {
			return null;
		} else {
			this.removeToDo(todo);
			ToDo newTodo = new ToDo(todo);
			newTodo.changeTitle(newTitle);
			this.addToDo(newTodo);
			return new ToDo(newTodo);
		}
    }
	
	/**
     * MODIFY this by changing the description of the ToDo specified (if todo 
	 * already exists in the list). The ToDo passed wouldn't be modified.
     * @param todo: instance of ToDo that has to be modified in the list. 
	 * REQUIRED to be not null.
     * @param newDescription: the new description.
	 * @return a copy of the modified ToDo if the ToDo was present in the list 
	 * and was successfully modified, or null if todo is not present in the 
	 * list.
	 * @throws NullPointerException when todo is null. In this case nothing 
	 * is modified.
     */
    public ToDo modifyToDoDescription(ToDo todo, String newDescription){
        if (todo == null) {
			throw new NullPointerException();
		}

		if (!this.contains(todo)) {
			return null;
		} else {
			this.removeToDo(todo);
			ToDo newTodo = new ToDo(todo);
			newTodo.changeDescription(newDescription);
			this.addToDo(newTodo);
			return new ToDo(newTodo);
		}
    }
	
	/**
	 * MODIFY this by adding a new Tag to the ToDo specified (if todo already 
	 * exists in the list). If the ToDo already has that Tag, nothing will be 
	 * modified. The ToDo passed wouldn't be modified in any case.
     * @param todo: instance of ToDo that has to be modified in the list. 
	 * REQUIRED to be not null.
	 * @param newTag: the tag to add. REQUIRED to be not null.
	 * @return a copy of the modified ToDo if the Tag is successfully added, 
	 * the same ToDo if the Tag is already present, or null if todo is not 
	 * present in the list.
	 * @throws NullPointerException when todo or newTag is null. In this case 
	 * nothing is modified.
     */
    public ToDo addToDoTag(ToDo todo, Tag newTag) {

        if (todo == null || newTag == null) {
			throw new NullPointerException();
		}

		if (!this.contains(todo)) {
			return null;
		} else {
			ToDo newTodo = new ToDo(todo);
			if(newTodo.addAttribute(newTag)) {
				this.removeToDo(todo);
				this.addToDo(newTodo);
				return new ToDo(newTodo);
			} else {
				return todo;
			}
		}
    }
	
	/**
	 * MODIFY this by deleting a new Tag to the ToDo specified (if todo already 
	 * exists in the list). If the ToDo doesn't have that Tag, nothing will be 
	 * modified. The ToDo passed wouldn't be modified in any case.
     * @param todo: instance of ToDo that has to be modified in the list. 
	 * REQUIRED to be not null.
	 * @param newTag: the tag to delete. REQUIRED to be not null.
	 * @return a copy of the modified ToDo if the Tag is successfully deleted, 
	 * the same ToDo if the Tag wasn't already present, or null if todo is not 
	 * present in the list.
	 * @throws NullPointerException when todo or newTag is null. In this case 
	 * nothing is modified.
     */
    public ToDo deleteToDoTag(ToDo todo, Tag tag) {

        if (todo == null || tag == null) {
			throw new NullPointerException();
		}

		if (!this.contains(todo)) {
			return null;
		} else {
			ToDo newTodo = new ToDo(todo);
			if(newTodo.removeAttribute(tag)) {
				this.removeToDo(todo);
				this.addToDo(newTodo);
				return new ToDo(newTodo);
			} else {
				return todo;
			}
		}
    }
    
    /**
     * Get a copy of all the ToDos of the list.
     * @return a Collection with all the ToDo. If the list is empty the 
	 * Collection returned will be empty.
     */
    public Collection<ToDo> getData() {
		Collection<ToDo> res = new ArrayList<ToDo>();
		Iterator<ToDo> it = this.loadedToDos.values().iterator();
		
		while (it.hasNext()) {
			res.add(new ToDo(it.next()));
		}

        return res;
    }
    
    
    /**
     * Get the number of ToDo contained in the list.
     * @return number of ToDo.
     */
    public int size() {
        return this.loadedToDos.size();
	}
	
	/**
	 * @return a standard iterator over the todos of this list instance. The 
	 * iterator is not sensible to mutations of this list.
	 */
	public Iterator<ToDo> iterator() {
		return this.loadedToDos.values().iterator();
	}

}   // class ToDoList
