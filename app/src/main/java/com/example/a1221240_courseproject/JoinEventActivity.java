package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class JoinEventActivity extends AppCompatActivity {

    TextView textViewSelectedEvent;
    EditText editTextCount;
    Spinner spinnerReservationType;
    Button buttonConfirmBooking;
    Button buttonBackEvents;

    String eventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);

        textViewSelectedEvent = (TextView) findViewById(R.id.textViewSelectedEvent);
        editTextCount = (EditText) findViewById(R.id.editTextCount);
        spinnerReservationType = (Spinner) findViewById(R.id.spinnerReservationType);
        buttonConfirmBooking = (Button) findViewById(R.id.buttonConfirmBooking);
        buttonBackEvents = (Button) findViewById(R.id.buttonBackEvents);

        eventName = getIntent().getStringExtra("eventName");

        if (eventName == null) {
            eventName = "Unknown Event";
        }

        textViewSelectedEvent.setText(eventName);

        buttonConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countText = editTextCount.getText().toString();
                String reservationType = spinnerReservationType.getSelectedItem().toString();

                if (countText.isEmpty()) {

                    Toast.makeText(JoinEventActivity.this,
                            "Please enter participation count",
                            Toast.LENGTH_SHORT).show();

                } else {
                    int count = Integer.parseInt(countText);

                    if (count <= 0) {
                        Toast.makeText(JoinEventActivity.this,
                                "Count must be greater than zero",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Booking booking = new Booking(
                                Booking.bookingsArrayList.size() + 1,
                                eventName,
                                count,
                                reservationType,
                                "Confirmed"
                        );

                        Booking.bookingsArrayList.add(booking);

                        Toast.makeText(JoinEventActivity.this,
                                "Reservation confirmed",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(JoinEventActivity.this, EventListActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        buttonBackEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JoinEventActivity.this, EventListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}