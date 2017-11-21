package com.example.steph.apcsmcquiz5;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Question> specificQuestions;
    private AppDatabase db;
    private Question q;
    public static String topic;
    private TextView qText;
    private RadioButton a, b, c, d, e;
    private RadioButton[] choices;
    private String choiceId;
    public static int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a = (RadioButton)findViewById(R.id.choice1Text);
        b = (RadioButton)findViewById(R.id.choice2Text);
        c = (RadioButton)findViewById(R.id.choice3Text);
        d = (RadioButton)findViewById(R.id.choice4Text);
        e = (RadioButton)findViewById(R.id.choice5Text);

        choices = new RadioButton[]{a,b,c,d,e};

        qText = (TextView)findViewById(R.id.questionText);

        for(int i = 0; i<choices.length; i++) {
            choices[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click(v);
                }
            });
        }

        db = Data.db;

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                if(topic.equals("all")){
                    specificQuestions = db.questionDao().getAll();
                }
                else if(topic.equals("review")){
                    specificQuestions = db.questionDao().getReview();
                }
                else{
                    specificQuestions = db.questionDao().getQuestions(topic);
                }
                if(specificQuestions.size()>0){
                    newQuestion();
                }
                else{
                    qText.setText("You don't need a review!  You have already completed all the questions correctly.");
                }
            }
        };

        Thread getQuestions = new Thread(r2);
        getQuestions.start();
    }

    public void newQuestion(){
        for(int i = 0; i<choices.length; i++){
            choices[i].setChecked(false);
        }
        Random r = new Random();
        int i = r.nextInt(specificQuestions.size());
        q = specificQuestions.get(i);
        qText.setText(q.getQuestion());
        a.setText(q.getChoice1());
        b.setText(q.getChoice2());
        c.setText(q.getChoice3());
        d.setText(q.getChoice4());
        e.setText(q.getChoice5());
        specificQuestions.remove(i);
    }

    public void click(View v){
        switch(v.getId()){
            case R.id.choice1Text:
                choiceId = "1";
                break;
            case R.id.choice2Text:
                choiceId = "2";
                break;
            case R.id.choice3Text:
                choiceId = "3";
                break;
            case R.id.choice4Text:
                choiceId = "4";
                break;
            case R.id.choice5Text:
                choiceId = "5";
                break;
        }

        if (choiceId.equals(q.getAnswer())){
            updateReview(q.getQuestionID(), "false");
            score++;
        }
        else{
            updateReview(q.getQuestionID(), "true");
        }

        if(specificQuestions.size()>0){
            newQuestion();
        }

        else{
            Intent intent = new Intent(this, EndPage.class);
            startActivity(intent);
        }
    }

    public void updateReview(final int id, final String s){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                db.questionDao().updateR(id, s);
            }
        };

        Thread t = new Thread(r);
        t.start();
    }

}
