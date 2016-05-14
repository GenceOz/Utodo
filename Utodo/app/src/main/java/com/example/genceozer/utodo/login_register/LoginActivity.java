package com.example.genceozer.utodo.login_register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.genceozer.utodo.Connector;
import com.firebase.client.Firebase;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.taskgroup.TaskGroupActivity;

public class LoginActivity extends AppCompatActivity implements Connector.ConnectorLogin{

    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailText   = (EditText)findViewById(R.id.email);
        passwordText =(EditText)findViewById(R.id.password);
        Firebase.setAndroidContext(this);
        Connector.getInstance().loginActivityDelegate = this;

    }

    public void switchRegister(View v) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void login(View v){
        Connector.getInstance().userLogIn(emailText.getText().toString(),passwordText.getText().toString());
        return;
    }

    @Override
    public void userLoggedIn(){
        Intent i = new Intent(this, TaskGroupActivity.class);
        startActivity(i);
    }

    @Override
    public void userLogInFailed(){
        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
    }
}
