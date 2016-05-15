package com.example.genceozer.utodo.invitations;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.Invitation;
import com.example.genceozer.utodo.entities.SubTask;

import java.util.List;

public class InvitationListAdapter extends ArrayAdapter<Invitation> {

    public InvitationListAdapter(Context ctx, List<Invitation> invitations){

        super(ctx,android.R.layout.simple_list_item_1,invitations);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder  = null;


        if(row == null){
            LayoutInflater inflator =((Activity) getContext()).getLayoutInflater();
            row = inflator.inflate(R.layout.activity_invitation_list_adapter, parent, false);

            holder = new ViewHolder();

            holder.groupTitle = (TextView)row.findViewById(R.id.invitationGroupName);
            holder.invitorName = (TextView)row.findViewById(R.id.invitationInvitorName);

            row.setTag(holder);


        }


        holder = (ViewHolder)row.getTag();

        holder.groupTitle.setText(getItem(position).getGroupTitle());
        holder.invitorName.setText(getItem(position).getInvitorName());

        return row;


    }

    class ViewHolder{
        TextView groupTitle;
        TextView invitorName;

    }
}
