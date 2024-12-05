package com.example.donchenko6task.models;

import java.io.Serializable;

public class Reminder implements Serializable {
    private long reminderId;
    private String title;
    private String text;
    private long dateTime;

    public Reminder(String title, String text, long dateTime) {
        this.title = title;
        this.text = text;
        this.dateTime = dateTime;
    }

    public long getReminderId() {
        return reminderId;
    }

    public void setReminderId(long reminderId) {
        this.reminderId = reminderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}