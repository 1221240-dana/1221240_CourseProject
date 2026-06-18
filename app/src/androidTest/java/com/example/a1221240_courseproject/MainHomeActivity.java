package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
    Button buttonOpenDrawer;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);

        buttonOpenDrawer = (Button) findViewById(R.id.buttonOpenDrawer);
        buttonHome = (Button) findViewById(R.id.buttonHome);
        buttonEvents = (Button) findViewById(R.id.buttonEvents);
        buttonReservations = (Button) findViewById(R.id.buttonReservations);
        buttonFavorites = (Button) findViewById(R.id.buttonFavorites);
        buttonSpecial = (Button) findViewById(R.id.buttonSpecial);
        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonContact = (Button) findViewById(R.id.buttonContact);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        buttonOpenDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(androidx.core.view.GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    Toast.makeText(MainHomeActivity.this,
                            "You are already on Home", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.nav_events) {
                    startActivity(new Intent(MainHomeActivity.this, EventListActivity.class));

                } else if (id == R.id.nav_reservations) {
                    startActivity(new Intent(MainHomeActivity.this, BookingListActivity.class));

                } else if (id == R.id.nav_favorites) {
                    startActivity(new Intent(MainHomeActivity.this, FavoritesActivity.class));

                } else if (id == R.id.nav_special) {
                    Toast.makeText(MainHomeActivity.this,
                            "Recommended Trips screen will be added next", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.nav_profile) {
                    Toast.makeText(MainHomeActivity.this,
                            "Profile Management screen will be added next", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.nav_contact) {
                    Toast.makeText(MainHomeActivity.this,
                            "Contact Us screen will be added next", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.nav_logout) {
                    startActivity(new Intent(MainHomeActivity.this, SignInActivity.class));
                    finish();
                }

                return true;
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainHomeActivity.this,
                        "You are already on Home", Toast.LENGTH_SHORT).show();
            }
        });

        buttonEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeActivity.this, EventListActivity.class));
            }
        });

        buttonReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeActivity.this, BookingListActivity.class));
            }
        });

        buttonFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeActivity.this, FavoritesActivity.class));
            }
        });

        buttonSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainHomeActivity.this,
                        "Recommended Trips screen will be added next", Toast.LENGTH_SHORT).show();
            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainHomeActivity.this,
                        "Profile Management screen will be added next", Toast.LENGTH_SHORT).show();
            }
        });

        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainHomeActivity.this,
                        "Contact Us screen will be added next", Toast.LENGTH_SHORT).show();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeActivity.this, SignInActivity.class));
                finish();
            }
        });
    }
}