package com.bravaafreeca.meteoci;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * Created by nnbbbbbbbnn on 25/06/2017.
 */

public class DownloadTask extends AsyncTask<String,Void,String>{

    @Override
    protected  String doInBackground(String... urls){
        String reponse = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while(data != -1){
                char current = (char)data;
                reponse += current;
                data = reader.read();
            }

            return reponse;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String reponse) {
        super.onPostExecute(reponse);

        try {
            JSONObject jsonObject = new JSONObject(reponse);
            JSONObject main = new JSONObject(jsonObject.getString("main"));
            JSONObject wind = new JSONObject((jsonObject.getString("wind")));
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weather = (JSONObject) weatherArray.get(0);
            Log.d("VOIR","ok");
            double temperature = Double.parseDouble(main.getString("temp"));
            int temperatureInt = (int)(temperature);
            double speed = Double.parseDouble(wind.getString("speed"));
            String description = (String) weather.getString("description");
            aujourdhuiFragment.temp_textview.setText(String.valueOf(temperatureInt + "°"));
            aujourdhuiFragment.desc_textview.setText(getTranslateDesc(description));
            aujourdhuiFragment.precipitation_textview.setText(String.valueOf("precipitation : 100mm"));
            aujourdhuiFragment.windSpeed_textview.setText(String.valueOf("Vitesse du vent : " + speed));
            //String ville = jsonObject.getString("place");
            //Log.d("VILLE",ville);

            Log.d("TEMPERATURE", String.valueOf(temperature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected  String getTranslateDesc(String description){
        String translation = "";
        switch (description){
            case "clear sky":
                translation = "Ciel degagé";
                break;
            default:
                translation = "non definie";
        }
        return translation;
    }

}
