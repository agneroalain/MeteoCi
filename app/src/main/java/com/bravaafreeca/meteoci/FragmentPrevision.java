package com.bravaafreeca.meteoci;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import  com.bravaafreeca.meteoci.DownloadPrevision;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPrevision.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPrevision#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPrevision extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static View rootView;

    FrameLayout background;
    // TODO: Rename and change types of parameters
    ListView mListView;
    AsyncTask asyncTask;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentPrevision() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPrevision.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPrevision newInstance(String param1, String param2) {
        FragmentPrevision fragment = new FragmentPrevision();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_fragment_prevision,container,false);

        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        background = (FrameLayout) rootView.findViewById(R.id.bck_prevision);
        if(hour > 6 && hour < 12 ){
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                background.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.abidjan2));
            }
            //background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.abidjan2, null));
        }
        else if(hour > 12 && hour < 18 ){

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                background.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.abidjan));
            }
        }
        else{
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                background.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.abidjan3));
            }
        }
        DownloadPrevision getPrevision = new DownloadPrevision();
        getPrevision.execute("http://api.openweathermap.org/data/2.5/forecast/daily?lat=5.3096600&lon=-4.0126600&appid=f2df44ac14f938f5a4ad68434f12d383&lang=fr");
        chargerList();

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void chargerList(){

        ArrayList<Prevision> previsions = new ArrayList<Prevision>();
        asyncTask = new AsyncTask<String,Void,String>() {
            @Override
            protected String doInBackground(String... urls) {
                String reponse = "";
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?lat=5.3096600&lon=-4.0126600&appid=f2df44ac14f938f5a4ad68434f12d383&lang=fr&units=metric");
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
            protected void onPostExecute(String o) {
                super.onPostExecute(o);
                try {
                    JSONObject jsonObject = new JSONObject(o);
                    JSONArray previsionArray = jsonObject.getJSONArray("list");
                    ArrayList<Prevision> previsions = new ArrayList<Prevision>();
                    for(int i = 0; i< previsionArray.length(); i++){
                        Prevision prevision = new Prevision();
                        prevision.setJour(previsionArray.getJSONObject(i).getInt("dt"));
                        prevision.setLibelle(previsionArray.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description"));
                        prevision.setClouds(previsionArray.getJSONObject(i).getInt("clouds"));
                        prevision.setTemp_max(previsionArray.getJSONObject(i).getJSONObject("temp").getDouble("max"));
                        prevision.setTemp_max(previsionArray.getJSONObject(i).getJSONObject("temp").getDouble("min"));
                        previsions.add(prevision);
                    }

                    mListView = (ListView) getView().findViewById(R.id.listView);
                    ArrayAdapter<Prevision> itemsAdapter = new customAdapter(getContext(), R.layout.list_item_prevision, previsions);
                    mListView.setAdapter(itemsAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        };
        Object[] arg = new String[]{null,null,null};
        asyncTask.execute(arg);
    }


}
