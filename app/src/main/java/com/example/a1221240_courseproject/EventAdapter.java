package com.example.a1221240_courseproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    Context context;
    ArrayList<Event> eventsList;

    public EventAdapter(Context context, ArrayList<Event> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventsList.get(position);

        holder.textViewEventTitle.setText(event.getmTitle());
        holder.textViewEventCategory.setText("Category: " + event.getmCategory());
        holder.textViewEventDate.setText("Date: " + event.getmDate() + " at " + event.getmTime());
        holder.textViewEventLocation.setText("Location: " + event.getmLocation());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("title", event.getmTitle());
                intent.putExtra("description", event.getmDescription());
                intent.putExtra("category", event.getmCategory());
                intent.putExtra("date", event.getmDate());
                intent.putExtra("time", event.getmTime());
                intent.putExtra("location", event.getmLocation());
                intent.putExtra("seats", event.getmSeats());
                context.startActivity(intent);
            }
        });

        holder.buttonJoinEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JoinEventActivity.class);
                intent.putExtra("eventName", event.getmTitle());
                context.startActivity(intent);
            }
        });

        holder.buttonFavoriteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Favorite favorite = new Favorite();
                favorite.setmFavoriteId(Favorite.favoritesArrayList.size() + 1);
                favorite.setmEventName(event.getmTitle());
                favorite.setmCategory(event.getmCategory());
                favorite.setmDate(event.getmDate());
                Favorite.favoritesArrayList.add(favorite);
                Toast.makeText(context,
                        event.getmTitle() + " added to favorites",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {

        TextView textViewEventTitle;
        TextView textViewEventCategory;
        TextView textViewEventDate;
        TextView textViewEventLocation;
        Button buttonJoinEvent;
        Button buttonFavoriteEvent;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEventTitle = (TextView) itemView.findViewById(R.id.textViewEventTitle);
            textViewEventCategory = (TextView) itemView.findViewById(R.id.textViewEventCategory);
            textViewEventDate = (TextView) itemView.findViewById(R.id.textViewEventDate);
            textViewEventLocation = (TextView) itemView.findViewById(R.id.textViewEventLocation);
            buttonJoinEvent = (Button) itemView.findViewById(R.id.buttonJoinEvent);
            buttonFavoriteEvent = (Button) itemView.findViewById(R.id.buttonFavoriteEvent);
        }
    }
}