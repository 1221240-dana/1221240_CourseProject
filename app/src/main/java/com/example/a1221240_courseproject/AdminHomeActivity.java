package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminHomeActivity extends AppCompatActivity {

    Button buttonOpenAdminDrawer;
    Button buttonViewUsers;
    Button buttonViewEvents;
    Button buttonViewReservations;
    Button buttonAdminLogout;

    DrawerLayout adminDrawerLayout;
    NavigationView adminNavigationView;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        adminDrawerLayout = (DrawerLayout) findViewById(R.id.adminDrawerLayout);
        adminNavigationView = (NavigationView) findViewById(R.id.adminNavigationView);

        buttonOpenAdminDrawer = (Button) findViewById(R.id.buttonOpenAdminDrawer);
        buttonViewUsers = (Button) findViewById(R.id.buttonViewUsers);
        buttonViewEvents = (Button) findViewById(R.id.buttonViewEvents);
        buttonViewReservations = (Button) findViewById(R.id.buttonViewReservations);
        buttonAdminLogout = (Button) findViewById(R.id.buttonAdminLogout);

        databaseHelper = new DatabaseHelper(this);

        buttonOpenAdminDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        adminNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                adminDrawerLayout.closeDrawers();
                int id = item.getItemId();

                if (id == R.id.nav_admin_home) {
                    Toast.makeText(AdminHomeActivity.this,
                            "You are already on Admin Home", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.nav_admin_users) {
                    showUsers();

                } else if (id == R.id.nav_admin_events) {
                    showEvents();

                } else if (id == R.id.nav_admin_reservations) {
                    showReservations();

                } else if (id == R.id.nav_admin_logout) {
                    startActivity(new Intent(AdminHomeActivity.this, SignInActivity.class));
                    finish();
                }

                return true;
            }
        });

        buttonViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUsers();
            }
        });

        buttonViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEvents();
            }
        });

        buttonViewReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReservations();
            }
        });

        buttonAdminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, SignInActivity.class));
                finish();
            }
        });
    }

    private void showUsers() {
        Cursor cursor = databaseHelper.getUserInfo("");
        StringBuilder sb = new StringBuilder();
        sb.append("Users:\n\n");
        while (cursor.moveToNext()) {
            sb.append("Email: ").append(cursor.getString(1)).append("\n");
            sb.append("Name: ").append(cursor.getString(3)).append(" ")
                    .append(cursor.getString(4)).append("\n\n");
        }
        cursor.close();
        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
    }

    private void showEvents() {
        StringBuilder sb = new StringBuilder();
        sb.append("Events: " + Event.eventsArrayList.size() + "\n\n");
        for (int i = 0; i < Event.eventsArrayList.size(); i++) {
            sb.append(Event.eventsArrayList.get(i).getmTitle()).append("\n");
        }
        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
    }

    private void showReservations() {
        Cursor cursor = databaseHelper.getAllBookings();
        StringBuilder sb = new StringBuilder();
        sb.append("Reservations:\n\n");
        while (cursor.moveToNext()) {
            sb.append("Event: ").append(cursor.getString(1)).append("\n");
            sb.append("Status: ").append(cursor.getString(4)).append("\n\n");
        }
        cursor.close();
        Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
    }
}