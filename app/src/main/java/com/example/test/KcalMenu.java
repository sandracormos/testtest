package com.example.test;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;


enum Meal {Breakfast, Lunch, Dinner, Snacks}

public class KcalMenu extends AppCompatActivity {

    public static Meal myMeal;
    TextView kcalCount_label;
    TextView kcal_eaten;

    TextView breakfast_meal;

    ImageButton breakfast_button;
    ImageButton lunch_button;
    ImageButton dinner_button;
    ImageButton snacks_button;

    ScrollView  childScrollView;

    ScrollView parentScrollView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcal_menu);
        kcalCount_label = findViewById(R.id.kcalCount);
        kcal_eaten = findViewById(R.id.kcal_eaten);
        breakfast_meal = findViewById(R.id.breakfast_meal);


        breakfast_button = findViewById(R.id.breakfast_button);
        lunch_button = findViewById(R.id.lunch_button);
        dinner_button = findViewById(R.id.dinner_button);
        snacks_button = findViewById(R.id.snacks_button);


        childScrollView = (ScrollView) findViewById(R.id.breakfast_meals_scrollview);
        parentScrollView = (ScrollView) findViewById(R.id.parentScrollView);




        calculateRecommendedCaloriesForGoal(User.getGoal());
        configureScanBreakfast();
        configureScanLunch();
        configureScanDinner();
        configureScanSnacks();

     
    }


    private void addProductsToMeal(){
        DayEntry dayEntry = User.journal.get(LocalDate.now());
        if(dayEntry != null){
            for ( Map.Entry<ResponseProducts, Double> prod : dayEntry.getBreakfastList().entrySet()) {



                Double kcalPerProd = prod.getValue();
                Map<String, Double> myMap= ScanMeal.getNutritionalValues(prod.getKey().products.get(0).nutrition_facts);
                Double numberOfKcal = myMap.get("Energy");
                Double total = numberOfKcal * prod.getValue();

                String s = prod.getKey().products.get(0).title + "    " + total.toString() + "\n";
                breakfast_meal.append(s);


            }
        }

    }



    private void updateRemainingKcal(){
        DayEntry dayEntry = User.journal.get(LocalDate.now());
        if(dayEntry != null){
            Double kcalRemaining = User.getKcalCount() - dayEntry.calculateUsedKcalPerDay();
            kcalCount_label.setText(kcalRemaining.toString());
            kcal_eaten.setText(dayEntry.calculateUsedKcalPerDay().toString());
        }
        else
        {
            kcalCount_label.setText(User.getKcalCount().toString());
        }
    }



    @Override
    protected void onStart() {
         super.onStart();
        updateRemainingKcal();
        addProductsToMeal();

    }

    private void configureScanBreakfast() {
        breakfast_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMeal = Meal.Breakfast;
                startActivity((new Intent(KcalMenu.this, ScanMeal.class)));

            }
        });
    }
    private void configureScanLunch() {
        lunch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMeal = Meal.Lunch;
                startActivity((new Intent(KcalMenu.this, ScanMeal.class)));

            }
        });
    }
    private void configureScanDinner() {
        dinner_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMeal = Meal.Dinner;
                startActivity((new Intent(KcalMenu.this, ScanMeal.class)));

            }
        });
    }
    private void configureScanSnacks() {
        snacks_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myMeal = Meal.Snacks;
                startActivity((new Intent(KcalMenu.this, ScanMeal.class)));

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