package com.example.test;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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
    TextView tx;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_meal);
        tx = findViewById(R.id.responseTextView);
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

    public String SearchForFood(String barcode) {

        new HttpGetRequest().execute("https://api.nutritionix.com/v1_1/item?upc=49000036756&appId=[3f4bd6c4]&appKey=[4a3dc4f14378a3b2c6562d784da31153]");
        return "";
    }

    private class HttpGetRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                // Create a URL object from the provided URL
                URL url = new URL(params[0]);

                // Open a connection using HttpURLConnection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Set the request method to GET
                connection.setRequestMethod("GET");

                // Get the response code
                int responseCode = connection.getResponseCode();

                // Read the response from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                // Return the response as a string
                return response.toString();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // Update the UI with the response
            if (result != null) {
                responseTextView.setText(result);
            } else {
//                responseTextView.setText("Error occurred during the GET request");
            }
        }
    }

}
