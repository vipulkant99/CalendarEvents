package com.example.calendarevents;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Events {

    @PrimaryKey(autoGenerate = true)
    public int eventID;
    public String EventType;
    public String eventDate;
    public String eventName;

    public Events() {
    }

    public Events(int i, String eventType, String eventDate, String eventName) {
    }


    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
