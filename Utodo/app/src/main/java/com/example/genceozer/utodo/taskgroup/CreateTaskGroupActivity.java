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
import android.widget.Toast;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.TaskGroup;
import com.example.genceozer.utodo.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskGroupActivity extends AppCompatActivity implements Connector.ConnectorCreateTaskGroupActivity {

    static EditText contributorName, taskGroupTitle;
    static List<String> contributorList;
    static ListView contributorListView;
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
                List<String> userList = new ArrayList<>();
                userList.add(Connector.getInstance().loggedUser.getUsername());
                TaskGroup newTaskGroup = new TaskGroup(taskGroupTitle.getText().toString(), userList, null);
                Connector.getInstance().createTaskGroup(newTaskGroup,contributorList);
                finish();
                break;
        }

        return true;
    }

    public void addUserToContributorList(View v){
        Connector.getInstance().checkUserExistance(contributorName.getText().toString());
    }

    public void validityChecked(Boolean isUserValid){
        if (isUserValid){
            contributorList.add(contributorName.getText().toString());
            ((BaseAdapter)contributorListView.getAdapter()).notifyDataSetChanged();
        }
        else{
//            Toast.makeText(CreateTaskGroupActivity.this, "User does not exist.", Toast.LENGTH_SHORT).show();
        }

    }
}
