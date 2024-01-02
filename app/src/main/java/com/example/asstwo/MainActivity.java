package com.example.asstwo;
//TasnimAyed_1191332

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve stored credentials
                SharedPreferences sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE);
                String savedEmail = sharedPreferences.getString("email", "");
                String savedPassword = sharedPreferences.getString("password", "");

                // Get entered credentials
                EditText editTextEmail = findViewById(R.id.Email);
                EditText editTextPassword = findViewById(R.id.Password);
                String enteredEmail = editTextEmail.getText().toString();
                String enteredPassword = editTextPassword.getText().toString();

                // Check if entered credentials match stored credentials
                if (enteredEmail.equals(savedEmail) && enteredPassword.equals(savedPassword)) {
                    // Login successful, navigate to HomeActivity
                    Intent intent = new Intent(MainActivity.this, ChooseApiActivity.class);
                    startActivity(intent);
                } else {
                    // Show login failed message or handle accordingly
                    // For example, display a toast message
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
