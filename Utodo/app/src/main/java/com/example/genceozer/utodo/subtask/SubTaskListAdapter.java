package com.example.genceozer.utodo.subtask;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.SubTask;
import com.example.genceozer.utodo.entities.TaskGroup;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by genceozer on 10/05/16.
 */
public class SubTaskListAdapter extends ArrayAdapter<SubTask> {

    public SubTaskListAdapter(Context ctx, List<SubTask> subTasks){

        super(ctx,android.R.layout.simple_list_item_1,subTasks);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder  = null;


        if(row == null){
            LayoutInflater inflator =((Activity) getContext()).getLayoutInflater();
            row = inflator.inflate(R.layout.subtask_list_layout, parent, false);

            holder = new ViewHolder();

            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.checkButton = (ImageView)row.findViewById(R.id.checkButton);

            row.setTag(holder);


        }


        holder = (ViewHolder)row.getTag();

        holder.txtTitle.setText(getItem(position).getTitle());

        if(getItem(position).isDone()){
            holder.checkButton.setBackgroundResource(R.drawable.check_icon);
        }else{
            holder.checkButton.setBackgroundResource(R.drawable.pending_icon);
        }

        return row;


    }

    class ViewHolder{
        TextView txtTitle;
        ImageView checkButton;

    }
}
