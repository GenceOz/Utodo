package com.example.genceozer.utodo.login_register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.genceozer.utodo.R;
import com.example.genceozer.utodo.taskgroup.TaskGroupActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



    }

    public void switchRegister(View v) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void login(View v){
        Intent i = new Intent(this, TaskGroupActivity.class);
        startActivity(i);
    }
}
