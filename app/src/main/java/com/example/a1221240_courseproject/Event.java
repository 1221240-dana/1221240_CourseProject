package com.example.a1221240_courseproject;

import java.util.ArrayList;

public class Event {

    public static ArrayList<Event> eventsArrayList = new ArrayList<Event>();

    private long mEventId;
    private String mTitle;
    private String mDescription;
    private String mCategory;
    private String mDate;
    private String mTime;
    private String mLocation;
    private int mSeats;

    public Event() {
    }

    public Event(long mEventId, String mTitle, String mDescription, String mCategory,
                 String mDate, String mTime, String mLocation, int mSeats) {
        this.mEventId = mEventId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mCategory = mCategory;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mLocation = mLocation;
        this.mSeats = mSeats;
    }

    public long getmEventId() {
        return mEventId;
    }

    public void setmEventId(long mEventId) {
        this.mEventId = mEventId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmLocation() {
        return mLocation;
    }

    public void setmLocation(String mLocation) {
        this.mLocation = mLocation;
    }

    public int getmSeats() {
        return mSeats;
    }

    public void setmSeats(int mSeats) {
        this.mSeats = mSeats;
    }

    @Override
    public String toString() {
        return "Event{" +
                "\nmEventId=" + mEventId +
                "\n, mTitle='" + mTitle + '\'' +
                "\n, mDescription='" + mDescription + '\'' +
                "\n, mCategory='" + mCategory + '\'' +
                "\n, mDate='" + mDate + '\'' +
                "\n, mTime='" + mTime + '\'' +
                "\n, mLocation='" + mLocation + '\'' +
                "\n, mSeats=" + mSeats +
                "\n}\n\n";
    }
}