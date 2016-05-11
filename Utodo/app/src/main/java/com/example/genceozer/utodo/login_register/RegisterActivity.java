package com.example.genceozer.utodo.login_register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.genceozer.utodo.Connector;
import com.example.genceozer.utodo.R;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.usernameText);
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passwordText);
    }

    public void register(){
        Connector.getInstance().userSignUp(username.getText().toString(),
                                        email.getText().toString(),password.getText().toString());
    }
}
