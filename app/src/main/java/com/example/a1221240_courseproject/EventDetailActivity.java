package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventDetailActivity extends AppCompatActivity {

    Button buttonBackFromDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        buttonBackFromDetail = (Button) findViewById(R.id.buttonBackFromDetail);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String category = getIntent().getStringExtra("category");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String location = getIntent().getStringExtra("location");
        int seats = getIntent().getIntExtra("seats", 0);

        EventDetailFragment fragment = EventDetailFragment.newInstance(
                title, description, category, date, time, location, seats);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();

        buttonBackFromDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventDetailActivity.this, EventListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}