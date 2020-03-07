package com.example.calendarevents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {
    public List<Events> eventsList;

    public recyclerAdapter(List<Events> eventsList) {
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.eventDate.setText(eventsList.get(position).getEventDate());
        holder.eventName.setText(eventsList.get(position).getEventName());
        /*String type = eventsList.get(position).getEventType();
        if (type.equals("Goal") && type!=null){
            holder.eventType.setImageAlpha(R.drawable.blackgoal);
        }else if (type.equals("Reminder")  && type!=null){
            holder.eventType.setImageAlpha(R.drawable.alarm);
        }else if (type.equals("Event")  && type!=null){
            holder.eventType.setImageAlpha(R.drawable.delivery_date);
        }*/
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView eventDate, eventName;
        ImageView eventType;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            eventDate = itemView.findViewById(R.id.EventDate);
            eventType = itemView.findViewById(R.id.EventImage);
            eventName = itemView.findViewById(R.id.EventName);
        }
    }
}
