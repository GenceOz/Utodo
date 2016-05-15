package com.example.genceozer.utodo.login_register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;

public class RegisterActivity extends AppCompatActivity implements Connector.ConnectorSignUp {

    EditText username;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Connector.getInstance().registerActivityDelegate = this;
        username = (EditText) findViewById(R.id.usernameText);
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passwordText);
    }

    public void register(View v){
        Connector.getInstance().userSignUp(username.getText().toString(),
                                        email.getText().toString(),password.getText().toString());
    }

    @Override
    public void userSignedUp(){
        finish();
    }

    @Override
    public void popSignUpError(){
        Toast.makeText(RegisterActivity.this, "Sign Up Failed.", Toast.LENGTH_SHORT).show();
        username.setText("");
        email.setText("");
        password.setText("");
    }
}
