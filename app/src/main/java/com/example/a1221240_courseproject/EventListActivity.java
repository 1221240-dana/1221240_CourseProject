package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity {

    RecyclerView recyclerViewEvents;
    EditText editTextSearch;
    Spinner spinnerFilter;
    Button buttonBackHome;

    EventAdapter eventAdapter;
    ArrayList<Event> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recyclerViewEvents = (RecyclerView) findViewById(R.id.recyclerViewEvents);
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        spinnerFilter = (Spinner) findViewById(R.id.spinnerFilter);
        buttonBackHome = (Button) findViewById(R.id.buttonBackHome);

        // Sample data if empty
        if (Event.eventsArrayList.isEmpty()) {
            Event.eventsArrayList.add(new Event(1, "AI Workshop",
                    "Introduction to AI", "Technology",
                    "2026-07-10", "10:00 AM", "Engineering Hall", 80));
            Event.eventsArrayList.add(new Event(2, "Cyber Security Day",
                    "Learn about cyber security", "Technology",
                    "2026-07-15", "09:00 AM", "Computer Lab", 50));
            Event.eventsArrayList.add(new Event(3, "Career Talk",
                    "Meet top companies", "Career",
                    "2026-07-20", "11:00 AM", "Main Hall", 200));
        }

        filteredList = new ArrayList<Event>(Event.eventsArrayList);

        eventAdapter = new EventAdapter(this, filteredList);
        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEvents.setAdapter(eventAdapter);

        // Filter spinner
        ArrayList<String> categories = new ArrayList<String>();
        categories.add("All");
        categories.add("Technology");
        categories.add("Career");
        categories.add("Culture");
        categories.add("Academic");
        categories.add("Health");
        categories.add("Arts");
        categories.add("Sports");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(spinnerAdapter);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                filterEvents(editTextSearch.getText().toString(),
                        spinnerFilter.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterEvents(charSequence.toString(),
                        spinnerFilter.getSelectedItem().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        buttonBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventListActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void filterEvents(String searchText, String category) {
        filteredList.clear();
        for (int i = 0; i < Event.eventsArrayList.size(); i++) {
            Event event = Event.eventsArrayList.get(i);
            boolean matchesSearch = event.getmTitle().toLowerCase()
                    .contains(searchText.toLowerCase());
            boolean matchesCategory = category.equals("All") ||
                    event.getmCategory().equals(category);
            if (matchesSearch && matchesCategory) {
                filteredList.add(event);
            }
        }
        eventAdapter.notifyDataSetChanged();
    }
}