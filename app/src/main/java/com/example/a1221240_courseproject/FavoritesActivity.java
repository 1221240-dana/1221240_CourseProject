package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                textView.setText(favorite.toString());
                textView.setTextSize(18);
                textView.setPadding(0, 30, 0, 10);
                linearLayoutFavorites.addView(textView);

                Button buttonReserve = new Button(FavoritesActivity.this);
                buttonReserve.setText("Reserve This Event");
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