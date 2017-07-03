package com.bravaafreeca.meteoci;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.content.res.ResourcesCompat;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link aujourdhuiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link aujourdhuiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class aujourdhuiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static TextView ville_textview;
    public static TextView temp_textview;
    public static TextView desc_textview;
    public static TextView windSpeed_textview;
    public static TextView precipitation_textview;
    public static TextView conseil;
    public static TextView humidite;
    public static ImageView image;
    public  static FrameLayout background;

    private OnFragmentInteractionListener mListener;

    public aujourdhuiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment aujourdhuiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static aujourdhuiFragment newInstance(String param1, String param2) {
        aujourdhuiFragment fragment = new aujourdhuiFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_aujourdhui,container,false);


        ville_textview = (TextView) rootView.findViewById(R.id.textView_Ville);
        temp_textview = (TextView) rootView.findViewById(R.id.textView_Temp);
        desc_textview = (TextView) rootView.findViewById(R.id.textView_description);
        windSpeed_textview = (TextView) rootView.findViewById(R.id.textView_windSpeed);
        precipitation_textview = (TextView) rootView.findViewById(R.id.textView_precipitation);
        image = (ImageView) rootView.findViewById(R.id.image);
        conseil = (TextView) rootView.findViewById(R.id.conseil);
        humidite = (TextView) rootView.findViewById(R.id.humidite);


        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        background = (FrameLayout) rootView.findViewById(R.id.bck_today);
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
            }
            conseil.setTextColor(Color.parseColor("#FFFFFF"));
        }


            DownloadTask task = new DownloadTask();
                task.execute("http://api.openweathermap.org/data/2.5/weather?lat=5.3096600&lon=-4.0126600&appid=f2df44ac14f938f5a4ad68434f12d383&lang=fr&units=metric");

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
}
