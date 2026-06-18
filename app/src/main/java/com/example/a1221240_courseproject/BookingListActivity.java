package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BookingListActivity extends AppCompatActivity {

    LinearLayout linearLayoutBookings;
    Button buttonBackHomeFromBookings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);

        linearLayoutBookings = (LinearLayout) findViewById(R.id.linearLayoutBookings);
        buttonBackHomeFromBookings = (Button) findViewById(R.id.buttonBackHomeFromBookings);

        if (Booking.bookingsArrayList.size() == 0) {
            TextView textView = new TextView(BookingListActivity.this);
            textView.setText("No reservations yet.");
            textView.setTextSize(18);
            textView.setPadding(0, 30, 0, 30);
            linearLayoutBookings.addView(textView);
        } else {
            for (int i = 0; i < Booking.bookingsArrayList.size(); i++) {
                TextView textView = new TextView(BookingListActivity.this);
                textView.setText(Booking.bookingsArrayList.get(i).toString());
                textView.setTextSize(18);
                textView.setPadding(0, 30, 0, 30);
                linearLayoutBookings.addView(textView);
            }
        }

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