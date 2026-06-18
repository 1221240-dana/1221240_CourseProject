package com.example.a1221240_courseproject;

import java.util.ArrayList;

public class Booking {

    public static ArrayList<Booking> bookingsArrayList = new ArrayList<Booking>();

    private long mBookingId;
    private String mEventName;
    private int mCount;
    private String mReservationType;
    private String mStatus;

    public Booking() {
    }

    public Booking(long mBookingId, String mEventName, int mCount,
                   String mReservationType, String mStatus) {
        this.mBookingId = mBookingId;
        this.mEventName = mEventName;
        this.mCount = mCount;
        this.mReservationType = mReservationType;
        this.mStatus = mStatus;
    }

    public long getmBookingId() {
        return mBookingId;
    }

    public void setmBookingId(long mBookingId) {
        this.mBookingId = mBookingId;
    }

    public String getmEventName() {
        return mEventName;
    }

    public void setmEventName(String mEventName) {
        this.mEventName = mEventName;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public String getmReservationType() {
        return mReservationType;
    }

    public void setmReservationType(String mReservationType) {
        this.mReservationType = mReservationType;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "\nmBookingId=" + mBookingId +
                "\n, mEventName='" + mEventName + '\'' +
                "\n, mCount=" + mCount +
                "\n, mReservationType='" + mReservationType + '\'' +
                "\n, mStatus='" + mStatus + '\'' +
                "\n}\n\n";
    }
}