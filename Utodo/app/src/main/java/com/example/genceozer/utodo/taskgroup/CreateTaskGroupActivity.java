package com.example.genceozer.utodo.taskgroup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CreateTaskGroupActivity extends AppCompatActivity {

    List<User> contributorList;
    ListView contributorListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task_group);

        contributorListView = (ListView)findViewById(R.id.contributorListView);

        //This part of the code is for static input for prototyping
        //Has to be replaced after prototyping

        contributorList = new ArrayList<>();
        contributorList.add(new User(1,"Gence","1","gence@gmail.com"));
        contributorList.add(new User(1, "Alp", "2", "alp@gmail.com"));


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
            case R.id.mnCreateGroup:

                Intent i = new Intent(this,TaskGroupActivity.class);
                startActivity(i);
                break;
        }

        return true;
    }

    public void addUserToContributorList(View v){
        contributorList.add(new User(1,"Gence","1","gence@gmail.com"));
        ((BaseAdapter)contributorListView.getAdapter()).notifyDataSetChanged();

    }
}
