package com.example.genceozer.utodo.entities;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by genceozer on 05/05/16.
 */
public class Task {

    private Date dueDate;
    private String title;
    private String description;
    private boolean isDone;
    private ArrayList<SubTask> subTasks;

    public Task() {
    }

    public Task(Date dueDate, String title, String description, boolean isDone, ArrayList<SubTask> subTasks) {
        this.dueDate = dueDate;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.subTasks = subTasks;
    }

    public Date getDueDate() {

        return dueDate;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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
