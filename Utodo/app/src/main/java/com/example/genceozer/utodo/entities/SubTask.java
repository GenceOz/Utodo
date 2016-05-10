package com.example.genceozer.utodo.entities;

/**
 * Created by genceozer on 10/05/16.
 */
public class SubTask {
    private String title;
    private String description;
    private boolean isDone;

    public SubTask() {
    }

    public SubTask(String title, String description, boolean isDone) {

        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
