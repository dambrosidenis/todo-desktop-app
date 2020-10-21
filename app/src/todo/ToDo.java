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
     * @param newTitle is the new title to set
     */
    public void modifyTitle(String newTitle) {
        this.title = newTitle;
    }

}   // class ToDo