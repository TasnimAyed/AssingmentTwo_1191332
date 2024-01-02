package com.example.asstwo;
//TasnimAyed_1191332

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiTwoActivity extends AppCompatActivity {

    EditText nameEditText;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_two);

        nameEditText = findViewById(R.id.name);
        resultTextView = findViewById(R.id.Result);

        Button analyzeButton = findViewById(R.id.button);
        analyzeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                if (!name.isEmpty()) {
                    fetchGenderFromAPI(name);
                }
            }
        });
    }

    private void fetchGenderFromAPI(String name) {
        String apiUrl = "https://api.genderize.io/?name=" + name;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String gender = response.getString("gender");
                            // Display the gender in the Result TextView
                            resultTextView.setText(gender);
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

        queue.add(jsonObjectRequest);
    }
}
