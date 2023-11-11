import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NutritionixApiRequest extends AsyncTask<String, Void, String> {

    private static final String API_URL = "https://api.nutritionix.com/v1_1/item";
    private static final String API_KEY = "361289611376913c977a8536f0a6777d";

    @Override
    protected String doInBackground(String... barcodes) {

        try {
            // Construct the URL with parameters
            String apiUrl = API_URL + "?upc=" + barcodes[0];
            URL url = new URL(apiUrl);

            // Open connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Set request method and headers
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("x-app-id", "YOUR_APP_ID");
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

    @Override
    protected void onPostExecute(String result) {
        // Handle the API response in this method
        if (result != null) {
            Log.d("NutritionixApiRequest", result);
            // Process the result
            // For example, update UI or perform other actions with the result
        } else {
            // Handle the error
            // For example, show an error message to the user
        }
    }

    // Note: The onCancelled method is not used in this example, but you may override it if needed
}
