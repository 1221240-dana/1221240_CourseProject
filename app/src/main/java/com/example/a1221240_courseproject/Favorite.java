package com.example.a1221240_courseproject;

import java.util.ArrayList;

public class Favorite {

    public static ArrayList<Favorite> favoritesArrayList = new ArrayList<Favorite>();

    private long mFavoriteId;
    private String mEventName;
    private String mCategory;
    private String mDate;

    public Favorite() {
    }

    public Favorite(long mFavoriteId, String mEventName, String mCategory, String mDate) {
        this.mFavoriteId = mFavoriteId;
        this.mEventName = mEventName;
        this.mCategory = mCategory;
        this.mDate = mDate;
    }

    public long getmFavoriteId() {
        return mFavoriteId;
    }

    public void setmFavoriteId(long mFavoriteId) {
        this.mFavoriteId = mFavoriteId;
    }

    public String getmEventName() {
        return mEventName;
    }

    public void setmEventName(String mEventName) {
        this.mEventName = mEventName;
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

    @Override
    public String toString() {
        return "Favorite{" +
                "\nmFavoriteId=" + mFavoriteId +
                "\n, mEventName='" + mEventName + '\'' +
                "\n, mCategory='" + mCategory + '\'' +
                "\n, mDate='" + mDate + '\'' +
                "\n}\n\n";
    }
}