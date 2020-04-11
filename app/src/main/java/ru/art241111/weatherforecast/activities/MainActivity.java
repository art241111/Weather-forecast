package ru.art241111.weatherforecast.activities;

import androidx.appcompat.app.AppCompatActivity;
import ru.art241111.weatherforecast.R;
import ru.art241111.weatherforecast.model.Weather;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private final String URL = "https://api.openweathermap.org/data/2.5/weather";
    private final String KEY = "015e5b2ea69e84f4e95e781aeed4b580";

    private final String URL_WITH_KEY = URL + "?appid=" + KEY;
    private final String TEMPERATURE_UNITS = "&units=metric";
    private String LANG = "&lang=" + Locale.getDefault().getLanguage();

    private ImageButton ib_enter;

    private EditText et_city;

    private TextView tv_data;
    private TextView tv_temperature;
    private TextView tv_condition;
    private TextView tv_analysis;

    private ImageView iv_poster_weight;

    private RequestQueue requestQueue;
    Context context = this;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializationView();

        requestQueue = Volley.newRequestQueue(context);
        sharedPreferences
                = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);


        if(sharedPreferences.contains("city")){
            Weather weather = new Weather();

            weather.setCity(sharedPreferences.getString("city", ""));
            weather.setCondition(sharedPreferences.getString("condition", ""));
            weather.setTemperature(sharedPreferences.getString("temperature", ""));
            weather.setPosterWeather(sharedPreferences.getString("poster", ""));
            weather.setDate(sharedPreferences.getString("date", ""));

            drawView(weather);
        } else {
            et_city.setText("Санкт-Петербург");
            getWeather(et_city.getText().toString().trim());
        }


        setListenerOnButtonEnter();
        setListenerOnEditText();





    }

    private void initializationView() {
        ib_enter = findViewById(R.id.ib_enter);

        et_city = findViewById(R.id.et_city);

        tv_data = findViewById(R.id.tv_data);
        tv_temperature = findViewById(R.id.tv_temperature);
        tv_condition = findViewById(R.id.tv_condition);
        tv_analysis = findViewById(R.id.tv_analysis);

        iv_poster_weight = findViewById(R.id.iv_poster_weather);
    }


    private void setListenerOnEditText() {
        et_city.setOnKeyListener(new View.OnKeyListener()
                                 {
                                     public boolean onKey(View v, int keyCode, KeyEvent event)
                                     {
                                         if(event.getAction() == KeyEvent.ACTION_DOWN &&
                                                 (keyCode == KeyEvent.KEYCODE_ENTER))
                                         {
                                             getWeather(et_city.getText().toString().trim());
                                             return true;
                                         }
                                         return false;
                                     }
                                 }
        );
    }

    private void setListenerOnButtonEnter() {
        ib_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWeather(et_city.getText().toString().trim());
            }
        });
    }



    public void getWeather(String city){
        String url = URL_WITH_KEY + "&q=" + city + TEMPERATURE_UNITS + LANG;
        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        readJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,
                                context.getResources().getString(R.string.error_write_city),
                                Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }

    public void getWeather(Integer id){
        String url = URL_WITH_KEY + "&id=" + id + TEMPERATURE_UNITS + LANG;
        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        readJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,
                                context.getResources().getString(R.string.error_write_id),
                                Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }

    public void getWeather(double lon, double lat){
        String url = URL_WITH_KEY + "&lat=" + lat + "&lon=" + lon + TEMPERATURE_UNITS + LANG;
        JsonObjectRequest request =
                new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        readJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,
                                context.getResources().getString(R.string.error_write_coordinate),
                                Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request);
    }

    private void readJSON(JSONObject response){
        Log.d("response", response.toString());

        try {
            Weather weather = new Weather();
            JSONArray jsonArray = response.getJSONArray("weather");
            JSONObject weatherObject = jsonArray.getJSONObject(0);
            String condition = weatherObject.getString("description");
            String icon = weatherObject.getString("icon");

            String temperature = response.getJSONObject("main").getString("temp");
            response.getJSONObject("main").getString("temp");

            temperature = String.valueOf(Math.round(Float.parseFloat(temperature)));

            String data = response.getString("dt");
            String city = response.getString("name");

            weather.setCondition(condition);
            weather.setPosterWeather("http://openweathermap.org/img/wn/" + icon + "@2x.png");

            weather.setTemperature(temperature);
            weather.setDateUTC(data);
            weather.setCity(city);

            weather.setCity(et_city.getText().toString().trim());
            drawView(weather);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void drawView(Weather weather) {
        et_city.setText(weather.getCity());
        et_city.setSelection(et_city.getText().length());

        tv_data.setText(weather.getDate());
        tv_condition.setText(firstUpperCase(weather.getCondition()));
        tv_temperature.setText(weather.getTemperature() + "°");
        tv_analysis.setText(weather.analyzeWeather());
        Picasso.get()
                .load(weather.getPosterWeather())
                .fit()
                .centerInside()
                .into(iv_poster_weight);

        saveWeatherFromSharedPreference(weather);
    }
    public String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";//или return word;
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private void saveWeatherFromSharedPreference(Weather weather) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("city", weather.getCity());
        editor.putString("condition", weather.getCondition());
        editor.putString("temperature", weather.getTemperature());
        editor.putString("poster", weather.getPosterWeather());
        editor.putString("date", weather.getDate());
        editor.apply();
    }
}
