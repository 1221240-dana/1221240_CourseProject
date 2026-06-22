package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JoinEventActivity extends AppCompatActivity {

    TextView textViewJoinTitle;
    TextView textViewSelectedEvent;
    EditText editTextCount;
    Spinner spinnerReservationType;
    Button buttonConfirmBooking;
    Button buttonBackEvents;

    DatabaseHelper databaseHelper;
    String eventName;
    String currentUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_event);

        textViewJoinTitle = (TextView) findViewById(R.id.textViewJoinTitle);
        textViewSelectedEvent = (TextView) findViewById(R.id.textViewSelectedEvent);
        editTextCount = (EditText) findViewById(R.id.editTextCount);
        spinnerReservationType = (Spinner) findViewById(R.id.spinnerReservationType);
        buttonConfirmBooking = (Button) findViewById(R.id.buttonConfirmBooking);
        buttonBackEvents = (Button) findViewById(R.id.buttonBackEvents);

        databaseHelper = new DatabaseHelper(this);

        SharedPreferences loginPreferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        currentUserEmail = loginPreferences.getString("currentEmail", "");

        eventName = getIntent().getStringExtra("eventName");
        textViewSelectedEvent.setText("Event: " + eventName);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.reservation_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerReservationType.setAdapter(adapter);

        buttonConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countStr = editTextCount.getText().toString();
                String reservationType = spinnerReservationType.getSelectedItem().toString();

                if (countStr.isEmpty()) {
                    Toast.makeText(JoinEventActivity.this,
                            "Please enter participation count",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                int count = Integer.parseInt(countStr);

                if (count <= 0) {
                    Toast.makeText(JoinEventActivity.this,
                            "Count must be greater than 0",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        .format(new Date());

                long result = databaseHelper.addBooking(currentUserEmail, eventName, count,
                        reservationType, "Confirmed", currentDate);

                Booking booking = new Booking();
                booking.setmBookingId(result);
                booking.setmEventName(eventName);
                booking.setmCount(count);
                booking.setmReservationType(reservationType);
                booking.setmStatus("Confirmed");
                Booking.bookingsArrayList.add(booking);

                Toast.makeText(JoinEventActivity.this,
                        "Reservation confirmed",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(JoinEventActivity.this, EventListActivity.class);
                startActivity(intent);
                finish();
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