package com.example.genceozer.utodo.task;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Date;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.SubTask;
import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.login_register.RegisterActivity;

import java.util.Calendar;
import java.util.List;

public class CreateTasks extends AppCompatActivity implements Connector.ConnectorCreateTask {

    EditText name,description;
    static Date taskDate;
    static List<SubTask> subTaskList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tasks);

        Connector.getInstance().createTaskDelegate = this;

        name = (EditText)findViewById(R.id.taskName);
        description = (EditText) findViewById(R.id.taskDescription);
    }


    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showSubTasksDialog(View w){
        SubTaskAddDialog dlg = new SubTaskAddDialog();
        dlg.show(getSupportFragmentManager(), "subTaskDialog");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            taskDate = new Date(year,month,day);
        }
    }

    public static class SubTaskAddDialog extends DialogFragment {

        EditText title,description;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.add_subtask_dialog,null);
        }

        public void setSubtasks(View v){
            subTaskList.add(new SubTask(title.getText().toString(),description.getText().toString(),false));
        }
    }

    public void createTask(View v){
        Task newTask = new Task();
        newTask.setTitle(name.getText().toString());
        newTask.setDescription(description.getText().toString());
        newTask.setIsDone(false);
        newTask.setSubTasks(null);
        newTask.setDueDate(null);

        Intent i = getIntent();
        Connector.getInstance().createTask(i.getStringExtra("gid"),newTask);
    }

    public void taskCreated(){
        Intent i = new Intent(this, TaskActivity.class);
        startActivity(i);
    }
}
