package com.example.test;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Map;


enum Meal {Breakfast, Lunch, Dinner, Snacks}

public class KcalMenu extends AppCompatActivity {

    public static Meal myMeal;
    TextView kcalCount_label;
    TextView kcal_eaten;

    TextView breakfast_meal;
    TextView lunch_meal;
    TextView dinner_meal;
    TextView snacks_meal;

    ImageButton breakfast_button;
    ImageButton lunch_button;
    ImageButton dinner_button;
    ImageButton snacks_button;
    ScrollView parentScrollView;


    TextView journal_day;
    ImageButton next_day;
    ImageButton day_before;

    Integer counterDays = 0;

    ProgressBar progress_bar_protein;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcal_menu);
        kcalCount_label = findViewById(R.id.kcalCount);
        kcal_eaten = findViewById(R.id.kcal_eaten);

        breakfast_meal = findViewById(R.id.breakfast_meal);
        lunch_meal = findViewById(R.id.lunch_meal);
        dinner_meal = findViewById(R.id.dinner_meal);
        snacks_meal = findViewById(R.id.snacks_meal);


        breakfast_button = findViewById(R.id.breakfast_button);
        lunch_button = findViewById(R.id.lunch_button);
        dinner_button = findViewById(R.id.dinner_button);
        snacks_button = findViewById(R.id.snacks_button);

        parentScrollView = (ScrollView) findViewById(R.id.parentScrollView);

        journal_day = findViewById(R.id.journal_date);
        next_day = findViewById(R.id.next_day);
        day_before = findViewById(R.id.day_before);

        progress_bar_protein = findViewById(R.id.progress_bar_protein);




        calculateRecommendedCaloriesForGoal(User.getGoal());
        configureScanBreakfast();
        configureScanLunch();
        configureScanDinner();
        configureScanSnacks();

        next_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterDays +=1;

                showDate();
                RefreshMealTextviews();

            }
        });

        day_before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterDays -=1;
                showDate();
                RefreshMealTextviews();
            }

        });

        showDate();
     
    }




    private void RefreshMealTextviews(){
        breakfast_meal.setText("");
        lunch_meal.setText("");
        dinner_meal.setText("");
        snacks_meal.setText("");
        DayEntry dayEntry = User.journal.get(User.currentJournalDay);
        if(dayEntry != null){
            for ( Map.Entry<ResponseProducts, Double> prod : dayEntry.getBreakfastList().entrySet()) {
                String s= getMealProducts(prod);
                breakfast_meal.append(s.toString());
            }
            for ( Map.Entry<ResponseProducts, Double> prod : dayEntry.getLunchList().entrySet()) {
                String s= getMealProducts(prod);
                lunch_meal.append(s.toString());
            }
            for ( Map.Entry<ResponseProducts, Double> prod : dayEntry.getDinnerList().entrySet()) {
                String s= getMealProducts(prod);
                dinner_meal.append(s.toString());
            }
            for ( Map.Entry<ResponseProducts, Double> prod : dayEntry.getSnacksList().entrySet()) {
                String s= getMealProducts(prod);
                snacks_meal.append(s.toString());
            }
        }
    }

    private String getMealProducts( Map.Entry<ResponseProducts, Double> prod){
        Map<String, Double> myMap= ScanMeal.getNutritionalValues(prod.getKey().products.get(0).nutrition_facts);
        Double numberOfKcal = myMap.get("Energy");
        Double total = numberOfKcal * prod.getValue();

        String s =" "+ prod.getKey().products.get(0).title + "    " + total.toString() + "\n";
        return s;
    }





    private void updateRemainingKcal(){
        DayEntry dayEntry = User.journal.get(User.currentJournalDay);
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
        RefreshMealTextviews();

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

    private Double calculateBaseCalorieNumber(){
        Double kcalCount=0.0;
        if(User.getGender() == Gender.Male){
             kcalCount = Double.valueOf(10* User.getWeight() + 6.25 * User.getHeight() -5* User.getAge() + 5);

        }
        else{
             kcalCount = Double.valueOf (10*User.getWeight() + 6.25 *User.getHeight() - 5 * User.getAge() - 161);
        }
        return kcalCount;
    };
    public void calculateRecommendedCaloriesForGoal(Goal myGoal){
        Double kcalCount = 0.0;
        Double result = 0.0;
        switch (myGoal){
            case weightLoss:

                kcalCount = calculateBaseCalorieNumber();
                result = kcalCount * 1.25;
                User.setKcalCount( result);
                kcalCount_label.setText(result.toString());



                break;

            case bodybuilding:
                kcalCount = calculateBaseCalorieNumber();
                result = kcalCount + kcalCount* (15/100);
                User.setKcalCount( result);
                kcalCount_label.setText(result.toString());

                break;

            case wellness:
                kcalCount = calculateBaseCalorieNumber();
                User.setKcalCount( kcalCount);
                kcalCount_label.setText(kcalCount.toString());

                break;

        }
    }


    public void showDate(){
        journal_day.setText(LocalDate.now().toString());
        LocalDate date= LocalDate.now().plusDays(counterDays);
        journal_day.setText(date.toString());
        User.currentJournalDay = date;
    };


    public void
}