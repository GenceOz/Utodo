package com.example.genceozer.utodo.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.StaticInput;
import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.login_register.LoginActivity;

import java.util.List;

public class TaskActivity extends AppCompatActivity {

    List<Task> tasks;
    ListView tasksListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tasksListView = (ListView) findViewById(R.id.taskList);

        StaticInput input = new StaticInput(null);
        tasks = input.getTaskStatic();

        TaskListAdapter adp = new TaskListAdapter(this,tasks);
        tasksListView.setAdapter(adp);

        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("DEV","Listener Called");
                if(tasks.get(position).getSubTasks() == null){
                    Intent i = new Intent(TaskActivity.this, LoginActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(TaskActivity.this, TaskActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.task_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()) {

            //Create task group menu item
            case R.id.mnCreateGroup:

                break;
        }

        return true;
    }
}
