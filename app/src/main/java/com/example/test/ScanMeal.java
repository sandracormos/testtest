package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ScanMeal extends AppCompatActivity {



    ImageButton scan_button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_meal);

        scan_button = findViewById(R.id.scan_button);


        configureScanButton();
    }


    private void configureScanButton() {
        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(ScanMeal.this, SetGoalActivity.class)));
            }
        });
    }
}