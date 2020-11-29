package todoapp.todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Iterator;

import todoapp.exceptions.InvalidFieldException;

import java.util.ArrayList;

public class ToDo {

    private String title;
    private String description;
    private LocalDateTime creation;
    private Collection<String> tags;

    public ToDo(String title, String description, Collection<String> tags) {
        this.title = title;
        this.description = description;
        this.creation = LocalDateTime.now();
        this.tags = tags;
    }

    public ToDo(String title, String description) {
        this.title = title;
        this.description = description;
        this.creation = LocalDateTime.now();
        this.tags = new ArrayList<String>();
    }

    public ToDo(String title, String description, String creation, Collection<String> tags) {

        try {
            this.title = title;
            this.description = description;
            this.tags = tags;
            this.creation = LocalDateTime.parse(creation);
        } catch (DateTimeParseException dtp) {
            this.creation = LocalDateTime.now();
            throw dtp;
        }

    }


    /**
     * MODIFY the title of the todo.
     * @param newTitle is the new title to set. REQUIRED a not-empty string.
     */
    void setTitle(String newTitle) {

        assert (newTitle != null && !newTitle.isEmpty()): "A ToDo title can't be empty";

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
    String getTitle() {
        return this.title;
    }

    /**
     * RETURN the description of the todo.
     * @return the description of the todo as String.
     */
    String getDescription() {
        return this.description;
    }

    /**
     * RETURN the date of creation of the todo.
     * @return the date of creation of the todo as LocalDateTime.
     */
    LocalDateTime getCreation() {
        return this.creation;
    }

    /**
     * RETURN the tags of the todo.
     * @return the tags of the todo as Collection.
     */
    Collection<String> getTags() {
        return this.tags;
    }

    /**
     * MODIFY the tag list of the todo by adding a tag if not already existing.
     * 
     * @param newTag is the new tag.
     * @throws  InvalidFieldException if it contains a character ','. In this
     *          case no tag is added.
     */
    void addTag(String newTag) throws InvalidFieldException {

        assert (newTag != null && !newTag.isEmpty()): "A ToDo tag that is going to be added can't be empty";
        assert (!tags.contains(newTag)): "Tag already exists for this ToDo";
        
        if (newTag.contains(",")) {
            throw new InvalidFieldException();
        } else {
            tags.add(newTag);
        }
    }

    /**
     * MODIFY the tag list of the todo by deleting a tag if already existing.
     * @param deletedTag is the tag that needs to be removed.
     */
    void deleteTag(String deletedTag) {

        assert (deletedTag != null && !deletedTag.isEmpty()): "A ToDo tag that is going to be added can't be empty";
        assert (tags.contains(deletedTag)): "Tag already exists for this ToDo";

        tags.remove(deletedTag);
    }
    
    /**
     * RETURN all the data about the ToDo.
     * @return a string array containing, in order: the title, the description, the date of creation and the tags of the ToDo.
     */
    String[] getData() {

        assert (!title.isEmpty()): "The ToDo has to have a valid title";
        assert (!description.isEmpty()): "The ToDo has to have a valid description";
        assert (!creation.toString().isEmpty()): "The ToDo has to have a title";

        String[] data = new String[ tags.size() + 3 ];
        data[0] = title;
        data[1] = description;
        data[2] = creation.toString();
        Iterator<String> tagIterator = tags.iterator();

        int i = 3;
        while(tagIterator.hasNext()) {
            data[i] = tagIterator.next().toString();
            i++;
        }

        return data;
    }
}   // class ToDo

