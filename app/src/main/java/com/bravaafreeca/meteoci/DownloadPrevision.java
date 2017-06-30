package com.bravaafreeca.meteoci;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nnbbbbbbbnn on 27/06/2017.
 */

@TargetApi(Build.VERSION_CODES.CUPCAKE)//????
public class DownloadPrevision extends AsyncTask<String,Void,String> {
    ArrayList<Prevision> prev_array = new ArrayList<Prevision>();

    @Override
    protected String doInBackground(String... urls) {
        String reponse = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while (data != -1) {
                char current = (char) data;
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
            JSONArray previsionArray = jsonObject.getJSONArray("list");
            JSONObject prevision = (JSONObject) previsionArray.get(0);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

