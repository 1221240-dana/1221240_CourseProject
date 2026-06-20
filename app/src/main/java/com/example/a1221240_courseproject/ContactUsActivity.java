package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactUsActivity extends AppCompatActivity {

    Button buttonCall;
    Button buttonMap;
    Button buttonEmail;
    Button buttonBackFromContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        buttonCall = (Button) findViewById(R.id.buttonCall);
        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonEmail = (Button) findViewById(R.id.buttonEmail);
        buttonBackFromContact = (Button) findViewById(R.id.buttonBackFromContact);

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+970599000000"));
                startActivity(intent);
            }
        });

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:31.9789,35.2136?q=Birzeit+University"));
                startActivity(intent);
            }
        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:contact@birzeit.edu"));
                startActivity(intent);
            }
        });

        buttonBackFromContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactUsActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}