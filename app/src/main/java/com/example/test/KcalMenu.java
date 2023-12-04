package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


enum Meal {Breakfast, Lunch, Dinner, Snacks}

public class KcalMenu extends AppCompatActivity {

    Meal myMeal;
    TextView kcalCount_label;

    ImageButton breakfast_button;
    ImageButton lunch_button;
    ImageButton dinner_button;
    ImageButton snacks_button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcal_menu);
        kcalCount_label = findViewById(R.id.kcalCount);

        breakfast_button = findViewById(R.id.breakfast_button);
        lunch_button = findViewById(R.id.lunch_button);
        dinner_button = findViewById(R.id.dinner_button);
        snacks_button = findViewById(R.id.snacks_button);




        calculateRecommendedCaloriesForGoal(User.getGoal());
        configureScanBreakfast();
        configureScanLunch();
        configureScanDinner();
        configureScanSnacks();



    }

    @Override
    protected void onStart() {
        super.onStart();
        kcalCount_label.setText(User.getKcalCount().toString());

    }

    private void configureScanBreakfast() {
        breakfast_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(KcalMenu.this, ScanMeal.class)));
                myMeal = Meal.Breakfast;
            }
        });
    }
    private void configureScanLunch() {
        lunch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(KcalMenu.this, ScanMeal.class)));

                myMeal = Meal.Lunch;
            }
        });
    }
    private void configureScanDinner() {
        dinner_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(KcalMenu.this, ScanMeal.class)));

                myMeal = Meal.Dinner;
            }
        });
    }
    private void configureScanSnacks() {
        snacks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity((new Intent(KcalMenu.this, ScanMeal.class)));

                myMeal = Meal.Snacks;
            }
        });
    }

    public void calculateRecommendedCaloriesForGoal(Goal myGoal){
        switch (myGoal){
            case weightLoss:
                //code


                break;

            case bodybuilding:
                //code


                break;

            case wellness:

                if(User.getGender() == Gender.Male){
                    Double kcalCount = Double.valueOf(10* User.getWeight() + 6.25 * User.getHeight() -5* User.getAge() + 5);
                    User.setKcalCount( kcalCount);
                    kcalCount_label.setText(kcalCount.toString());
                }
                else{
                    Double kcalCount = Double.valueOf (10*User.getWeight() + 6.25 *User.getHeight() - 5 * User.getAge() - 161);
                    User.setKcalCount(kcalCount);
                    kcalCount_label.setText(kcalCount.toString());
                }



                break;
            default:
                //code

        }


    }
}