package com.example.a1221240_courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText editTextRegisterEmail;
    EditText editTextFirstName;
    EditText editTextLastName;
    EditText editTextRegisterPassword;
    EditText editTextConfirmPassword;
    EditText editTextPhone;

    Spinner spinnerGender;
    Spinner spinnerMajor;

    Button buttonRegister;

    DatabaseHelper databaseHelper;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextRegisterEmail = (EditText) findViewById(R.id.editTextRegisterEmail);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextRegisterPassword = (EditText) findViewById(R.id.editTextRegisterPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);

        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        spinnerMajor = (Spinner) findViewById(R.id.spinnerMajor);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        databaseHelper = new DatabaseHelper(this);
        loginPreferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        loginEditor = loginPreferences.edit();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextRegisterEmail.getText().toString();
                String firstName = editTextFirstName.getText().toString();
                String lastName = editTextLastName.getText().toString();
                String password = editTextRegisterPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                String phone = editTextPhone.getText().toString();
                String gender = spinnerGender.getSelectedItem().toString();
                String major = spinnerMajor.getSelectedItem().toString();

                if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
                        || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(SignUpActivity.this,
                            "Please fill all fields", Toast.LENGTH_SHORT).show();

                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(SignUpActivity.this,
                            "Invalid email", Toast.LENGTH_SHORT).show();

                } else if (firstName.length() < 3) {
                    Toast.makeText(SignUpActivity.this,
                            "First name must be at least 3 characters", Toast.LENGTH_SHORT).show();

                } else if (lastName.length() < 3) {
                    Toast.makeText(SignUpActivity.this,
                            "Last name must be at least 3 characters", Toast.LENGTH_SHORT).show();

                } else if (password.length() < 6) {
                    Toast.makeText(SignUpActivity.this,
                            "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();

                } else if (!hasLetter(password)) {
                    Toast.makeText(SignUpActivity.this,
                            "Password must contain at least one letter", Toast.LENGTH_SHORT).show();

                } else if (!hasNumber(password)) {
                    Toast.makeText(SignUpActivity.this,
                            "Password must contain at least one number", Toast.LENGTH_SHORT).show();

                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignUpActivity.this,
                            "Passwords do not match", Toast.LENGTH_SHORT).show();

                } else if (databaseHelper.checkEmail(email)) {
                    Toast.makeText(SignUpActivity.this,
                            "Email already registered", Toast.LENGTH_SHORT).show();

                } else {
                    String encryptedPassword = Base64.encodeToString(
                            password.getBytes(), Base64.DEFAULT);

                    long result = databaseHelper.addUser(email, encryptedPassword,
                            firstName, lastName, phone, gender, major);

                    if (result != -1) {
                        loginEditor.putString("currentEmail", email);
                        loginEditor.commit();

                        Toast.makeText(SignUpActivity.this,
                                "Registration successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this,
                                "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public boolean hasLetter(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) return true;
        }
        return false;
    }

    public boolean hasNumber(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) return true;
        }
        return false;
    }
}