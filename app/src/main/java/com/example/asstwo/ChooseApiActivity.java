package com.example.asstwo;
//TasnimAyed_1191332

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseApiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_api);

        ImageButton api1Button = findViewById(R.id.api1);
        ImageButton api2Button = findViewById(R.id.api2);

        api1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new activity for API 1
                Intent intent = new Intent(ChooseApiActivity.this, ApiOneActivity.class);
                startActivity(intent);
            }
        });

        api2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open new activity for API 2
                Intent intent = new Intent(ChooseApiActivity.this, ApiTwoActivity.class);
                startActivity(intent);
            }
        });
    }
}
