package com.example.steph.apcsmcquiz5;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by steph on 11/19/2017.
 */

@Database(entities = {Question.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionDao questionDao();


}
