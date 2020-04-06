package com.example.covidtoolkit;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.android.volley.toolbox.JsonObjectRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(MainActivity.this);
        // array of images
        int images[] = {R.drawable.handwash1, R.drawable.handwash2, R.drawable.handwash3, R.drawable.handwash4};

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_api, R.id.navigation_timer, R.id.navigation_faq)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void corona_data (View view){
        final TextView todayTxt = (TextView) findViewById(R.id.apiToday);
        final TextView yesterdayTxt = (TextView) findViewById(R.id.apiYesterday);
        final EditText countryTxt = (EditText) findViewById(R.id.apiEdit);

        String todayApi = "https://corona.lmao.ninja/countries/" + countryTxt.getText();
        String yesterdayApi = "https://corona.lmao.ninja/yesterday/" + countryTxt.getText();

        JsonObjectRequest arrayRequest = new JsonObjectRequest(Request.Method.GET, todayApi,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                              String countryName = response.getString("country");
                              String cases = response.getString("cases");
                              String todayCases = response.getString("todayCases");
                              String deaths = response.getString("deaths");
                              String todayDeaths = response.getString("todayDeaths");
                              String recovered = response.getString("recovered");
                              String active = response.getString("active");

                              todayTxt.setText(String.format("%s Today\n\nCases: %s \nToday Cases: %s \nDeaths: %s \nToday Deaths: %s \nRecovered: %s \nActive: %s"
                                                             , countryName, cases, todayCases, deaths, todayDeaths, recovered, active));

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        JsonObjectRequest arrayRequest2 = new JsonObjectRequest(Request.Method.GET, yesterdayApi,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String countryName = response.getString("country");
                            String cases = response.getString("cases");
                            String yesterdayCases = response.getString("todayCases");
                            String deaths = response.getString("deaths");
                            String yesterdayDeaths = response.getString("todayDeaths");
                            String recovered = response.getString("recovered");
                            String active = response.getString("active");

                            yesterdayTxt.setText(String.format("%s Yesterday\n\nCases: %s \nYesterday Cases: %s \nDeaths: %s \nYesterday Deaths: %s \nRecovered: %s \nActive: %s"
                                    , countryName, cases, yesterdayCases, deaths, yesterdayDeaths, recovered, active));

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }

                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(arrayRequest);
        requestQueue.add(arrayRequest2);
        //apiTxt.setText(test);
    }

}
