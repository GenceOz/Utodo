package com.example.genceozer.utodo.entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by genceozer on 05/05/16.
 */
public class Task {

    private Date dueDate;
    private String title;
    private String desc;
    private boolean isDone;
    private ArrayList<SubTask> subTasks;

    public Task() {
    }

    public Task(Map<String,Object> responseMap){
        //handle date
        this.title = responseMap.get("title").toString();
        this.desc = responseMap.get("desc").toString();
        this.isDone = (Boolean)responseMap.get("status");
        this.subTasks = (ArrayList<SubTask>) responseMap.get("subTasks"); //Resolve
    }

    public Task(Date dueDate, String title, String desc, boolean isDone, ArrayList<SubTask> subTasks) {
        this.dueDate = dueDate;
        this.title = title;
        this.desc = desc;
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
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
