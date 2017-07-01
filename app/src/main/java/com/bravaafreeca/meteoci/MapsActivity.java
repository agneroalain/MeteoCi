package com.bravaafreeca.meteoci;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ArrayList<Localisation>Listville=new ArrayList<Localisation>();
        Listville=loadmap();
        // Add a marker in Sydney and move the camera
        for (Localisation item :Listville) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(item.getLatitude(), item.getLongitude())).title(item.getCity()));

        }
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public  ArrayList<Localisation> loadmap()
    {
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.location);
        Scanner scanner =new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while(scanner.hasNextLine())
        {
            builder.append(scanner.nextLine());
        }
        return parserJson(builder.toString());
    }
    //fonction de chagement du menu (recuperation des donné dans le fichier json)

    public  ArrayList<Localisation> parserJson(String json)
    {
        ArrayList<Localisation>localisationMenu=new ArrayList<Localisation>();
        try {
            StringBuilder builder =new StringBuilder();
            JSONObject root = new JSONObject(json);
            JSONArray villes= root.getJSONArray("ville");
            for (int i=0;i<villes.length();i++)
            {
                Localisation locat =new Localisation(villes.getJSONObject(i).getInt("locId"),villes.getJSONObject(i).getString("city"),villes.getJSONObject(i).getDouble("latitude"),villes.getJSONObject(i).getDouble("longitude"));

                localisationMenu.add(locat);
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    return localisationMenu;
    }
}
