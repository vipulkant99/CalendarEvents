package com.example.calendarevents;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addEvents(Events allEvents);

    @Query("select * from Events")
    public List<Events> getEvents();
}
