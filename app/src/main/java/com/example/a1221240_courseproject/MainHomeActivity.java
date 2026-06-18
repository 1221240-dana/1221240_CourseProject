package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainHomeActivity extends AppCompatActivity {

    Button buttonHome;
    Button buttonEvents;
    Button buttonReservations;
    Button buttonFavorites;
    Button buttonSpecial;
    Button buttonProfile;
    Button buttonContact;
    Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonHome = (Button) findViewById(R.id.buttonHome);
        buttonEvents = (Button) findViewById(R.id.buttonEvents);
        buttonReservations = (Button) findViewById(R.id.buttonReservations);
        buttonFavorites = (Button) findViewById(R.id.buttonFavorites);
        buttonSpecial = (Button) findViewById(R.id.buttonSpecial);
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonContact = (Button) findViewById(R.id.buttonContact);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainHomeActivity.this,
                        "You are already on Home",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHomeActivity.this, EventListActivity.class);
                startActivity(intent);
            }
        });

        buttonReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHomeActivity.this, BookingListActivity.class);
                startActivity(intent);
            }
        });

        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHomeActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

        buttonSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainHomeActivity.this,
                        "Recommended Trips screen will be added next",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainHomeActivity.this,
                        "Profile Management screen will be added next",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainHomeActivity.this,
                        "Contact Us screen will be added next",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainHomeActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}