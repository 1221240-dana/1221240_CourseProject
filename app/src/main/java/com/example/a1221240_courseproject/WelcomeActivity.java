package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    Button buttonConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        buttonConnect = (Button) findViewById(R.id.buttonConnect);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WelcomeActivity.this,
                        "Connection will be added in Step 2",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(WelcomeActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}