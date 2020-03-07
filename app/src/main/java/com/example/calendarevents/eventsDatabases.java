package com.example.calendarevents;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Events.class,version = 1)
public abstract class eventsDatabases extends RoomDatabase {

    public abstract TheDao dao();
}
