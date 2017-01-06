package com.example.jere.garbageapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public void onBindViewHolder(ViewHolder holder, int position) {

        Events event =  getDataAdapter.get(position);

        holder.evt_name.setText(event.getEvent_name());
        //holder.IdTextView.setText(String.valueOf(getDataAdapter1.getId()));
        holder.evt_desc.setText(event.getEvent_description());
        holder.evt_venue.setText(event.getVenue());
        //holder.evt_date.setText(event.getEvent_date());

    }

    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView evt_name;
        public TextView evt_desc;
        public TextView evt_venue;
        public TextView evt_date;


        public ViewHolder(View itemView) {

            super(itemView);
            evt_name= (TextView) itemView.findViewById(R.id.events_row_event_name) ;
            evt_desc = (TextView) itemView.findViewById(R.id.events_row_event_description) ;
            evt_venue= (TextView) itemView.findViewById(R.id.events_row_venue) ;
            //evt_date = (TextView) itemView.findViewById(R.id.events_row_date) ;

        }
    }
}
