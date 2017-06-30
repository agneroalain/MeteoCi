package com.bravaafreeca.meteoci;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by nnbbbbbbbnn on 29/06/2017.
 */

public class customAdapter extends ArrayAdapter<Prevision> {
    Context context;
    int layoutResourceId;
    ArrayList<Prevision> data = null;
    private static TextView jour;
    private static TextView temp_min;
    private static TextView temp_max;
    private static TextView libelle;
    private static TextView clouds;
    private static ImageView icone;

    public customAdapter(Context context, int resource, List<Prevision> objects) {
        super(context, resource, objects);
        this.layoutResourceId = resource;
        this.context = context;
        this.data = (ArrayList) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PrevisionHolder prevision = null;


        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            prevision = new PrevisionHolder();
            prevision.jour = (TextView) row.findViewById(R.id.jour);
            prevision.libelle = (TextView)row.findViewById(R.id.libelle);
            prevision.temp_max = (TextView)row.findViewById(R.id.temp_max);
            prevision.temp_min = (TextView)row.findViewById(R.id.temp_min);
            prevision.clouds = (TextView)row.findViewById(R.id.clouds);
            prevision.icone = (ImageView) row.findViewById(R.id.icone_temp);

            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            if(hour > 18 || hour < 6){
                    prevision.jour.setTextColor((Color.parseColor("#FFFFFF")));
                    prevision.temp_min.setTextColor((Color.parseColor("#FFFFFF")));
                    prevision.temp_max.setTextColor((Color.parseColor("#FFFFFF")));
                    prevision.clouds.setTextColor((Color.parseColor("#FFFFFF")));
                    prevision.libelle.setTextColor((Color.parseColor("#FFFFFF")));

            }
            else{
                Prevision item = data.get(position);
                prevision.jour.setText(convertDate(item.getJour()));
                prevision.libelle.setText(item.getLibelle());
                prevision.temp_max.setText(String.valueOf("Temperature maximale : " + item.getTemp_max()+"°"));
                prevision.temp_min.setText(String.valueOf("Temperature minimale : " + item.getTemp_min() + "°"));
                prevision.clouds.setText(String.valueOf("Couvert à " + item.getClouds() + "%"));

                switch (item.getLibelle()){
                    case "nuageux" :
                        if(item.getClouds() < 20) {
                            prevision.icone.setImageResource(R.drawable.j1);
                        }
                        else if(item.getClouds() < 40){
                            prevision.icone.setImageResource(R.drawable.j2);
                        }
                        else if(item.getClouds() < 60){
                            prevision.icone.setImageResource(R.drawable.j3);
                        }
                        else if(item.getClouds() < 80){
                            prevision.icone.setImageResource(R.drawable.j4);
                        }
                        else {
                            prevision.icone.setImageResource(R.drawable.c13);
                        }
                }
            }
            row.setTag(prevision);
        }
        else
        {
            prevision = (PrevisionHolder) row.getTag();
        }





        return row;
    }

    public String convertDate(int time){
        //calcul de la date
        Date dateObj = new Date(time);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String text = df.format(dateObj);

        return  text;
    }

    private class PrevisionHolder {
        public TextView jour;
        public TextView libelle;
        public TextView temp_max;
        public TextView temp_min;
        public TextView clouds;
        public ImageView icone;

    }
}
