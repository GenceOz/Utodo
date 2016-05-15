package com.example.genceozer.utodo.entities;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by genceozer on 05/05/16.
 */
public class Task {

    private String dueDate;
    private String title;
    private String desc;
    private boolean isDone;
    private List<SubTask> subTasks;

    public Task() {
    }

    public Task(Map<String,Object> responseMap){
        //handle date
        this.title = responseMap.get("title").toString();
        this.desc = responseMap.get("desc").toString();
        this.isDone = (Boolean)responseMap.get("status");
        this.subTasks = (List<SubTask>) responseMap.get("subTasks"); //Resolve
    }

    public Task(String dueDate, String title, String desc, boolean isDone, List<SubTask> subTasks) {
        this.dueDate = dueDate;
        this.title = title;
        this.desc = desc;
        this.isDone = isDone;
        this.subTasks = subTasks;
    }

    public String getDueDate() {

        return dueDate;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void setDueDate(String dueDate) {
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
