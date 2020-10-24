package app.src.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ToDo {

    public String title;
    public String description;
    public LocalDateTime creation;
    public ArrayList<String> tags;

    public ToDo(String title, String description, ArrayList<String> tags) {
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

    /**
     * MODIFY the title of the todo
     * @param newTitle is the new title to set. REQUIRED a not-empty string.
     */
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    /**
     * MODIFY the description of the todo
     * @param newDescription is the new description to set
     */
    void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * RETURN the title of the todo
     * @return the title of the todo as String
     */
    String getTitle() {
        return this.title;
    }

    /**
     * RETURN the description of the todo
     * @return the description of the todo as String
     */
    String getDescription() {
        return this.description;
    }

    /**
     * RETURN the date of creation of the todo
     * @return the date of creation of the todo as LocalDateTime
     */
    LocalDateTime getCreation() {
        return this.creation;
    }

    /**
     * RETURN the tags of the todo
     * @return the tags of the todo as ArrayList
     */
    ArrayList<String> getTags() {
        return this.tags;
    }

    /**
     * MODIFY the tag list of the todo by adding a tag if not already existing.
     * @param newTag is the new tag.
     */
    void addTag(String newTag) {
        if (tags.contains(newTag)) {
            System.out.println("Already existing tag");
        } else {
            tags.add(newTag);
        }
    }

    /**
     * MODIFY the tag list of the todo by deleting a tag if already existing.
     * @param deletedTag is the tag that needs to be removed.
     */
    void deleteTag(String deletedTag) {
        if (!tags.contains(deletedTag)) {
            System.out.println("Not existing tag");
        } else {
            tags.add(deletedTag);
        }
    }
}   // class ToDo
