package com.example.v.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.etUser);
        password = (EditText)findViewById(R.id.etPswd);
    }

    public void OnLogin(View view) {
        String user = username.getText().toString();
        String pswd = password.getText().toString();
        String type = "login";
        BackgroundTask bckTask = new BackgroundTask(this);
        bckTask.execute(type,user,pswd);
    }
}
