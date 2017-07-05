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
            aujourdhuiFragment.desc_textview.setText(description);
            aujourdhuiFragment.pression.setText(String.valueOf("Pression atmosphérique : " + main.getString("pressure") + "hPa"));
            aujourdhuiFragment.windSpeed_textview.setText(String.valueOf("Vitesse du vent : " + speed + "m/s"));
            aujourdhuiFragment.ville_textview.setText(jsonObject.getString("name") + ", " + jsonObject.getJSONObject("sys").getString("country"));
            aujourdhuiFragment.humidite.setText("humidité : " + main.getString("humidity") + "%");
            String icon = (String) weather.getString("icon");
            switch (icon){
                case "01n" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.moon);
                break;
                case "02n" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.n2);
                    break;
                case "03n" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.n2);
                    break;
                case "04n" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.d4);
                    break;
                case "09n" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.n9);
                    break;
                case "10n" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.n10);
                    break;
                case "50d" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.d50);
                    break;
                case "11d" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.orage);
                    break;
                case "01d" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.sun);
                    break;
                case "02d" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.d2);
                    break;
                case "03d" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.d3);
                    break;
                case "04d" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.d4);
                    break;
                case "09d" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.d9);
                    break;
                case "10d" :
                    aujourdhuiFragment.image.setImageResource(R.drawable.d10);
                    break;
               default: aujourdhuiFragment.image.setImageResource(R.drawable.sun);
                   break;
            }

            Log.d("TEMPERATURE", String.valueOf(temperature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
