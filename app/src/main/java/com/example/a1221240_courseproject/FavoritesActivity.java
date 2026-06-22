package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FavoritesActivity extends AppCompatActivity {

    LinearLayout linearLayoutFavorites;
    Button buttonBackHomeFromFavorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        linearLayoutFavorites = (LinearLayout) findViewById(R.id.linearLayoutFavorites);
        buttonBackHomeFromFavorites = (Button) findViewById(R.id.buttonBackHomeFromFavorites);

        if (Favorite.favoritesArrayList.size() == 0) {
            TextView textView = new TextView(FavoritesActivity.this);
            textView.setText("No favorite events yet.");
            textView.setTextSize(18);
            textView.setPadding(0, 30, 0, 30);
            linearLayoutFavorites.addView(textView);
        } else {
            for (int i = 0; i < Favorite.favoritesArrayList.size(); i++) {

                final int index = i;
                final Favorite favorite = Favorite.favoritesArrayList.get(i);

                TextView textView = new TextView(FavoritesActivity.this);
                textView.setText("Event: " + favorite.getmEventName() + "\n" +
                        "Category: " + favorite.getmCategory() + "\n" +
                        "Date: " + favorite.getmDate());
                textView.setTextSize(16);
                textView.setPadding(0, 30, 0, 10);
                linearLayoutFavorites.addView(textView);

                Button buttonOpenDetails = new Button(FavoritesActivity.this);
                buttonOpenDetails.setText("Open Event Details");
                buttonOpenDetails.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.colorButton1)));
                linearLayoutFavorites.addView(buttonOpenDetails);

                buttonOpenDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        for (int j = 0; j < Event.eventsArrayList.size(); j++) {
                            if (Event.eventsArrayList.get(j).getmTitle()
                                    .equals(favorite.getmEventName())) {
                                Event event = Event.eventsArrayList.get(j);
                                Intent intent = new Intent(FavoritesActivity.this,
                                        EventDetailActivity.class);
                                intent.putExtra("title", event.getmTitle());
                                intent.putExtra("description", event.getmDescription());
                                intent.putExtra("category", event.getmCategory());
                                intent.putExtra("date", event.getmDate());
                                intent.putExtra("time", event.getmTime());
                                intent.putExtra("location", event.getmLocation());
                                intent.putExtra("seats", event.getmSeats());
                                startActivity(intent);
                                return;
                            }
                        }
                        Toast.makeText(FavoritesActivity.this,
                                "Event details not available",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                Button buttonReserve = new Button(FavoritesActivity.this);
                buttonReserve.setText("Reserve This Event");
                buttonReserve.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.colorButton2)));
                linearLayoutFavorites.addView(buttonReserve);

                buttonReserve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(FavoritesActivity.this, JoinEventActivity.class);
                        intent.putExtra("eventName", favorite.getmEventName());
                        startActivity(intent);
                    }
                });

                Button buttonRemove = new Button(FavoritesActivity.this);
                buttonRemove.setText("Remove From Favorites");
                buttonRemove.setBackgroundTintList(ColorStateList.valueOf(
                        getResources().getColor(R.color.colorButton3)));
                linearLayoutFavorites.addView(buttonRemove);

                buttonRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Favorite.favoritesArrayList.remove(index);
                        Toast.makeText(FavoritesActivity.this,
                                "Removed from favorites",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FavoritesActivity.this, FavoritesActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }

        buttonBackHomeFromFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavoritesActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}