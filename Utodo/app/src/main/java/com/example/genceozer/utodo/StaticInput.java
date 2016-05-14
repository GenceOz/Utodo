package com.example.genceozer.utodo;

import com.example.genceozer.utodo.entities.SubTask;
import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.entities.TaskGroup;
import android.support.v7.app.AppCompatActivity;
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
//        taskGroups.add(new TaskGroup("Home", 4, cal3.getTime(), 1,null));
//        taskGroups.add(new TaskGroup("Work", 20, cal3.getTime(), 2,null));
//        taskGroups.add(new TaskGroup("Sport Buddies",5,cal3.getTime(),3,null));

        return taskGroups;
    }

    public List<Task> getTaskStatic(){

        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.YEAR,2015);
        cal3.set(Calendar.MONTH,7);
        cal3.set(Calendar.DAY_OF_MONTH, 27);

        List<Task> list = new ArrayList<Task>();
        list.add(new Task(cal3.getTime(),"Do market Shopping","Buy eggs and stuff",false,getSubTaskStatic()));
        list.add(new Task(cal3.getTime(),"Complete the assignemnet","Do the backend",true,null));

        return list;
    }

    public ArrayList<SubTask> getSubTaskStatic(){

        ArrayList<SubTask> list = new ArrayList<SubTask>();
        list.add(new SubTask("Buy Eggs","Buy nice ones",false));
        list.add(new SubTask("Buy tomato","Buy nice ones",true));

        return list;
    }
}

