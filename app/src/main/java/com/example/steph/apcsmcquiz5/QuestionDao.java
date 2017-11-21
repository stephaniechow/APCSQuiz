package com.example.steph.apcsmcquiz5;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.steph.apcsmcquiz5.Question;

/**
 * Created by steph on 11/19/2017.
 */

@Dao
public interface QuestionDao{
    @Query("SELECT * FROM questions")
    List<Question> getAll();

    @Query("SELECT * FROM questions WHERE topic LIKE :name")
    List<Question> getQuestions(String name);

    @Query("SELECT * FROM questions WHERE review LIKE 'true'")
    List<Question> getReview();

    @Insert
    void insertAll(List<Question> qs);

    @Query("SELECT MAX(score) FROM users")
    int getMax();

    @Insert
    void insertUser(User u);


}
