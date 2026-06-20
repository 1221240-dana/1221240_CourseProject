package com.example.a1221240_courseproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "university_events.db";
    private static final int DATABASE_VERSION = 1;

    // Users table
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_FIRST_NAME = "first_name";
    public static final String COLUMN_USER_LAST_NAME = "last_name";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_GENDER = "gender";
    public static final String COLUMN_USER_MAJOR = "major";

    // Events table
    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_EVENT_ID = "event_id";
    public static final String COLUMN_EVENT_TITLE = "title";
    public static final String COLUMN_EVENT_DESCRIPTION = "description";
    public static final String COLUMN_EVENT_CATEGORY = "category";
    public static final String COLUMN_EVENT_DATE = "date";
    public static final String COLUMN_EVENT_TIME = "time";
    public static final String COLUMN_EVENT_LOCATION = "location";
    public static final String COLUMN_EVENT_SEATS = "seats";

    // Bookings table
    public static final String TABLE_BOOKINGS = "bookings";
    public static final String COLUMN_BOOKING_ID = "booking_id";
    public static final String COLUMN_BOOKING_EVENT_NAME = "event_name";
    public static final String COLUMN_BOOKING_COUNT = "count";
    public static final String COLUMN_BOOKING_TYPE = "reservation_type";
    public static final String COLUMN_BOOKING_STATUS = "status";
    public static final String COLUMN_BOOKING_DATE = "booking_date";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_EMAIL + " TEXT, " +
                COLUMN_USER_PASSWORD + " TEXT, " +
                COLUMN_USER_FIRST_NAME + " TEXT, " +
                COLUMN_USER_LAST_NAME + " TEXT, " +
                COLUMN_USER_PHONE + " TEXT, " +
                COLUMN_USER_GENDER + " TEXT, " +
                COLUMN_USER_MAJOR + " TEXT)";

        String createEventsTable = "CREATE TABLE " + TABLE_EVENTS + " (" +
                COLUMN_EVENT_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_EVENT_TITLE + " TEXT, " +
                COLUMN_EVENT_DESCRIPTION + " TEXT, " +
                COLUMN_EVENT_CATEGORY + " TEXT, " +
                COLUMN_EVENT_DATE + " TEXT, " +
                COLUMN_EVENT_TIME + " TEXT, " +
                COLUMN_EVENT_LOCATION + " TEXT, " +
                COLUMN_EVENT_SEATS + " INTEGER)";

        String createBookingsTable = "CREATE TABLE " + TABLE_BOOKINGS + " (" +
                COLUMN_BOOKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BOOKING_EVENT_NAME + " TEXT, " +
                COLUMN_BOOKING_COUNT + " INTEGER, " +
                COLUMN_BOOKING_TYPE + " TEXT, " +
                COLUMN_BOOKING_STATUS + " TEXT, " +
                COLUMN_BOOKING_DATE + " TEXT)";

        db.execSQL(createUsersTable);
        db.execSQL(createEventsTable);
        db.execSQL(createBookingsTable);

        // Insert admin account
        db.execSQL("INSERT INTO " + TABLE_USERS + " VALUES (1, 'admin@admin.com', 'Admin123!', 'Admin', 'Admin', '0000000000', 'Male', 'Other')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKINGS);
        onCreate(db);
    }

    // Add user
    public long addUser(String email, String password, String firstName,
                        String lastName, String phone, String gender, String major) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_FIRST_NAME, firstName);
        values.put(COLUMN_USER_LAST_NAME, lastName);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_GENDER, gender);
        values.put(COLUMN_USER_MAJOR, major);
        return db.insert(TABLE_USERS, null, values);
    }

    // Check user login
    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USER_EMAIL + "=? AND " +
                COLUMN_USER_PASSWORD + "=?", new String[]{email, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Check if email exists
    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USER_EMAIL + "=?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Add event
    public long addEvent(long id, String title, String description, String category,
                         String date, String time, String location, int seats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_ID, id);
        values.put(COLUMN_EVENT_TITLE, title);
        values.put(COLUMN_EVENT_DESCRIPTION, description);
        values.put(COLUMN_EVENT_CATEGORY, category);
        values.put(COLUMN_EVENT_DATE, date);
        values.put(COLUMN_EVENT_TIME, time);
        values.put(COLUMN_EVENT_LOCATION, location);
        values.put(COLUMN_EVENT_SEATS, seats);
        return db.insertWithOnConflict(TABLE_EVENTS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    // Add booking
    public long addBooking(String eventName, int count, String type, String status, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BOOKING_EVENT_NAME, eventName);
        values.put(COLUMN_BOOKING_COUNT, count);
        values.put(COLUMN_BOOKING_TYPE, type);
        values.put(COLUMN_BOOKING_STATUS, status);
        values.put(COLUMN_BOOKING_DATE, date);
        return db.insert(TABLE_BOOKINGS, null, values);
    }

    // Get all bookings
    public Cursor getAllBookings() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_BOOKINGS, null);
    }

    // Get user info
    public Cursor getUserInfo(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_USER_EMAIL + "=?", new String[]{email});
    }

    // Update user info
    public int updateUser(String email, String firstName, String lastName,
                          String phone, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FIRST_NAME, firstName);
        values.put(COLUMN_USER_LAST_NAME, lastName);
        values.put(COLUMN_USER_PHONE, phone);
        values.put(COLUMN_USER_PASSWORD, password);
        return db.update(TABLE_USERS, values,
                COLUMN_USER_EMAIL + "=?", new String[]{email});
    }
}