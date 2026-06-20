package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WelcomeActivity extends AppCompatActivity {

    Button buttonConnect;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        databaseHelper = new DatabaseHelper(this);

        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        buttonConnect.startAnimation(slideIn);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonConnect.setEnabled(false);
                buttonConnect.setText("Connecting...");
                fetchEvents();
            }
        });
    }

    private void fetchEvents() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://mocki.io/v1/c60a4bbe-78b3-405e-9adc-d23301beffab");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    reader.close();
                    connection.disconnect();

                    JSONArray jsonArray = new JSONArray(result.toString());
                    Event.eventsArrayList.clear();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        long id = obj.getLong("id");
                        String title = obj.getString("title");
                        String description = obj.getString("description");
                        String category = obj.getString("category");
                        String date = obj.getString("date");
                        String time = obj.getString("time");
                        String location = obj.getString("location");
                        int seats = obj.getInt("seats");

                        // Save to static list
                        Event event = new Event();
                        event.setmEventId(id);
                        event.setmTitle(title);
                        event.setmDescription(description);
                        event.setmCategory(category);
                        event.setmDate(date);
                        event.setmTime(time);
                        event.setmLocation(location);
                        event.setmSeats(seats);
                        Event.eventsArrayList.add(event);

                        // Save to SQLite
                        databaseHelper.addEvent(id, title, description, category,
                                date, time, location, seats);
                    }

                    new Handler(getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WelcomeActivity.this,
                                    "Connected! " + Event.eventsArrayList.size() + " events loaded",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(WelcomeActivity.this, SignInActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                } catch (Exception e) {
                    new Handler(getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            buttonConnect.setEnabled(true);
                            buttonConnect.setText("Connect");
                            Toast.makeText(WelcomeActivity.this,
                                    "Connection failed. Please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        thread.start();
    }
}