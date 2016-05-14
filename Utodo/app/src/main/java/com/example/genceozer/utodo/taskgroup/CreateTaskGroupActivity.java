package com.example.genceozer.utodo.taskgroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.TaskGroup;
import com.example.genceozer.utodo.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskGroupActivity extends AppCompatActivity {

    EditText contributorName, taskGroupTitle;
    List<String> contributorList;
    ListView contributorListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task_group);

        contributorListView = (ListView)findViewById(R.id.contributorListView);
        contributorName = (EditText)findViewById(R.id.contributorText);
        taskGroupTitle = (EditText)findViewById(R.id.taskGroupTitleText);

        contributorList = new ArrayList<>();

        CreateTaskGroupListAdapter adp = new CreateTaskGroupListAdapter(this, contributorList);
        contributorListView.setAdapter(adp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.group_create_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()) {

            //Create finish forming group menu item
            case R.id.finishButton:
                TaskGroup newTaskGroup = new TaskGroup(taskGroupTitle.getText().toString(), contributorList, null);
                Connector.getInstance().createTaskGroup(newTaskGroup);
                Intent i = new Intent(this,TaskGroupActivity.class);
                startActivity(i);
                break;
        }

        return true;
    }

    public void addUserToContributorList(View v){
        //Check validity
        contributorList.add(contributorName.getText().toString());
        ((BaseAdapter)contributorListView.getAdapter()).notifyDataSetChanged();

    }
}
