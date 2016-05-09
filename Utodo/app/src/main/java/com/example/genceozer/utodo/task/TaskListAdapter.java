package com.example.genceozer.utodo.task;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.Task;
import com.example.genceozer.utodo.entities.TaskGroup;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by genceozer on 06/05/16.
 */
    //This class is used for filling the Task Group List with the data parameter
    public class TaskListAdapter extends ArrayAdapter<Task> {

        public TaskListAdapter(Context ctx, List<Task> tasks){

            super(ctx,android.R.layout.simple_list_item_1,tasks);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            ViewHolder holder  = null;


            if(row == null){
                LayoutInflater inflator =((Activity) getContext()).getLayoutInflater();
                row = inflator.inflate(R.layout.task_list_layout, parent, false);

                holder = new ViewHolder();

                holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
                holder.txtDate = (TextView)row.findViewById(R.id.txtDate);
                holder.checkButton = (ImageView)row.findViewById(R.id.checkButton);
                row.setTag(holder);


            }

            holder = (ViewHolder)row.getTag();

            holder.txtTitle.setText(getItem(position).getTitle().toString());

            SimpleDateFormat format = new
                    SimpleDateFormat("dd/MM/yyyy");


            holder.txtDate.setText(format.format(getItem(position).getDueDate()));

            if(getItem(position).isDone()){
                holder.checkButton.setBackgroundResource(R.drawable.check_icon);
            }else{
                holder.checkButton.setBackgroundResource(R.drawable.pending_icon);
            }


            return row;


        }

        class ViewHolder{
            TextView txtTitle;
            TextView txtDate;
            ImageView checkButton;
        }
}
