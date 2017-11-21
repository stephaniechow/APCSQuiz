package com.example.steph.apcsmcquiz5;

import android.app.ActivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;


public class EndPage extends AppCompatActivity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);
        t = (TextView) findViewById(R.id.textView);
        t.setText(getString(R.string.text, MainActivity.score));
    }

    public void restartQuiz(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void reviewQuiz(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        MainActivity.topic = "review";
        startActivity(intent);
    }

    public void exitQuiz(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        ((ActivityManager)getApplicationContext().getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData();
    }

    public void review(View v) {
        MainActivity.topic = "review";
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}