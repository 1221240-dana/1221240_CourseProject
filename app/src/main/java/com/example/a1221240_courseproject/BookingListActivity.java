package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookingListActivity extends AppCompatActivity {

    LinearLayout linearLayoutBookings;
    Button buttonBackHomeFromBookings;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        linearLayoutBookings = (LinearLayout) findViewById(R.id.linearLayoutBookings);
        buttonBackHomeFromBookings = (Button) findViewById(R.id.buttonBackHomeFromBookings);

        databaseHelper = new DatabaseHelper(this);

        SharedPreferences loginPreferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        String currentEmail = loginPreferences.getString("currentEmail", "");

        Cursor cursor = databaseHelper.getBookingsByUser(currentEmail);

        if (cursor.getCount() == 0) {
            TextView textView = new TextView(BookingListActivity.this);
            textView.setText("No reservations yet.");
            textView.setTextSize(18);
            textView.setPadding(0, 30, 0, 30);
            linearLayoutBookings.addView(textView);
        } else {
            while (cursor.moveToNext()) {
                TextView textView = new TextView(BookingListActivity.this);
                textView.setText(
                        "Event: " + cursor.getString(2) + "\n" +
                                "Count: " + cursor.getInt(3) + "\n" +
                                "Type: " + cursor.getString(4) + "\n" +
                                "Status: " + cursor.getString(5) + "\n" +
                                "Date: " + cursor.getString(6));
                textView.setTextSize(16);
                textView.setPadding(0, 30, 0, 30);
                linearLayoutBookings.addView(textView);
            }
        }
        cursor.close();

        buttonBackHomeFromBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingListActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}