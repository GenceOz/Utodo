package com.example.genceozer.utodo.task;

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
    Button deleteTask;
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

        taskId = getIntent().getStringExtra("tid");
        groupId = getIntent().getStringExtra("gid");

        taskTitle.setText(getIntent().getStringExtra("title"));
        taskDesc.setText(getIntent().getStringExtra("desc"));

        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getInstance().deleteTask(groupId,taskId);
            }
        });

    }

    @Override
    public void endTaskDelete(){
        Log.i("Dev","Task delete called");
        finish();
    }
}
