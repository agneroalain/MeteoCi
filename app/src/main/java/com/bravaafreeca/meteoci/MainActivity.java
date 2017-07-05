package com.bravaafreeca.meteoci;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String CURRENT_TAG = "principal";
    ListView listmenu;
    ArrayList<String> listStringMenu = new ArrayList<String>();
    ArrayList<Localisation> localisationMenu=new ArrayList<Localisation>();
    DrawerLayout drawer;
    //static TextView title_textview;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recuperation de la date




        Fragment fragment = getfragmentPrincipal();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();






//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

// chargement des ville dans le menu
        loadMenu();
        listmenu=(ListView) findViewById(R.id.list_view_inside_nav);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, this.listStringMenu);
        listmenu.setAdapter(adapter);
        listmenu.setTextFilterEnabled(true);

        listmenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                Toast.makeText(getApplication(),"Source de donnée manquante",Toast.LENGTH_LONG).show();

                drawer.closeDrawers();
            }
        });

        Button btnpos=(Button)findViewById(R.id.maposition);
        btnpos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(),"Données position manquantes",Toast.LENGTH_LONG).show();
                drawer.closeDrawers();
            }
        });
        //filter de rechercher un ville dans la liste
        EditText myFilter = (EditText) findViewById(R.id.filter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }
        });

        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private Fragment getfragmentPrincipal() {
        fragmentPrincipal principal = new fragmentPrincipal();
        return principal;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.map_action) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   /* public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }*/

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        AppIndex.AppIndexApi.end(client, getIndexApiAction());
//        client.disconnect();
    }
//    public ArrayList<Localisation> loadLocalisation() throws IOException, JSONException {
//       final Type REVIEW_TYPE = new TypeToken<ArrayList<Localisation>>() {}.getType();
//        JSONResourceReader jr =new JSONResourceReader(getResources(),R.raw.location);
//        return new Gson().fromJson(jr.getString(),REVIEW_TYPE);
//    }
    //fonction de chagement du menu (list des villes)
    public void loadMenu()
    {
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.location);
        Scanner scanner =new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while(scanner.hasNextLine())
        {
            builder.append(scanner.nextLine());
        }
        parserJson(builder.toString());
    }
    //fonction de chagement du menu (recuperation des donné dans le fichier json)

    public void parserJson(String json)
    {
        try {
            StringBuilder builder =new StringBuilder();
            JSONObject root = new JSONObject(json);
            JSONArray villes= root.getJSONArray("ville");
            for (int i=0;i<villes.length();i++)
            {
                Localisation locat =new Localisation(villes.getJSONObject(i).getInt("locId"),villes.getJSONObject(i).getString("city"),villes.getJSONObject(i).getDouble("latitude"),villes.getJSONObject(i).getDouble("longitude"));
                this.localisationMenu.add(locat);
                this.listStringMenu.add(villes.getJSONObject(i).getString("city"));
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
//    public class MenuAdapter extends ArrayAdapter<Localisation>
//    {
//        Context context;
//        int layoutResourceId;
//        ArrayList<Localisation> data = null;
//        TextView city;
//
//        public MenuAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Localisation> objects) {
//            super(context, resource, objects);
//            this.layoutResourceId = resource;
//            this.context = context;
//            this.data = (ArrayList) objects;
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//            View row = convertView;
//            Localisation locat = null;
//        }
//    }

}

