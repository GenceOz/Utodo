package com.example.genceozer.utodo.subtask;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.SubTask;
import com.example.genceozer.utodo.task.TaskInfoActivity;

import java.util.ArrayList;
import java.util.List;

public class SubtaskActivity extends AppCompatActivity implements Connector.ConnectorSubtask{

    static List<SubTask> subTasks;
    static List<Object> subTaskId;
    static ListView subTasksListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtask_list);

        subTasks = new ArrayList<>();
        subTaskId = new ArrayList<>();
        subTasksListView = (ListView) findViewById(R.id.taskList);

        Connector.getInstance().getSubTask(getIntent().getStringExtra("gid"), getIntent().getStringExtra("tid"));

        SubTaskListAdapter adp = new SubTaskListAdapter(this,subTasks);
        subTasksListView.setAdapter(adp);

        subTasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("DEV", "Listener Called");
                SubTaskDialog dlg = new SubTaskDialog();
                dlg.show(getSupportFragmentManager(), "subTaskDialog");

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.subtask_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()) {

            //Create task group menu item
            case R.id.taskInfo:
                Intent i = new Intent(SubtaskActivity.this, TaskInfoActivity.class);
                startActivity(i);

                break;
        }

        return true;
    }

    public static class SubTaskDialog extends DialogFragment {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.subtask_dialog,null);
        }
    }

    @Override
    public void subTaskLoaded(SubTask s, Object sid){
        subTasks.add(s);
        subTaskId.add(sid);
        ((BaseAdapter) subTasksListView.getAdapter()).notifyDataSetChanged();
    }
}
