package com.example.genceozer.utodo.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by genceozer on 05/05/16.
 */
public class TaskGroup {

    String groupTitle;
    List<String> userList;
    List<Task> taskList;
//    Date incomingDeadline;

    public TaskGroup(){

    }
    public TaskGroup(String groupTitle,List<String> userList, List<Task> taskList){

        this.groupTitle = groupTitle;
        this.userList = userList;
        this.taskList = taskList;
//        this.incomingDeadline = incomingDeadline;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

//    public Date getIncomingDeadline() {
//        return incomingDeadline;
//    }
//
//    public void setIncomingDeadline(Date incomingDeadline) {
//        this.incomingDeadline = incomingDeadline;
//    }
}
