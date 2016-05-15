package com.example.genceozer.utodo.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.login_register.LoginActivity;
import com.example.genceozer.utodo.subtask.SubtaskActivity;

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity implements Connector.ConnectorTask {

    static List<Task> tasks;
    static List<Object> taskId;
    static ListView tasksListView;
    static String gid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tasksListView = (ListView) findViewById(R.id.taskList);

        tasks = new ArrayList<>();
        taskId = new ArrayList<>();

        Intent i = getIntent();
        gid = i.getStringExtra("gid");

        Connector.getInstance().getTasks(getIntent().getStringExtra("gid"));

        
        Connector.getInstance().getTasks(getIntent().getStringExtra("gid"));

        TaskListAdapter adp = new TaskListAdapter(this,tasks);
        tasksListView.setAdapter(adp);

        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(TaskActivity.this, SubtaskActivity.class);

                i.putExtra("tid",taskId.get(position).toString());
                i.putExtra("gid",gid);
                startActivity(i);

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
            case R.id.taskInfo:
                    Intent i = getIntent();
                    String gid = i.getStringExtra("gid");
                    i = new Intent(TaskActivity.this,CreateTasks.class);
                    i.putExtra("gid", gid);
                    startActivity(i);

                break;
        }

        return true;
    }

    @Override
    public void taskLoaded(Task t, Object tid){
        tasks.add(t);
        taskId.add(tid);
        ((BaseAdapter) tasksListView.getAdapter()).notifyDataSetChanged();
    }
}
