package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Navigation_Menu extends AppCompatActivity {
    ImageButton fasting_tracker_button;
    ImageButton water_intake_button;
    ImageButton journal_button;
    ImageButton settings_button;
    ImageButton bmi_button;
    ImageButton gps_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);

        water_intake_button = findViewById(R.id.water_intake_button);
        journal_button = findViewById(R.id.journal_button);
        settings_button = findViewById(R.id.settings_button);
        bmi_button = findViewById(R.id.bmi_button);
        gps_button = findViewById(R.id.gps_button);



        journal_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), KcalMenu.class));
            }
        });

        bmi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }
}