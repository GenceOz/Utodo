package com.example.genceozer.utodo.invitations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.Invitation;

import java.util.ArrayList;
import java.util.List;

public class InvitationActivity extends AppCompatActivity implements Connector.ConnectorInvitation{

    static ListView invitationListView;
    static List<Invitation> invitationList;
    static List<Object> invitationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);

        invitationList = new ArrayList<>();
        invitationId = new ArrayList<>();

        invitationListView = (ListView) findViewById(R.id.invitationList);

        Connector.getInstance().getUserInvitations();

        InvitationListAdapter adp = new InvitationListAdapter(this, invitationList);
        invitationListView.setAdapter(adp);
    }

    @Override
    public void invitationLoaded(Invitation i,Object iid){
        invitationList.add(i);
        invitationId.add(iid);
        ((BaseAdapter) invitationListView.getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void invitationRefreshInvitations(){
        ((BaseAdapter) invitationListView.getAdapter()).notifyDataSetChanged();
    }
}
