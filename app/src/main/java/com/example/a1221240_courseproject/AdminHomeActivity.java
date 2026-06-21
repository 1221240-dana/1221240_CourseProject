package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminHomeActivity extends AppCompatActivity {

    Button buttonOpenAdminDrawer;
    Button buttonAddAdmin;
    Button buttonViewUsers;
    Button buttonDeleteUser;
    Button buttonAddEvent;
    Button buttonEditEvent;
    Button buttonDeleteEvent;
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
        buttonAddAdmin = (Button) findViewById(R.id.buttonAddAdmin);
        buttonViewUsers = (Button) findViewById(R.id.buttonViewUsers);
        buttonDeleteUser = (Button) findViewById(R.id.buttonDeleteUser);
        buttonAddEvent = (Button) findViewById(R.id.buttonAddEvent);
        buttonEditEvent = (Button) findViewById(R.id.buttonEditEvent);
        buttonDeleteEvent = (Button) findViewById(R.id.buttonDeleteEvent);
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

        buttonAddAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddAdminDialog();
            }
        });

        buttonViewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUsers();
            }
        });

        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteUserDialog();
            }
        });

        buttonAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEventDialog();
            }
        });

        buttonEditEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditEventDialog();
            }
        });

        buttonDeleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteEventDialog();
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
        Cursor cursor = databaseHelper.getAllUsers();
        StringBuilder sb = new StringBuilder();
        sb.append("Users:\n\n");
        while (cursor.moveToNext()) {
            sb.append("Email: ").append(cursor.getString(1)).append("\n");
            sb.append("Name: ").append(cursor.getString(3))
                    .append(" ").append(cursor.getString(4)).append("\n\n");
        }
        cursor.close();
        new AlertDialog.Builder(this)
                .setTitle("All Users")
                .setMessage(sb.toString())
                .setPositiveButton("OK", null)
                .show();
    }

    private void showEvents() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total Events: " + Event.eventsArrayList.size() + "\n\n");
        for (int i = 0; i < Event.eventsArrayList.size(); i++) {
            sb.append(i + 1).append(". ").append(Event.eventsArrayList.get(i).getmTitle()).append("\n");
        }
        new AlertDialog.Builder(this)
                .setTitle("All Events")
                .setMessage(sb.toString())
                .setPositiveButton("OK", null)
                .show();
    }

    private void showReservations() {
        Cursor cursor = databaseHelper.getAllBookings();
        StringBuilder sb = new StringBuilder();
        sb.append("Reservations:\n\n");
        while (cursor.moveToNext()) {
            sb.append("Event: ").append(cursor.getString(1)).append("\n");
            sb.append("Count: ").append(cursor.getInt(2)).append("\n");
            sb.append("Type: ").append(cursor.getString(3)).append("\n");
            sb.append("Status: ").append(cursor.getString(4)).append("\n");
            sb.append("Date: ").append(cursor.getString(5)).append("\n\n");
        }
        cursor.close();
        new AlertDialog.Builder(this)
                .setTitle("All Reservations")
                .setMessage(sb.toString())
                .setPositiveButton("OK", null)
                .show();
    }

    private void showAddAdminDialog() {
        View dialogView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, null);
        final EditText editTextEmail = new EditText(this);
        editTextEmail.setHint("Admin Email");
        final EditText editTextPassword = new EditText(this);
        editTextPassword.setHint("Admin Password");

        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(40, 20, 40, 20);
        layout.addView(editTextEmail);
        layout.addView(editTextPassword);

        new AlertDialog.Builder(this)
                .setTitle("Add New Admin")
                .setView(layout)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = editTextEmail.getText().toString();
                        String password = editTextPassword.getText().toString();
                        if (!email.isEmpty() && !password.isEmpty()) {
                            String encrypted = Base64.encodeToString(
                                    password.getBytes(), Base64.DEFAULT);
                            databaseHelper.addUser(email, encrypted,
                                    "Admin", "Admin", "0000000000", "Male", "Other");
                            Toast.makeText(AdminHomeActivity.this,
                                    "Admin added successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showDeleteUserDialog() {
        final EditText editTextEmail = new EditText(this);
        editTextEmail.setHint("Enter user email to delete");
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setPadding(40, 20, 40, 20);
        layout.addView(editTextEmail);

        new AlertDialog.Builder(this)
                .setTitle("Delete User")
                .setView(layout)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String email = editTextEmail.getText().toString();
                        if (!email.isEmpty()) {
                            int rows = databaseHelper.deleteUser(email);
                            if (rows > 0) {
                                Toast.makeText(AdminHomeActivity.this,
                                        "User deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AdminHomeActivity.this,
                                        "User not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showAddEventDialog() {
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(40, 20, 40, 20);

        final EditText editTitle = new EditText(this);
        editTitle.setHint("Title");
        final EditText editDescription = new EditText(this);
        editDescription.setHint("Description");
        final EditText editCategory = new EditText(this);
        editCategory.setHint("Category");
        final EditText editDate = new EditText(this);
        editDate.setHint("Date (yyyy-mm-dd)");
        final EditText editTime = new EditText(this);
        editTime.setHint("Time");
        final EditText editLocation = new EditText(this);
        editLocation.setHint("Location");
        final EditText editSeats = new EditText(this);
        editSeats.setHint("Seats");
        editSeats.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        layout.addView(editTitle);
        layout.addView(editDescription);
        layout.addView(editCategory);
        layout.addView(editDate);
        layout.addView(editTime);
        layout.addView(editLocation);
        layout.addView(editSeats);

        new AlertDialog.Builder(this)
                .setTitle("Add New Event")
                .setView(layout)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = editTitle.getText().toString();
                        String description = editDescription.getText().toString();
                        String category = editCategory.getText().toString();
                        String date = editDate.getText().toString();
                        String time = editTime.getText().toString();
                        String location = editLocation.getText().toString();
                        int seats = editSeats.getText().toString().isEmpty() ? 0 :
                                Integer.parseInt(editSeats.getText().toString());

                        if (!title.isEmpty()) {
                            long id = Event.eventsArrayList.size() + 100;
                            Event event = new Event(id, title, description,
                                    category, date, time, location, seats);
                            Event.eventsArrayList.add(event);
                            databaseHelper.addEvent(id, title, description,
                                    category, date, time, location, seats);
                            Toast.makeText(AdminHomeActivity.this,
                                    "Event added", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showEditEventDialog() {
        final EditText editTitle = new EditText(this);
        editTitle.setHint("Enter event title to edit");
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setPadding(40, 20, 40, 20);
        layout.addView(editTitle);

        new AlertDialog.Builder(this)
                .setTitle("Edit Event - Enter Title")
                .setView(layout)
                .setPositiveButton("Find", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = editTitle.getText().toString();
                        for (int i = 0; i < Event.eventsArrayList.size(); i++) {
                            if (Event.eventsArrayList.get(i).getmTitle()
                                    .equalsIgnoreCase(title)) {
                                Toast.makeText(AdminHomeActivity.this,
                                        "Event found: " + title + "\nEdit feature coming soon",
                                        Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        Toast.makeText(AdminHomeActivity.this,
                                "Event not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void showDeleteEventDialog() {
        final EditText editTitle = new EditText(this);
        editTitle.setHint("Enter event title to delete");
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setPadding(40, 20, 40, 20);
        layout.addView(editTitle);

        new AlertDialog.Builder(this)
                .setTitle("Delete Event")
                .setView(layout)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String title = editTitle.getText().toString();
                        for (int i = 0; i < Event.eventsArrayList.size(); i++) {
                            if (Event.eventsArrayList.get(i).getmTitle()
                                    .equalsIgnoreCase(title)) {
                                Event.eventsArrayList.remove(i);
                                databaseHelper.deleteEvent(title);
                                Toast.makeText(AdminHomeActivity.this,
                                        "Event deleted", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        Toast.makeText(AdminHomeActivity.this,
                                "Event not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}