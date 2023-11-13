package com.example.test;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ScanMeal extends AppCompatActivity {

    Button btn_scan;
    TextView tx;

    String barcode;
//    private static final String APP_ID="3f4bd6c4";
//    private static final String API_KEY = "4a3dc4f14378a3b2c6562d784da31153";

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



    public void sendRequest(){

        String myApiKey = "xxuyc3plheqm4g7uqgxxwp418t54m2";
        String url = "https://api.barcodelookup.com/v3/products?barcode=" + barcode + "&key=" + myApiKey;
        String API_URL = url + barcode;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                String responseRawText = response.toString();
                tx.setText(response.toString());

                Gson gson = new Gson();
                ResponseProducts foodData = gson.fromJson(responseRawText, ResponseProducts.class);
                Map<String, Double> myMap = getNutritionalValues(foodData.products.get(0).nutrition_facts);
                //tx.setText(foodData.products.get(0).nutrition_facts);

                tx.setText((CharSequence) myMap);

                // Now you can use the 'foodData' object as needed

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tx.setText("error "+error.toString());
            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            Log.i(e.toString(), "MyTag");
        }

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
            barcode= result.getContents();
            sendRequest();
        }
    });

//    private void handleScannedBarcode(String barcode) {
//        // Make the API request
////        String result = SearchForFood(barcode);
////        result = "";
//    }


    private Map<String, Double> getNutritionalValues(String nutritionsFacts) {
//
//        Map<String, Nutrient> nutrientMap = getNutritionalValues(nutritionFacts);
//
//        // Print the resulting map
//        for (Map.Entry<String, Nutrient> entry : nutrientMap.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
//        String[] resultArray = nutritionsFacts.split(",");
//        for (String entry : resultArray){
//            if ()
//        }

        Map<String, Double> resultMap = new HashMap<>();

        // Process each part of the split array
        for (String entry : resultArray) {
            // Split each entry by space
            String[] keyValue = entry.split(" ");

            // Extract the key and value
            if (keyValue[0].length() >= 2) {
                // Extract the key and value
                String key = keyValue[0];
                Double value = Double.parseDouble(keyValue[1]);

                // Put the key-value pair into the map
                resultMap.put(key, value);
            } else {
                String key = keyValue[1];
                Double value = Double.parseDouble(keyValue[2]);
                resultMap.put(key, value);
            }

            // Print the resulting map
            System.out.println(resultMap);


        }
        return resultMap;

    }
}


