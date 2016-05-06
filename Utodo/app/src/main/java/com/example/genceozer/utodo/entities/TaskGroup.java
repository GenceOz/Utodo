package com.example.genceozer.utodo.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by genceozer on 05/05/16.
 */
public class TaskGroup {

    String groupTitle;
    Integer groupMemberCount;
    //List<Task> taskList;
    Integer taskGroupId;
    Date incomingDeadline;

    public TaskGroup(){

    }
    public TaskGroup(String groupTitle,Integer groupMemberCount,Date incomingDeadline, Integer taskGroupId){

        this.groupTitle = groupTitle;
        this.groupMemberCount = groupMemberCount;
        this.taskGroupId = taskGroupId;
        //this.taskList = taskList;
        this.incomingDeadline = incomingDeadline;
    }

    /*
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    */
    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public Integer getGroupMemberCount() {
        return groupMemberCount;
    }

    public void setGroupMemberCount(Integer groupMemberCount) {
        this.groupMemberCount = groupMemberCount;
    }

    public Date getIncomingDeadline() {
        return incomingDeadline;
    }

    public void setIncomingDeadline(Date incomingDeadline) {
        this.incomingDeadline = incomingDeadline;
    }
}
