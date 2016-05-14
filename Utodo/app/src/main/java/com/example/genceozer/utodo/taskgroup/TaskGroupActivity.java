package com.example.genceozer.utodo.taskgroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.StaticInput;
import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.entities.TaskGroup;
import com.example.genceozer.utodo.task.TaskActivity;

import java.util.ArrayList;
import java.util.List;

public class TaskGroupActivity extends AppCompatActivity implements Connector.ConnectorTaskGroup {

    List<TaskGroup> taskGroups;
    ListView taskGroupListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_group);

        taskGroupListView = (ListView)findViewById(R.id.taskGroupList);

        //This part of the code is for static input for prototyping
        //Has to be replaced after prototyping
        Log.i("DEV", "burasi iyi hocam");
        Connector.getInstance().getAllGroups(Connector.getInstance().loggedUser.getUsername());

        taskGroups = new ArrayList<>();

        TaskGroupListAdapter adp = new TaskGroupListAdapter(this, taskGroups);
        taskGroupListView.setAdapter(adp);

        taskGroupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(TaskGroupActivity.this,TaskActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()) {

            //Create task group menu item
            case R.id.mnCreateGroup:

                Intent i = new Intent(this,CreateTaskGroupActivity.class);
                startActivity(i);
                break;

            //Check invitation menu item
            case R.id.mnInvitation:

                break;
        }

        return true;
    }

    @Override
    public void taskLoaded(TaskGroup taskGroup){
        taskGroups.add(taskGroup);
    }


}
