package com.example.steph.apcsmcquiz5;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by steph on 11/19/2017.
 */

@Entity(tableName = "questions")
public class Question {

    @PrimaryKey@ColumnInfo(name = "questionID")
    private int questionID;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "answer")
    private String answer;

    @ColumnInfo (name = "choice1")
    private String choice1;

    @ColumnInfo (name = "choice2")
    private String choice2;

    @ColumnInfo (name = "choice3")
    private String choice3;

    @ColumnInfo (name = "choice4")
    private String choice4;

    @ColumnInfo (name = "choice5")
    private String choice5;

    @ColumnInfo (name = "topic")
    private String topic;

    public Question(int id, String q, String a, String a1, String a2, String a3, String a4, String a5, String t){
        questionID = id;
        question = q;
        answer = a;
        choice1 = a1;
        choice2 = a2;
        choice3 = a3;
        choice4 = a4;
        choice5 = a5;
        topic = t;
    }

    public Question(){}

    public int getQuestionID(){
        return questionID;
    }

    public void setQuestionID(int i){
        questionID = i;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String q){
        question = q;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String q){
        answer = q;
    }

    public String getChoice1(){
        return choice1;
    }

    public void setChoice1(String q){
        choice1 = q;
    }

    public String getChoice2(){
        return choice2;
    }

    public void setChoice2(String q){
        choice2 = q;
    }

    public String getChoice3(){
        return choice3;
    }

    public void setChoice3(String q){
        choice3 = q;
    }

    public String getChoice4(){
        return choice4;
    }

    public void setChoice4(String q){
        choice4 = q;
    }

    public String getChoice5(){
        return choice5;
    }

    public void setChoice5(String q){
        choice5 = q;
    }

    public String getTopic(){
        return topic;
    }

    public void setTopic(String q){
        topic = q;
    }
}
