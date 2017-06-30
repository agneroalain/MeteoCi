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


            prevision.jour.setTextColor((Color.parseColor("#FFFFFF")));
            prevision.temp_min.setTextColor((Color.parseColor("#FFFFFF")));
            prevision.temp_max.setTextColor((Color.parseColor("#FFFFFF")));
            prevision.clouds.setTextColor((Color.parseColor("#FFFFFF")));
            prevision.libelle.setTextColor((Color.parseColor("#FFFFFF")));

            Prevision item = data.get(position);

            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            if(hour > 18 || hour < 6){

                switch (item.getIcon()){
                    case "04n" :
                        prevision.icone.setImageResource(R.drawable.c13);
                        break;
                    case "10n" :
                        prevision.icone.setImageResource(R.drawable.n12);
                        break;
                    case "03n" :
                        prevision.icone.setImageResource(R.drawable.c13);
                        break;

                    case "09n" :
                        prevision.icone.setImageResource(R.drawable.c14);
                        break;

                    case "11n" :
                        prevision.icone.setImageResource(R.drawable.orage);
                        break;

                    default:
                        prevision.icone.setImageResource(R.drawable.j1);
                        break;
                }
            }
            else{

                prevision.jour.setText(convertDate());
                prevision.libelle.setText(item.getLibelle());
                prevision.temp_max.setText(String.valueOf("Temperature maximale : " + item.getTemp_max()+"°"));
                prevision.temp_min.setText(String.valueOf("Temperature minimale : " + item.getTemp_min() + "°"));
                prevision.clouds.setText(String.valueOf("Couvert à " + item.getClouds() + "%"));

                switch (item.getIcon()){
                    case "04d" :
                        prevision.icone.setImageResource(R.drawable.c13);
                    break;
                    case "10d" :
                        prevision.icone.setImageResource(R.drawable.j6);
                        break;
                        case "03d" :
                        prevision.icone.setImageResource(R.drawable.c13);
                            break;

                    case "09d" :
                        prevision.icone.setImageResource(R.drawable.c14);
                        break;

                    case "11d" :
                        prevision.icone.setImageResource(R.drawable.orage);
                        break;

                        default:
                            prevision.icone.setImageResource(R.drawable.j1);
                            break;
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

    public String convertDate(){
        //calcul de la date
        Date dateObj = new Date();
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
