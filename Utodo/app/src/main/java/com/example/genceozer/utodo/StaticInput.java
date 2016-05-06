package com.example.genceozer.utodo;

import com.example.genceozer.utodo.entities.TaskGroup;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by genceozer on 05/05/16.
 */

//This class acts as an filler for background servies
    //Should be removed upon implementation of background services
public class StaticInput {

   private static List<TaskGroup> taskGroups;

    public StaticInput(){

    }
    public StaticInput(List<TaskGroup> taskGroups) {
        this.taskGroups = taskGroups;
    }

    public List<TaskGroup> getTaskGroupStatic(){

        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.YEAR,2015);
        cal3.set(Calendar.MONTH,7);
        cal3.set(Calendar.DAY_OF_MONTH, 27);

        taskGroups = new ArrayList<>();
        taskGroups.add(new TaskGroup("Home", 4, cal3.getTime(), 1));
        taskGroups.add(new TaskGroup("Work", 20, cal3.getTime(), 2));
        taskGroups.add(new TaskGroup("Sport Buddies",5,cal3.getTime(),3));
        taskGroups.add(new TaskGroup("Home",4,cal3.getTime(),1));
        taskGroups.add(new TaskGroup("Work",20,cal3.getTime(),2));
        taskGroups.add(new TaskGroup("Sport Buddies",5,cal3.getTime(),3));
        taskGroups.add(new TaskGroup("Home",4,cal3.getTime(),1));
        taskGroups.add(new TaskGroup("Work",20,cal3.getTime(),2));
        taskGroups.add(new TaskGroup("Sport Buddies",5,cal3.getTime(),3));
        taskGroups.add(new TaskGroup("Home",4,cal3.getTime(),1));
        taskGroups.add(new TaskGroup("Work",20,cal3.getTime(),2));
        taskGroups.add(new TaskGroup("Sport Buddies",5,cal3.getTime(),3));
        taskGroups.add(new TaskGroup("Home",4,cal3.getTime(),1));
        taskGroups.add(new TaskGroup("Work",20,cal3.getTime(),2));
        taskGroups.add(new TaskGroup("Sport Buddies",5,cal3.getTime(),3));
        taskGroups.add(new TaskGroup("Home",4,cal3.getTime(),1));
        taskGroups.add(new TaskGroup("Work",20,cal3.getTime(),2));
        taskGroups.add(new TaskGroup("Sport Buddies",5,cal3.getTime(),3));

        return taskGroups;
    }
}
