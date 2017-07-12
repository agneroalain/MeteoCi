package com.bravaafreeca.meteoci;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragmentDemain.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragmentDemain#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentDemain extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static View rootView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static TextView ville_textview_demain;
    public static TextView temp_textview_demain;
    public static TextView desc_textview_demain;
    public static TextView pa_demain;
    public static TextView vv_demain;
    public static TextView humidity_demain;



    public static ImageView image_demain;
    public  static FrameLayout background_demain;

    private OnFragmentInteractionListener mListener;

    public fragmentDemain() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentDemain.
     */
    // TODO: Rename and change types and number of parameters
    public static fragmentDemain newInstance(String param1, String param2) {
        fragmentDemain fragment = new fragmentDemain();
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
        rootView = inflater.inflate(R.layout.fragment_fragment_demain,container,false);


        ville_textview_demain = (TextView) rootView.findViewById(R.id.textView_Ville_Demain);
        pa_demain = (TextView) rootView.findViewById(R.id.pa_demain);
        humidity_demain = (TextView) rootView.findViewById(R.id.humidity_demain);
        vv_demain = (TextView) rootView.findViewById(R.id.vv_demain);
        temp_textview_demain = (TextView) rootView.findViewById(R.id.textView_TempDemain);
        desc_textview_demain = (TextView) rootView.findViewById(R.id.textView_descriptionDemain);
        image_demain = (ImageView) rootView.findViewById(R.id.image_demain);


        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        background_demain = (FrameLayout) rootView.findViewById(R.id.bck_demain);
        if(hour > 6 && hour < 12 ){
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                background_demain.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.abidjan2));
            }
            //background.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.abidjan2, null));
        }
        else if(hour > 12 && hour < 18 ){
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                background_demain.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.abidjan));
            }
        }
        else{
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                background_demain.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.abidjan3));
            }
        }


        DownloadDemain task_demain = new DownloadDemain();
        task_demain.execute("http://api.openweathermap.org/data/2.5/forecast/daily?lat="+ MainActivity.LAT +"&lon="+ MainActivity.LNG +"&cnt=10&appid=f2df44ac14f938f5a4ad68434f12d383&lang=fr&units=metric");

        return rootView;
        // Inflate the layout for this fragment
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
