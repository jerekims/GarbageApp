package com.example.jere.garbageapp.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.jere.garbageapp.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComplainFragment extends Fragment {
    private EditText description, wtype,mlocation;
    AppCompatButton bntreport;
    private ProgressBar progress;
    private SharedPreferences pref;
    private MaterialBetterSpinner wastetype;

    private Spinner location,estate;

    public ComplainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_complain, container, false);
            description=(EditText)view.findViewById(R.id.fragment_complain_description);
        description.setHint("Complain description");
//        location=(Spinner) view.findViewById(R.id.fragment_complain_location);
//        estate=(Spinner)view.findViewById(R.id.fragment_complain_estate);
        wastetype=(MaterialBetterSpinner)view.findViewById(R.id.fragment_complain_wastetype);

        description.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus)
                    description.setHint("");
                else
                    description.setHint("Complain description");
            }
        });

//        List<String> mylocations= new ArrayList<>();
//        mylocations.add("Langata");
//        mylocations.add("Nairobi West");
//        mylocations.add("StrathMore");
//        mylocations.add("HighRise");
//
//        ArrayAdapter<String> locationadapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylocations);
//        locationadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        location.setAdapter(locationadapter);
//
//        List<String> myestates = new ArrayList<>();
//        myestates.add("Funguo");
//        myestates.add("Akila");
//        myestates.add("Airport View");
//        myestates.add("Blue Sky");
//        myestates.add("Siwaka Estate");
//
//        ArrayAdapter<String> estateadapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, myestates);
//        estateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // estate.setAdapter(estateadapter);

//        String[] mywaste = {"Biodegradable", "Plastics", "Metaallic"};

        List<String> mywaste = new ArrayList<>();
        mywaste.add("Biodegradable");
        mywaste.add("Plastics");
        mywaste.add("Metaallics");

        ArrayAdapter<String> wasteadapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mywaste);
        wasteadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wastetype.setAdapter(wasteadapter);
        // Inflate the layout for this fragment
        return view;
    }
}
