package com.example.genceozer.utodo.login_register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.genceozer.utodo.Connector;
import com.firebase.client.Firebase;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.taskgroup.TaskGroupActivity;

public class LoginActivity extends AppCompatActivity {

    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);

    }

    public void switchRegister(View v) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void login(View v){
        Connector.getInstance().userLogIn(emailText.getText().toString(),passwordText.getText().toString());
    }
}
