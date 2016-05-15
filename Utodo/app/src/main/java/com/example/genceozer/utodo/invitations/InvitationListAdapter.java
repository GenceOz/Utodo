package com.example.genceozer.utodo.invitations;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.Invitation;
import com.example.genceozer.utodo.entities.SubTask;

import java.util.List;

public class InvitationListAdapter extends ArrayAdapter<Invitation> {
    List<Object> iidList;
    List <Object> groupIdList;

    public InvitationListAdapter(Context ctx, List<Invitation> invitations, List<Object> iidL, List<Object> groupIdList){

        super(ctx,android.R.layout.simple_list_item_1,invitations);
        iidList = iidL;
        this.groupIdList = groupIdList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder  = null;


        if(row == null){
            LayoutInflater inflator =((Activity) getContext()).getLayoutInflater();
            row = inflator.inflate(R.layout.activity_invitation_list_adapter, parent, false);

            holder = new ViewHolder();

            holder.groupTitle = (TextView)row.findViewById(R.id.invitationGroupName);
            holder.invitorName = (TextView)row.findViewById(R.id.invitationInvitorName);
            holder.accept = (ImageButton) row.findViewById(R.id.acceptInvitationButton);
            holder.reject = (ImageButton) row.findViewById(R.id.rejectInvitationButton);

            row.setTag(holder);

            holder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Connector.getInstance().acceptInvitation(groupIdList.get(position),getItem(position).getGroupTitle(),iidList.get(position));
                }
            });

            holder.reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Connector.getInstance().deleteInvitation(iidList.get(position));
                }
            });


        }


        holder = (ViewHolder)row.getTag();

        holder.groupTitle.setText(getItem(position).getGroupTitle());
        holder.invitorName.setText(getItem(position).getInvitorName());

        holder
        return row;


    }

    class ViewHolder{
        TextView groupTitle;
        TextView invitorName;
        ImageButton accept;
        ImageButton reject;

    }
}
