package com.example.genceozer.utodo.taskgroup;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.TaskGroup;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by genceozer on 05/05/16.
 */
public class TaskGroupListAdapter extends ArrayAdapter<TaskGroup> {

    public TaskGroupListAdapter(Context ctx, List<TaskGroup> taskGroups){

        super(ctx,android.R.layout.simple_list_item_1,taskGroups);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder  = null;


        if(row == null){
            LayoutInflater inflator =((Activity) getContext()).getLayoutInflater();
            row = inflator.inflate(R.layout.task_group_list_layout, parent, false);

            holder = new ViewHolder();

            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.txtDate = (TextView)row.findViewById(R.id.txtDate);

            row.setTag(holder);


        }

        holder = (ViewHolder)row.getTag();

        holder.txtTitle.setText(getItem(position).getGroupTitle().toString());

        SimpleDateFormat format = new
                SimpleDateFormat("dd/MM/yyyy");


//        holder.txtDate.setText(format.format(getItem(position).getIncomingDeadline()));




        return row;


    }

    class ViewHolder{
        TextView txtTitle;
        TextView txtDate;

    }
}
