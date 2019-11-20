package com.android2taskapp;

import android.app.Application;

import androidx.room.Room;

import com.android2taskapp.room.AppDatabase;

public class App extends Application {

    public static App instance;

    public static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries().build();
    }

    public static AppDatabase getDatabase() {
        return database;
    }
}
