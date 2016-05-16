package com.example.genceozer.utodo.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;

public class TaskInfoActivity extends AppCompatActivity implements Connector.ConnectorTaskInfoActivity {

    TextView taskTitle,taskDesc;
    Button deleteTask, finilazeTask;
    static String taskId;
    static String groupId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);
        Connector.getInstance().infoActivityDelegate = this;

        taskTitle = (TextView)findViewById(R.id.taskInfoTitle);
        taskDesc = (TextView) findViewById(R.id.taskInfoDesc);
        deleteTask = (Button) findViewById(R.id.taskInfoDeleteButton);
        finilazeTask = (Button) findViewById(R.id.taskInfoFinilazeButton);

        taskId = getIntent().getStringExtra("tid");
        groupId = getIntent().getStringExtra("gid");

        taskTitle.setText(getIntent().getStringExtra("title"));
        taskDesc.setText(getIntent().getStringExtra("desc"));

        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getInstance().deleteTask(groupId, taskId);
            }
        });

        finilazeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getInstance().completeTask(groupId,taskId);
            }
        });

    }

    @Override
    public void endTaskDelete(){
        Log.i("Dev", "Task delete called");
        Intent i = new Intent(this,TaskActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }
}
