package todoapp.todo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;

import todoapp.exceptions.EmptyFieldException;
import todoapp.todo.Tag.Color;

import java.util.ArrayList;

/**
 * This class provide an ADT for the ToDo object. A ToDo object is 
 * characterized by a title, a description, a list of tags where no duplicates 
 * allowed, and a creation date and time.
 * ADT Invariants: the ToDo object must have a non-empty title and a creation 
 * date. A tag, when added, must be not empty.
 */
public class ToDo {

	/**
	 * The object ToDo implementation is done with:
	 * - a String variable that represent the title
	 * - a String variable that represent the description
	 * - a LocalDateTime constant representing the creation date and time
	 * - a Tag ArrayList that represent the list of tags
	 * 
	 * INVARIANT: the title must be not null and not empty. The creation date 
	 * must be defined in the constructor and cannot be changed anymore. The 
	 * tag list must not be null, and must not contains duplicates.
	 */
    private String title;
    private String description;
    private final LocalDateTime creation;
	private ArrayList<Tag> tags;

	/**
	 * Constructor that specify a tag that the new ToDo should have.
	 * @param title: the title of the ToDo. REQUIRED to be not null and not 
	 * empty.
	 * @param description: the description of the ToDo.
	 * @param tags: a first tag to add.
	 * @throws EmptyFieldException when the title is null or empty.
	 */
    public ToDo(String title, String description, Tag tag) throws EmptyFieldException {

		if (title == null || title.isEmpty()) {
			throw new EmptyFieldException("A ToDo must have a title.", "title", "A valid non-empty string.");
		} else if (tag == null) {
			throw new EmptyFieldException("A ToDo tag cannot be null.", "tag", "A valid non-empty Tag instance.");
		}

        this.title = title;
        this.description = description;
		this.creation = LocalDateTime.now();
		this.tags = new ArrayList<Tag>();
        this.tags.add(new Tag(tag));
	}
	
	/**
	 * Constructor that specify a tag that the new ToDo should have.
	 * @param title: the title of the ToDo. REQUIRED to be not null and not 
	 * empty.
	 * @param description: the description of the ToDo.
	 * @param tagNmae: the name of the new tag to add. REQUIRED to be not null 
	 * and not empty.
	 * @param tagColor: the name of the new tag to add. If null the new Tag 
	 * color will be the default one (blue).
	 * @throws EmptyFieldException when the title or the tagName is null or 
	 * empty.
	 */
    public ToDo(String title, String description, String tagName, Color tagColor) throws EmptyFieldException {

		if (title == null || title.isEmpty()) {
			throw new EmptyFieldException("A ToDo must have a title.", "title", "A valid non-empty string.");
		}

        this.title = title;
        this.description = description;
		this.creation = LocalDateTime.now();
		this.tags = new ArrayList<Tag>();
		try {
			Tag t;
			if (tagColor == null) {
				t = new Tag(tagName);
			} else {
				t = new Tag(tagName, tagColor);
			}
			this.tags.add(t);
		} catch (EmptyFieldException efe) {
			throw new EmptyFieldException(efe);
		}
    }

	/**
	 * Constructor for a basic ToDo object with no tags.
	 * @param title: the title of the ToDo. REQUIRED to be not null and not 
	 * empty.
	 * @param description: the description of the ToDo.
	 * @throws EmptyFieldException when the title is null or empty.
	 */
    public ToDo(String title, String description) throws EmptyFieldException {

		if (title == null || title.isEmpty() == true) {
			throw new EmptyFieldException("A ToDo must have a title.", "title", "A valid non-empty string.");
		}

        this.title = title;
        this.description = description;
        this.creation = LocalDateTime.now();
        this.tags = new ArrayList<Tag>();
	}
	
	/**
	 * Copy constructor that creates a perfect copy of the ToDo instance passed.
	 * @param td: the ToDo to copy. REQUIRED not null.
	 * @throws EmptyFieldException when td is null.
	 */
    public ToDo(ToDo td) throws EmptyFieldException {

		if (td == null) {
			throw new EmptyFieldException("A ToDo cannot be empty.", "td", "A valid ToDo instance.");
		}

        this.title = td.title;
        this.description = td.description;
        this.creation = LocalDateTime.now();
		this.tags = new ArrayList<Tag>();
		Iterator<Tag> it = td.tags.iterator();
		while (it.hasNext()) {
			this.tags.add(new Tag(it.next()));
		}
    }

    /**
     * MODIFY the title of the todo.
     * @param newTitle is the new title to set. REQUIRED a not-empty string.
     */
    void setTitle(String newTitle) {

        assert (newTitle != null && !newTitle.isEmpty()): "A ToDo title must be a valid non-empty string.";

        this.title = newTitle;
    }

    /**
     * MODIFY the description of the todo.
     * @param newDescription is the new description to set.
     */
    void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * RETURN the title of the todo.
     * @return the title of the todo as String.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * RETURN the description of the todo.
     * @return the description of the todo as String.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * RETURN the date of creation of the todo.
     * @return the date of creation of the todo as LocalDateTime.
     */
    public LocalDateTime getCreation() {
        return this.creation;
    }

    /**
     * RETURN the tags of this ToDo instance as a Collection.
     * @return the tags of the todo as Collection.
     */
    public Collection<Tag> getTags() {
		Collection<Tag> ret = new ArrayList<Tag>();
		Iterator<Tag> it = this.tags.iterator();
		while (it.hasNext()) {
			try {
				ret.add(new Tag(it.next()));
			} catch (EmptyFieldException efe) { }
		}
        return ret;
    }

    /**
     * MODIFY the tag list of this ToDo by adding a tag if not already existing.
     * @param name: the name of the new tag. REQUIRED to be not null and not 
	 * empty.
	 * @param color: the color of the new tag. If null the new Tag color will 
	 * be the default one (blue).
	 * @return true if the operation ends successfully, false if the Tag 
	 * already exists in the list.
     */
    boolean addTag(String name, Color color) {

        assert (name != null && !name.isEmpty()): "A ToDo tag that is going to be added can't be empty.";
		
		try {
			return addTag(new Tag(name, color));
		} catch (EmptyFieldException efe) {}

		return false;
	}
	
	/**
     * MODIFY the tag list of this ToDo by adding a tag if not already existing.
     * @param newTag: the instance of Tag to add. REQUIRED to be not null.
	 * @return true if the operation ends successfully, false if the Tag 
	 * already exists in the list.
     */
    boolean addTag(Tag newTag) {

        assert (newTag != null): "A ToDo tag that is going to be added can't be empty.";
		
		Tag t = null;
		try {
			t = new Tag(newTag);
		} catch (EmptyFieldException efe) {}

		// If the tag created is already in the list return
		if (tags.contains(t)) {
			return false;
		}

		return tags.add(t);
    }

    /**
     * MODIFY the tag list of the todo by deleting a tag if already existing.
     * @param tagName: name of the tag to remove. REQUIRED to be not null and 
	 * not empty.
	 * @param tagColor: color of the tag to remove. If null the new Tag color 
	 * will be the default one (blue).
	 * @return true if the tag was successfully removed, false if it wasn't 
	 * removed (maybe because it doesn't exist).
     */
    boolean deleteTag(String tagName, Color tagColor) {

		assert (tagName != null && !tagName.isEmpty()): "A ToDo tag must have a valid name.";
		
		try {
			if (tagColor == null) {
				return deleteTag(new Tag(tagName));
			} else {
				return deleteTag(new Tag(tagName, tagColor));
			}
		} catch (EmptyFieldException efe) {}

		return false;
	}
	
	/**
     * MODIFY the tag list of the todo by deleting a tag if already existing.
     * @param t: instance representing the tag to remove. REQUIRED to be not 
	 * null.
	 * @return true if the tag was successfully removed, false if it wasn't 
	 * removed (maybe because it doesn't exist).
     */
    boolean deleteTag(Tag t) {

		assert (t != null): "A ToDo valid tag must be not null.";

		if (!tags.contains(t)) {
			return false;
		}

		tags.remove(t);
        return true;
    }
    
    /**
     * RETURN all the data about the ToDo.
     * @return a string array containing, in order: the title, the description, 
	 * the date of creation and the tags of the ToDo.
     */
    public String[] getData() {
        return new String[] {
			title,
			description,
			creation.toString()
		};
	}

	/**
	 * Implement a comparison bethween two instances of ToDo.
	 * @param obj: the object to compare with.
	 * @return true if obj and this have same title, description and tags; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != ToDo.class) {
			return false;
		}

		ToDo td = (ToDo)obj;
		if (this.title.length() != td.title.length()) {
			return false;
		}

		if (this.title.compareTo(td.title) != 0) {
			return false;
		}

		if (this.description == null) {
			if (td.description != null) {
				return false;
			}
		} else {
			if (td.description == null) {
				return false;
			}

			if (this.description.length() != td.description.length()) {
				return false;
			}
	
			if (this.description.compareTo(td.description) != 0) {
				return false;
			}
		}

		if (this.tags.size() != td.tags.size()) {
			return false;
		}

		for (int i = 0; i < this.tags.size(); i++) {
			if (!this.tags.contains(td.tags.get(i))) {
				return false;
			}
		}

		return true;
	}
	
}   // class ToDo
