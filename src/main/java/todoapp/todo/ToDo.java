package todoapp.todo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;

import todoapp.todo.exceptions.*;

/**
 * This class provide an ADT for the ToDo object. A ToDo is like a task that 
 * have to be completed. It can have two fundamental states:
 * - done, which means that the task represented is already been completed
 * - to do, which means that the task represented isn't completed yet
 * A todo is characterized by a title, a description, a list of attributes, and 
 * a creation date and time.
 * 
 * Invariant: 
 * - must have a non-empty title
 * - can't have duplicated attributes
 */
@EqualsAndHashCode
public class ToDo {

	/**
	 * The object ToDo implementation is done with:
	 * - a String variable that represent the title
	 * - a String variable that represent the description
	 * - a LocalDateTime constant representing the creation date and time
	 * - a Attribute ArrayList that represent the list of attributes
	 * 
	 * Invariant:
	 * - the title must be not null and not empty
	 * - the creation date must be defined in the constructor and cannot be 
	 * 		changed anymore
	 * - the attribute list must not be null, must not contains duplicates, and 
	 * 		can't contains null objects
	 */
    private String title;
    private String description;
    private final LocalDateTime creation;
	private ArrayList<Attribute> attributes;

	private static final String TITLE_EXCEPTION = "A ToDo must have a title.";
	private static final String TITLE_VALID_VALUE = "A non-empty string.";

	/**
	 * Constructor that specify an attribute that the new ToDo should have.
	 * @param title: the title of the ToDo. Required to be not null and not 
	 * empty.
	 * @param description: the description of the ToDo.
	 * @param attr: a first attribute to add. Required to be not null.
	 * @throws NullPointerException when the title is null.
	 * @throws EmptyFieldException when the title is empty.
	 */
    public ToDo(String title, String description, Attribute attr) throws EmptyFieldException {

		if (title == null) {
			throw new NullPointerException();
		} else if (title.isEmpty()) {
			throw new EmptyFieldException(TITLE_EXCEPTION, "title", TITLE_VALID_VALUE);
		}

        this.title = title;
        this.description = description;
		this.creation = LocalDateTime.now();
		this.attributes = new ArrayList<Attribute>();
		if (attr != null) {
			this.attributes.add(copyAttribute(attr));
		}
	}

	/**
	 * Constructor that creates a new ToDo with the Collection of Attribute 
	 * specified. If an Attribute is present more that once, only the first 
	 * instance is considered.
	 * @param title: the title of the ToDo. Required to be not null and not 
	 * empty.
	 * @param description: the description of the ToDo.
	 * @param attributes: the Attribute Collection base for the new ToDo. Required not null.
	 * @throws NullPointerException when title is null.
	 * @throws EmptyFieldException when the title is empty.
	 */
    public ToDo(String title, String description, Collection<Attribute> attributes) throws EmptyFieldException {

		if (title == null) {
			throw new NullPointerException();
		} else if (title.isEmpty()) {
			throw new EmptyFieldException(TITLE_EXCEPTION, "title", TITLE_VALID_VALUE);
		}

        this.title = title;
        this.description = description;
		this.creation = LocalDateTime.now();
		this.attributes = new ArrayList<Attribute>();

		if (attributes != null) {
			Iterator<Attribute> attrIt = attributes.iterator();
			while (attrIt.hasNext()) {
				Attribute attr = attrIt.next();
				if (!this.attributes.contains(attr) && attr != null) {
					this.attributes.add(copyAttribute(attr));
				}
			}
		}
	}

	/**
	 * Constructor for a basic ToDo object with no attributes.
	 * @param title: the title of the ToDo. Required to be not null and not empty.
	 * @param description: the description of the ToDo.
	 * @throws NullPointerException when the title is null.
	 * @throws EmptyFieldException when the title is empty.
	 */
    public ToDo(String title, String description) throws EmptyFieldException {

		if (title == null) {
			throw new NullPointerException();
		} else if (title.isEmpty()) {
			throw new EmptyFieldException(TITLE_EXCEPTION, "title", TITLE_VALID_VALUE);
		}

        this.title = title;
        this.description = description;
        this.creation = LocalDateTime.now();
        this.attributes = new ArrayList<Attribute>();
	}

	
	
	/**
	 * Copy constructor that creates a perfect copy of the ToDo instance passed.
	 * @param td: the ToDo to copy. Required to be not null.
	 * @throws NullPointerException when td is null.
	 */
    public ToDo(ToDo td) {

		if (td == null) {
			throw new NullPointerException();
		}

        this.title = td.title;
        this.description = td.description;
        this.creation = td.creation;
		this.attributes = new ArrayList<Attribute>();

		Iterator<Attribute> it = td.attributes.iterator();
		while (it.hasNext()) {
			this.attributes.add(copyAttribute(it.next()));
		}
	}

	/**
	 * Creates a copy of the Attribute attr.
	 * @param attr: the attribute to copy. Required to be not null.
	 * @return the copy of attr.
	 */
	private Attribute copyAttribute(Attribute attr) {

		assert attr != null;

		Attribute res = null;

		if (attr instanceof Tag) {
			res =  new Tag((Tag) attr);
		} else if (attr instanceof Deadline) {
			res = new Deadline((Deadline) attr);
		}

		assert res != null;

		return res;
	}

    /**
     * Modify the title of this todo with the one indicated.
     * @param newTitle: the new title to set. Required to be not null and 
	 * non-empty string.
	 * @throws NullPointerException if newTitle is null.
	 * @throws EmptyFieldExceptions if newTitle is empty.
     */
    public void changeTitle(String newTitle) throws EmptyFieldException {

        if (newTitle == null) {
			throw new NullPointerException();
		} else if (newTitle.isEmpty()) {
			throw new EmptyFieldException(TITLE_EXCEPTION, "newTitle", TITLE_VALID_VALUE);
		}

        this.title = newTitle;
    }

    /**
     * Modify the description of this todo with the one indicated.
     * @param newDescription: the new description to set.
     */
    public void changeDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Return the title that characterize this ToDo.
     * @return the title of the todo as String.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Return the description that characterize this ToDo.
     * @return the description of the todo as String.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Return the date on which this ToDo was created.
     * @return the date as LocalDateTime.
     */
    public LocalDateTime getCreationDate() {
        return this.creation;
    }

    /**
     * Return the attributes of this ToDo as a Collection.
     * @return the attributes in a Collection.
     */
    public Collection<Attribute> getAttributes() {
		Collection<Attribute> ret = new ArrayList<Attribute>();
		Iterator<Attribute> it = this.attributes.iterator();
		while (it.hasNext()) {
			ret.add(copyAttribute(it.next()));
		}
        return ret;
    }
	
	/**
     * Modify the attribute list of this ToDo adding the attribute specified if 
	 * not already existing.
     * @param newAttr: the instance of Attribute to add. Required to be not null.
	 * @return true if the operation ends successfully, false if the Attribute 
	 * already exists in the list.
	 * @throws NullPointerException if newAttr is null.
     */
    public boolean addAttribute(Attribute newAttr) {

        if (newAttr == null) {
			throw new NullPointerException();
		}
		
		Attribute attr = copyAttribute(newAttr);

		if (this.attributes.contains(attr)) {
			return false;
		}

		return this.attributes.add(attr);
	}
	
	/**
     * Modify the attribute list of this todo deleting the attribute specified 
	 * if is already in the list.
     * @param attr: instance representing the attribute to remove.
	 * @return true if the attribute was successfully removed, false if it 
	 * wasn't removed (maybe because it wasn't inside the list).
     */
    public boolean deleteAttribute(Attribute attr) {

		if (attr == null) {
			return false;
		}

		if (!this.attributes.contains(attr)) {
			return false;
		}

		this.attributes.remove(attr);
        return true;
    }

	/**
	 * @return a standard iterator over the attributes of this ToDo instance. 
	 * The iterator is not sensible to mutations of this ToDo.
	 */
	public Iterator<Attribute> iterator() {
		return this.attributes.iterator();
	}
	
}   // class ToDo
