package app.src.todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

class ToDo {

    private String title;
    private String description;
    private LocalDateTime creation;
    private ArrayList<String> tags;

    ToDo(String title, String description, ArrayList<String> tags) {
        this.title = title;
        this.description = description;
        this.creation = LocalDateTime.now();
        this.tags = tags;
    }

    ToDo(String title, String description) {
        this.title = title;
        this.description = description;
        this.creation = LocalDateTime.now();
        this.tags = new ArrayList<String>();
    }

    /**
     * MODIFY the title of the todo
     * @param newTitle is the new title to set
     */
    void setTitle(String newTitle) {
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
     * Retrieve the title of the todo
     * @return the title of the todo as String
     */
    String getTitle() {
        return this.title;
    }

    /**
     * Retrieve the description of the todo
     * @return the description of the todo as String
     */
    String getDescription() {
        return this.description;
    }

    /**
     * Retrieve the date of creation of the todo
     * @return the date of creation of the todo as LocalDateTime
     */
    LocalDateTime getCreation() {
        return this.creation;
    }

    /**
     * Retrieve the tags of the todo
     * @return the tags of the todo as ArrayList
     */
    ArrayList<String> getTags() {
        return this.tags;
    }

}   // class ToDo
