package com.example.test;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ScanMeal extends AppCompatActivity {

    Button btn_scan;
    TextView tx;
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
//            scanCode();
        });

        String API_URL = "https://trackapi.nutritionix.com/v2/search/item?nix_item_id=513fc9e73fe3ffd40300109f";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(API_URL, null, new Response.Listener<JSONObject>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONObject response) {
                String responseRawText = response.toString();
                tx.setText(response.toString());

                    Gson gson = new Gson();
                    NutritionIxItemResponse foodData = gson.fromJson(responseRawText, NutritionIxItemResponse.class);
                tx.setText(foodData.foods.get(0).getFoodName());
                    // Now you can use the 'foodData' object as needed

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tx.setText("error ");
            }

        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("x-app-id", "3f4bd6c4");
                headers.put("x-app-key", "d227dee70508049f901324317f0e4fc4");
                return headers;
            }
        };

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(jsonObjectRequest);
        }catch (Exception e)
        {
            Log.i(e.toString(), "MyTag");
        }





    }



//
//    private void scanCode() {
//        ScanOptions options = new ScanOptions();
//        options.setPrompt("Volume up to flash on");
//        options.setBeepEnabled(true);
//        options.setOrientationLocked(true);
//        options.setCaptureActivity(CaptureActivity.class);
//        barLauncher.launch(options);
//    }
//
//    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
//        if (result.getContents() != null) {
//            // Handle the scanned barcode
//            handleScannedBarcode(result.getContents());
//        }
//    });
//
//    private void handleScannedBarcode(String barcode) {
//        // Make the API request
////        String result = SearchForFood(barcode);
////        result = "";
//    }






}


