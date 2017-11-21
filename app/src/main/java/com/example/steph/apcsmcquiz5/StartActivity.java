package com.example.steph.apcsmcquiz5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    Button iterations, objectClass, array, string, logicAndOperations, all;

    public static String topic, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        iterations = findViewById(R.id.iterations);
        objectClass = findViewById(R.id.objectclass);
        array = findViewById(R.id.array);
        string = findViewById(R.id.string);
        logicAndOperations = findViewById(R.id.logicandop);
        all = findViewById(R.id.all);
    }

    public void click(View v){
        int id = v.getId();

        switch (id){
            case R.id.iterations:
                topic = "Iterations";
                break;
            case R.id.objectclass:
                topic = "Object Class";
                break;
            case R.id.array:
                topic = "Array";
                break;
            case R.id.string:
                topic = "Strings";
                break;
            case R.id.logicandop:
                topic = "Logic and Operations";
                break;
            case R.id.all:
                topic = null;
                break;
        }

        EditText e = findViewById(R.id.userName);
        username = e.getText().toString();

        if(Data.db == null){
            Intent i = new Intent(this, Data.class);
            startActivity(i);
        }
        else{
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}
