package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class KcalMenu extends AppCompatActivity {

    TextView kcalCount_label;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcal_menu);
        kcalCount_label = findViewById(R.id.kcalCount);

        calculateRecommendedCaloriesForGoal(User.getGoal());

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
                    Integer kcalCount = (int) (10* User.getWeight() + 6.25 * User.getHeight() -5* User.getAge() + 5);
                    User.setKcalCount( kcalCount);
                    kcalCount_label.setText(kcalCount.toString());
                }
                else{
                    Integer kcalCount = (int) (10*User.getWeight() + 6.25 *User.getHeight() - 5 * User.getAge() - 161);
                    User.setKcalCount(kcalCount);
                    kcalCount_label.setText(kcalCount.toString());
                }



                break;
            default:
                //code

        }


    }
}