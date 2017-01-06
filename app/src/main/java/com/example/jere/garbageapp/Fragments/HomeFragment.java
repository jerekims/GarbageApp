package com.example.jere.garbageapp.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jere.garbageapp.Adapters.EventsViewAdapter;
import com.example.jere.garbageapp.R;
import com.example.jere.garbageapp.libraries.Constants;
import com.example.jere.garbageapp.libraries.Events;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView recyclerView;
    List<Events> GetEvents;
    RecyclerView.Adapter recyclerViewadapter;
    ProgressBar progressBar;
    FloatingActionButton fab;

    String JSON_EVENT_ID = "event_id";
    String JSON_EVENT_NAME = "event_name";
    String JSON_DESC = "Event Description";
    String JSON_VENUE = "Venue";
    String JSON_DATE="event_date";

    //String JSON_PHONE_NUMBER = "phone_number";

    JsonArrayRequest jsonArrayRequest ;

    RequestQueue requestQueue ;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);
        myrecycler(view);
        return view;
    }

    public void myrecycler(View view){
        GetEvents = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_home_recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.fragment_home_progressbar);
        fab=(FloatingActionButton)view.findViewById(R.id.fragment_home_events_row_fab);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        JSON_DATA_WEB_CALL();
        progressBar.setVisibility(View.VISIBLE);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    public void JSON_DATA_WEB_CALL(){
        jsonArrayRequest = new JsonArrayRequest(Constants.GET_EVENTS,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        progressBar.setVisibility(View.GONE);

                        JSON_PARSE_DATA_AFTER_WEBCALL(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(getContext());

        requestQueue.add(jsonArrayRequest);
    }

    public void JSON_PARSE_DATA_AFTER_WEBCALL(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            Events myevents = new Events();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);

                //myevents.setEvent_id(json.getInt(JSON_EVENT_ID));

                myevents.setEvent_name(json.getString(JSON_EVENT_NAME));
                myevents.setEvent_description(json.getString(JSON_DESC));
                myevents.setVenue(json.getString(JSON_VENUE));
               // myevents.setEvent_date(json.getString(JSON_DATE));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            GetEvents.add(myevents);
        }

        recyclerViewadapter = new EventsViewAdapter(GetEvents,getContext());

        recyclerView.setAdapter(recyclerViewadapter);
    }

}
