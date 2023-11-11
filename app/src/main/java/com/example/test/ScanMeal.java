package com.example.test;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScanMeal extends AppCompatActivity {

    Button btn_scan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_meal);

        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v -> {
            scanCode();
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureActivity.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            // Handle the scanned barcode
            handleScannedBarcode(result.getContents());
        }
    });

    private void handleScannedBarcode(String barcode) {
        // Make the API request
        String result = SearchForFood(barcode);
        result = "";
    }

    private static final String API_URL = "https://api.nutritionix.com/v1_1/item";
    private static final String APP_ID="3f4bd6c4";
    private static final String API_KEY = "4a3dc4f14378a3b2c6562d784da31153";

    public static String SearchForFood(String barcode) {

        try {
            // Construct the URL with parameters
            String apiUrl = API_URL + "?upc=" + barcode;
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Set request method and headers
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("x-app-id", APP_ID);
            urlConnection.setRequestProperty("x-app-key", API_KEY);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Return the response
            return response.toString();
        } catch (IOException e) {
            Log.e("NutritionixApiRequest", "Error making API request", e);
            return null;
        }
    }

}
