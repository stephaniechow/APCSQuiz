package com.example.steph.apcsmcquiz5;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Zoe on 11/20/17.
 */

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    private int id;;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "score")
    private int score;

    public User(){};

    public User(String n, int s){
        name = n;
        score = s;
    }

    public String getName() {return name;}

    public void setName(String n) {name = n;}

    public int getScore() {return score;}

    public void setScore(int s) {score = s;}

    public int getId() {return id;}

    public void setId(int i) {id = i;}
}
