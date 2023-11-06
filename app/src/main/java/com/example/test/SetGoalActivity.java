package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class SetGoalActivity extends AppCompatActivity {

    RadioButton weightlossbtn;
    RadioButton bodybuildingbtn;

    RadioButton wellnessbtn;

    TextView quote;


    Goal myGoal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        weightlossbtn = findViewById(R.id.weightlossbtn);
        bodybuildingbtn = findViewById(R.id.bodybuildingbtn);
        wellnessbtn = findViewById(R.id.wellnessbtn);
        quote = findViewById(R.id.quote);





        //quotes
        weightlossbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                quote.setText("Every step counts. Progress, not perfection.");
                myGoal = Goal.weightLoss;
            }
        });
        bodybuildingbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                quote.setText("Strength comes from within. Lift, grow, repeat.");
                myGoal = Goal.bodybuilding;
            }

        });

        wellnessbtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                quote.setText("Health is wealth. Take care of your greatest asset.");
                myGoal= Goal.wellness;
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
                //code


                break;
            default:
                //code

        }
    }


}