package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    CheckBox checkBoxRememberMe;
    Button buttonLogin;
    Button buttonSignUp;

    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginEditor;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        checkBoxRememberMe = (CheckBox) findViewById(R.id.checkBoxRememberMe);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        loginPreferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        loginEditor = loginPreferences.edit();

        databaseHelper = new DatabaseHelper(this);

        String savedEmail = loginPreferences.getString("email", "");
        editTextEmail.setText(savedEmail);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this,
                            "Please enter email and password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // Admin check
                if (email.equals("admin@admin.com") && password.equals("Admin123!")) {
                    if (checkBoxRememberMe.isChecked()) {
                        loginEditor.putString("email", email);
                        loginEditor.commit();
                    }
                    Toast.makeText(SignInActivity.this,
                            "Admin login successful",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignInActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }

                // Regular user check with encrypted password
                String encryptedPassword = Base64.encodeToString(
                        password.getBytes(), Base64.DEFAULT);

                if (databaseHelper.checkUser(email, encryptedPassword)) {
                    if (checkBoxRememberMe.isChecked()) {
                        loginEditor.putString("email", email);
                        loginEditor.commit();
                    }
                    loginEditor.putString("currentEmail", email);
                    loginEditor.commit();

                    Toast.makeText(SignInActivity.this,
                            "Login successful",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignInActivity.this, MainHomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(SignInActivity.this,
                            "Invalid email or password",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}