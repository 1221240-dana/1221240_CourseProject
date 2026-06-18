package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class EventListActivity extends AppCompatActivity {

    LinearLayout linearLayoutEvents;
    Button buttonBackHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        linearLayoutEvents = (LinearLayout) findViewById(R.id.linearLayoutEvents);
        buttonBackHome = (Button) findViewById(R.id.buttonBackHome);

        if (Event.eventsArrayList.size() == 0) {
            Event.eventsArrayList.add(new Event(1, "AI Workshop",
                    "Introduction to AI", "Technology",
                    "2026-06-10", "10:00 AM",
                    "Engineering Hall", 80));

            Event.eventsArrayList.add(new Event(2, "Cyber Security Day",
                    "Basic cyber security awareness", "Technology",
                    "2026-06-12", "12:00 PM",
                    "IT Lab", 50));

            Event.eventsArrayList.add(new Event(3, "Career Talk",
                    "Meet companies and learn about internships", "Career",
                    "2026-06-15", "1:00 PM",
                    "Main Auditorium", 120));
        }

        for (int i = 0; i < Event.eventsArrayList.size(); i++) {
            final Event event = Event.eventsArrayList.get(i);

            TextView textView = new TextView(EventListActivity.this);
            textView.setText(event.toString());
            textView.setTextSize(18);
            textView.setPadding(0, 30, 0, 10);
            linearLayoutEvents.addView(textView);

            Button buttonJoin = new Button(EventListActivity.this);
            buttonJoin.setText("Join / Reserve");
            linearLayoutEvents.addView(buttonJoin);

            buttonJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(EventListActivity.this, JoinEventActivity.class);
                    intent.putExtra("eventName", event.getmTitle());
                    startActivity(intent);
                }
            });

            Button buttonFavorite = new Button(EventListActivity.this);
            buttonFavorite.setText("Add to Favorites");
            linearLayoutEvents.addView(buttonFavorite);

            buttonFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Favorite favorite = new Favorite(
                            Favorite.favoritesArrayList.size() + 1,
                            event.getmTitle(),
                            event.getmCategory(),
                            event.getmDate()
                    );

                    Favorite.favoritesArrayList.add(favorite);

                    Toast.makeText(EventListActivity.this,
                            "Added to favorites",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventListActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}