package com.android2taskapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.android2taskapp.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

}
