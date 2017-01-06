package com.example.jere.garbageapp.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jere.garbageapp.Fragments.BackgroundTasks;
import com.example.jere.garbageapp.R;
import com.example.jere.garbageapp.libraries.Events;

import java.util.List;

/**
 * Created by jere on 1/6/2017.
 */

public class EventsViewAdapter extends RecyclerView.Adapter<EventsViewAdapter.ViewHolder>  {

    Context context;

    List<Events> getDataAdapter;

    public EventsViewAdapter(List<Events> getDataAdapter, Context context){

        super();

        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_events_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Events event =  getDataAdapter.get(position);

        holder.evt_name.setText(event.getEvent_name());
       //holder.evt_id.setText(String.valueOf(event.getEvent_id()));
        holder.evt_desc.setText(event.getEvent_description());
        holder.evt_venue.setText(event.getVenue());

        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context,"Item at position:" +position ,Toast.LENGTH_LONG).show();

                StringBuilder dataString = new StringBuilder();
                String e_name=event.getEvent_name();
                String e_desc=event.getEvent_description();
                String e_venue=event.getVenue();

                dataString.append(" Event Description : " + e_desc + "\n\n");
                dataString.append(" Event venue : " + e_venue);


                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_SUBJECT, "Event Name : " + e_name);
                i.putExtra(Intent.EXTRA_EMAIL, new String[] {"recipient@example.com"});
                i.putExtra(Intent.EXTRA_TEXT, dataString.toString());

                try{

                    context.startActivity(Intent.createChooser(i, "Send mail..."));

                }catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "Please install email client before sending",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        holder.paricipate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e_id=String.valueOf(event.getEvent_id());
                String e_name=event.getEvent_name();
                String e_desc=event.getEvent_description();
                String e_venue=event.getVenue();
                String function="participate";
                String user_id="1";

                BackgroundTasks backgroundTasks=new BackgroundTasks(context);
                backgroundTasks.execute(function,user_id,e_id);
            }
        });

    }



    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView evt_name;
        public TextView evt_desc;
        public TextView evt_venue;
        private String evt_id;
        public DatePicker evt_date;
        public FloatingActionButton fab,paricipate;



        public ViewHolder(View itemView) {

            super(itemView);
            evt_name= (TextView) itemView.findViewById(R.id.events_row_event_name) ;
            evt_desc = (TextView) itemView.findViewById(R.id.events_row_event_description) ;
            evt_venue= (TextView) itemView.findViewById(R.id.events_row_venue) ;
            fab=(FloatingActionButton)itemView.findViewById(R.id.fragment_home_events_row_fab);
            paricipate=(FloatingActionButton)itemView.findViewById(R.id.fragment_home_events_row_fab_participate);
            evt_date = (DatePicker) itemView.findViewById(R.id.events_row_event_date) ;

        }
    }
}
