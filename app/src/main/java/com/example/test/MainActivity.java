package com.example.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.test.R;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView height;
    TextView weight;
    TextView status;
    ImageView chart;
    ImageView needle;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        final TextView resultText = findViewById(R.id.result);
        status = findViewById(R.id.status);
        chart = findViewById(R.id.chart);
        needle = findViewById(R.id.needle);

        // Define the minimum and maximum X-axis positions for the needle
        final int minPosition = 0;
        final int maxPosition = chart.getWidth() - needle.getWidth();
        float myNeedlePos = needle.getX();





        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                String heightStr = height.getText().toString();
                String weightStr = weight.getText().toString();

                if (heightStr.isEmpty() || weightStr.isEmpty()) {
                    resultText.setTextSize(20);
                    resultText.setText("Please enter both height and weight");
                    return;
                }

                double heightValue = Double.parseDouble(heightStr);
                double weightValue = Double.parseDouble(weightStr);

                double heightInMeters = heightValue / 100.0;
                double bmi = weightValue / (heightInMeters * heightInMeters);

                String weightStatus;
                if (bmi < 18.5) {
                    weightStatus = "Underweight";
                } else if (bmi < 24.9) {
                    weightStatus = "Normal weight";
                } else if (bmi < 29.9) {
                    weightStatus = "Overweight";
                } else {
                    weightStatus = "Obesity";
                }


                resultText.setText(String.format("Your BMI: %.2f", bmi));
                status.setText(String.format("( %s )", weightStatus));

                // Calculate the X-axis position for the needle based on BMI
                float position = calculatePosition(bmi, maxPosition);

                // Set the X-axis position of the needle

            }
        });
    }

    // Calculate the X-axis position based on the BMI value and chart design
    private float calculatePosition(double bmi, int maxPosition) {
        // Define the chart's minimum and maximum BMI values

        if(bmi < 10)
            bmi = 10;
        else if(bmi > 30)
            bmi = 30;

        float normalizedBmi = (float) (bmi - 10) / (30 - 10);
        float position = normalizedBmi * 1000 - 500;

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) needle.getLayoutParams();
        params.horizontalBias = normalizedBmi; // here is one modification for example. modify anything else you want :)
        needle.setLayoutParams(params); // request the view to use the new modified params

        return position;

//        double minBmi = 10.0;
//        double maxBmi = 30.0;
//        float position = 0;
//
//        if (bmi > 30) {
//            bmi = 30;
//        } else if (bmi < 10) {
//            bmi = 10;
//        }
//        // Calculate the X-axis position based on the user's BMI
//        if (bmi < 20) {
//            float aux = (float) ((bmi - minBmi) / 10) * 500;  //  450
//            position = (float) -500+ aux;
//        }
//        if (bmi > 20) {
//            float aux = (float) ((bmi - minBmi) / 10)*500;  //  9/10
//            position = aux - 500;
//
//
//        }
    }


}
