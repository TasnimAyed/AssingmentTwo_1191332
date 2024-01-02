package com.example.asstwo;
//TasnimAyed_1191332
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiOneActivity extends AppCompatActivity {

    EditText countryEditText;
    ScrollView scrollView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_one);

        countryEditText = findViewById(R.id.country);
        scrollView = findViewById(R.id.scrollView2);
        linearLayout = findViewById(R.id.linearLayout);

        Button searchButton = findViewById(R.id.search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country = countryEditText.getText().toString().trim();
                if (!country.isEmpty()) {
                    linearLayout.removeAllViews();
                    fetchDataFromAPI(country);
                }
            }
        });
    }

    private void fetchDataFromAPI(String country) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://raw.githubusercontent.com/Hipo/university-domains-list/master/world_universities_and_domains.json?country=" + country;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            displayUniversities(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }

    private void displayUniversities(JSONArray universities) throws JSONException {
        float textSize=20;
        int counter = 1;
        int blackColor = Color.BLACK;
        for (int i = 0; i < universities.length(); i++) {
            JSONObject university = universities.getJSONObject(i);
            String universityCountry = university.getString("country");
            if (universityCountry.equalsIgnoreCase(countryEditText.getText().toString().trim())) {
                String universityName = university.getString("name");

                TextView textView = new TextView(this);
                textView.setText(universityName);
                textView.setText(counter + " )   " + universityName);
                textView.setTextSize(textSize);
                textView.setTextColor(blackColor);
                linearLayout.addView(textView);
                counter++;

            }
        }
    }
}
