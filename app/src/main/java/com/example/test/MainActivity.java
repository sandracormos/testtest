package com.example.test;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView height;
    TextView weight;

    String weightStatus;
    TextView status;

    ImageView chart;
    ImageView needle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        final TextView resultText = findViewById(R.id.result);
        status = findViewById(R.id.status);
        chart = findViewById(R.id.chart); // Make sure the ID matches your layout
        needle = findViewById(R.id.needle);

        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onClick(View v) {
                // Get the user's input for height and weight
                String heightStr = height.getText().toString();
                String weightStr = weight.getText().toString();

                // Check if the input fields are not empty
                if (heightStr.isEmpty() || weightStr.isEmpty()) {
                    resultText.setTextSize(20);
                    resultText.setText("Please enter both height and weight");
                    return;
                }

                // Convert the input strings to numeric values
                double heightValue = Double.parseDouble(heightStr);
                double weightValue = Double.parseDouble(weightStr);

                // Calculate the BMI
                double heightInMeters = heightValue / 100.0; // Convert height from cm to meters
                double bmi = weightValue / (heightInMeters * heightInMeters);

                if (bmi < 18.5) {
                    weightStatus = "Underweight";
                } else if (bmi < 24.9 && bmi > 18.5) {
                    weightStatus = "Normal weight";
                } else if (bmi < 29.9 && bmi > 25) {
                    weightStatus = "Overweight";
                } else if (bmi >= 30) {
                    weightStatus = "Obesity";
                }

                // Display the calculated BMI
                resultText.setText(String.format("Your BMI: %.2f", bmi));
                status.setText(String.format("( %s )", weightStatus));

                // Calculate the rotation angle for the needle based on BMI and update the needle's rotation
                float rotationAngle = calculateRotationAngle(bmi);
                needle.setRotation(rotationAngle);
            }
        });
    }

    // Calculate the rotation angle based on the BMI value
    private float calculateRotationAngle(double bmi) {
        // Define your logic here to map BMI values to rotation angles
        // Adjust this logic based on your chart design.
        // This is just a placeholder logic; replace it with your own.

        float minBmi = 10.0f;
        float maxBmi = 30.0f;
        float minAngle = -45.0f; // Corresponds to BMI 10
        float maxAngle = 45.0f;  // Corresponds to BMI 30

        float angleRange = maxAngle - minAngle;
        float bmiRange = maxBmi - minBmi;

        // Calculate the rotation angle
        return minAngle + (float) ((bmi - minBmi) / bmiRange * angleRange);
    }
}
