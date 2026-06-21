package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    EditText editTextProfileFirstName;
    EditText editTextProfileLastName;
    EditText editTextProfilePhone;
    EditText editTextProfilePassword;
    EditText editTextProfileConfirmPassword;
    Button buttonUpdateProfile;
    Button buttonBackFromProfile;

    DatabaseHelper databaseHelper;
    SharedPreferences loginPreferences;
    String currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editTextProfileFirstName = (EditText) findViewById(R.id.editTextProfileFirstName);
        editTextProfileLastName = (EditText) findViewById(R.id.editTextProfileLastName);
        editTextProfilePhone = (EditText) findViewById(R.id.editTextProfilePhone);
        editTextProfilePassword = (EditText) findViewById(R.id.editTextProfilePassword);
        editTextProfileConfirmPassword = (EditText) findViewById(R.id.editTextProfileConfirmPassword);
        buttonUpdateProfile = (Button) findViewById(R.id.buttonUpdateProfile);
        buttonBackFromProfile = (Button) findViewById(R.id.buttonBackFromProfile);

        databaseHelper = new DatabaseHelper(this);
        loginPreferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        currentEmail = loginPreferences.getString("currentEmail", "");

        Cursor cursor = databaseHelper.getUserInfo(currentEmail);
        if (cursor.moveToFirst()) {
            editTextProfileFirstName.setText(cursor.getString(3));
            editTextProfileLastName.setText(cursor.getString(4));
            editTextProfilePhone.setText(cursor.getString(5));
        }
        cursor.close();

        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = editTextProfileFirstName.getText().toString();
                String lastName = editTextProfileLastName.getText().toString();
                String phone = editTextProfilePhone.getText().toString();
                String password = editTextProfilePassword.getText().toString();
                String confirmPassword = editTextProfileConfirmPassword.getText().toString();

                if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(ProfileActivity.this,
                            "First name, last name and phone cannot be empty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (firstName.length() < 3) {
                    Toast.makeText(ProfileActivity.this,
                            "First name must be at least 3 characters",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (lastName.length() < 3) {
                    Toast.makeText(ProfileActivity.this,
                            "Last name must be at least 3 characters",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String encryptedPassword = "";

                if (!password.isEmpty()) {
                    if (password.length() < 6) {
                        Toast.makeText(ProfileActivity.this,
                                "Password must be at least 6 characters",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    boolean hasLetter = false;
                    boolean hasNumber = false;
                    for (int i = 0; i < password.length(); i++) {
                        if (Character.isLetter(password.charAt(i))) hasLetter = true;
                        if (Character.isDigit(password.charAt(i))) hasNumber = true;
                    }

                    if (!hasLetter || !hasNumber) {
                        Toast.makeText(ProfileActivity.this,
                                "Password must contain at least 1 letter and 1 number",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!password.equals(confirmPassword)) {
                        Toast.makeText(ProfileActivity.this,
                                "Passwords do not match",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    encryptedPassword = Base64.encodeToString(
                            password.getBytes(), Base64.DEFAULT);
                }

                if (encryptedPassword.isEmpty()) {
                    Cursor cursor = databaseHelper.getUserInfo(currentEmail);
                    if (cursor.moveToFirst()) {
                        encryptedPassword = cursor.getString(2);
                    }
                    cursor.close();
                }

                databaseHelper.updateUser(currentEmail, firstName, lastName,
                        phone, encryptedPassword);

                Toast.makeText(ProfileActivity.this,
                        "Profile updated successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

        buttonBackFromProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}