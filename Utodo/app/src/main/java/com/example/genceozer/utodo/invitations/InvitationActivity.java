package com.example.genceozer.utodo.invitations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.entities.Invitation;

import java.util.ArrayList;
import java.util.List;

public class InvitationActivity extends AppCompatActivity {

    ListView invitationListView;
    List<Invitation> invitationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        invitationList = new ArrayList<>();
        invitationList.add(new Invitation("Best group","genceoz"));
        invitationListView = (ListView) findViewById(R.id.invitationList);

        InvitationListAdapter adp = new InvitationListAdapter(this, invitationList);
        invitationListView.setAdapter(adp);
    }
}
