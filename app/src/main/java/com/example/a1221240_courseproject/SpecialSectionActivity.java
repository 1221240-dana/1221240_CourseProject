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

public class SpecialSectionActivity extends AppCompatActivity {

    LinearLayout linearLayoutSpecial;
    Button buttonBackFromSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_section);

        linearLayoutSpecial = (LinearLayout) findViewById(R.id.linearLayoutSpecial);
        buttonBackFromSpecial = (Button) findViewById(R.id.buttonBackFromSpecial);

        loadFeaturedEvents();

        buttonBackFromSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpecialSectionActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadFeaturedEvents() {
        if (Event.eventsArrayList == null || Event.eventsArrayList.isEmpty()) {
            TextView textViewEmpty = new TextView(this);
            textViewEmpty.setText("No featured events yet.");
            textViewEmpty.setTextSize(16);
            linearLayoutSpecial.addView(textViewEmpty);
            return;
        }

        for (int i = 0; i < Event.eventsArrayList.size(); i++) {
            Event event = Event.eventsArrayList.get(i);

            if (event.getmCategory().equals("Technology") || event.getmCategory().equals("Career")) {

                TextView textViewEvent = new TextView(this);
                textViewEvent.setText("⭐ " + event.getmTitle() + "\n" +
                        "Category: " + event.getmCategory() + "\n" +
                        "Date: " + event.getmDate() + "\n" +
                        "Location: " + event.getmLocation());
                textViewEvent.setTextSize(16);
                textViewEvent.setPadding(16, 16, 16, 16);
                linearLayoutSpecial.addView(textViewEvent);

                Button buttonAddFavorite = new Button(this);
                buttonAddFavorite.setText("Add to Favorites");
                buttonAddFavorite.setBackgroundTintList(
                        ColorStateList.valueOf(
                                getResources().getColor(R.color.colorOrange)));

                final Event selectedEvent = event;
                buttonAddFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Favorite favorite = new Favorite();
                        favorite.setmFavoriteId(Favorite.favoritesArrayList.size() + 1);
                        favorite.setmEventName(selectedEvent.getmTitle());
                        favorite.setmCategory(selectedEvent.getmCategory());
                        favorite.setmDate(selectedEvent.getmDate());
                        Favorite.favoritesArrayList.add(favorite);
                        Toast.makeText(SpecialSectionActivity.this,
                                selectedEvent.getmTitle() + " added to favorites",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                linearLayoutSpecial.addView(buttonAddFavorite);
            }
        }
    }
}