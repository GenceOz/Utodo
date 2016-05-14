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
import com.example.genceozer.utodo.entities.User;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by genceozer on 06/05/16.
 */
public class CreateTaskGroupListAdapter extends ArrayAdapter<String> {

    public CreateTaskGroupListAdapter(Context ctx, List<String> contributors){

        super(ctx,android.R.layout.simple_list_item_1,contributors);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder  = null;


        if(row == null){
            LayoutInflater inflator =((Activity) getContext()).getLayoutInflater();
            row = inflator.inflate(R.layout.contribute_list_layout, parent, false);

            holder = new ViewHolder();

            holder.txtUsername = (TextView)row.findViewById(R.id.txtUsername);

            row.setTag(holder);


        }

        holder = (ViewHolder)row.getTag();

        holder.txtUsername.setText(getItem(position));

        return row;


    }

    class ViewHolder{
        TextView txtUsername;

    }
}
