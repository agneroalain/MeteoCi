package com.bravaafreeca.meteoci;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nnbbbbbbbnn on 12/07/2017.
 */

public class DownloadDemain extends AsyncTask<String,Void,String> {
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
            JSONObject list = jsonObject.getJSONArray("list").getJSONObject(1);
            String temperatureInt = String.valueOf(list.getJSONObject("temp").getDouble("day"));
            String description = String.valueOf(list.getJSONArray("weather").getJSONObject(0).getString("description"));
            String pressure = String.valueOf(list.getDouble("pressure"));
            String speed = String.valueOf(list.getDouble("speed"));
            String humidity = String.valueOf(list.getInt("humidity"));



            fragmentDemain.temp_textview_demain.setText(String.valueOf(temperatureInt + "°"));
            fragmentDemain.desc_textview_demain.setText(description);
            fragmentDemain.pa_demain.setText(String.valueOf("Pression atmosphérique : " +pressure + "hPa"));
            fragmentDemain.vv_demain.setText(String.valueOf("Vitesse du vent : " + speed + "m/s"));
            fragmentDemain.humidity_demain.setText("humidité : " +humidity  + "%");
            String icon = (String) String.valueOf(list.getJSONArray("weather").getJSONObject(0).getString("icon"));
            switch (icon){
                case "01n" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.moon);
                    break;
                case "02n" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.n2);
                    break;
                case "03n" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.n2);
                    break;
                case "04n" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.d4);
                    break;
                case "09n" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.n9);
                    break;
                case "10n" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.n10);
                    break;
                case "50d" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.d50);
                    break;
                case "11d" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.orage);
                    break;
                case "01d" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.sun);
                    break;
                case "02d" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.d2);
                    break;
                case "03d" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.d3);
                    break;
                case "04d" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.d4);
                    break;
                case "09d" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.d9);
                    break;
                case "10d" :
                    fragmentDemain.image_demain.setImageResource(R.drawable.d10);
                    break;
                default: fragmentDemain.image_demain.setImageResource(R.drawable.sun);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
