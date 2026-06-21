package com.example.a1221240_courseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class EventDetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_CATEGORY = "category";
    private static final String ARG_DATE = "date";
    private static final String ARG_TIME = "time";
    private static final String ARG_LOCATION = "location";
    private static final String ARG_SEATS = "seats";

    String title, description, category, date, time, location;
    int seats;

    TextView textViewDetailTitle;
    TextView textViewDetailDescription;
    TextView textViewDetailCategory;
    TextView textViewDetailDate;
    TextView textViewDetailTime;
    TextView textViewDetailLocation;
    TextView textViewDetailSeats;
    Button buttonDetailJoin;
    Button buttonDetailFavorite;

    public static EventDetailFragment newInstance(String title, String description,
                                                  String category, String date, String time, String location, int seats) {
        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        args.putString(ARG_CATEGORY, category);
        args.putString(ARG_DATE, date);
        args.putString(ARG_TIME, time);
        args.putString(ARG_LOCATION, location);
        args.putInt(ARG_SEATS, seats);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);

        textViewDetailTitle = (TextView) view.findViewById(R.id.textViewDetailTitle);
        textViewDetailDescription = (TextView) view.findViewById(R.id.textViewDetailDescription);
        textViewDetailCategory = (TextView) view.findViewById(R.id.textViewDetailCategory);
        textViewDetailDate = (TextView) view.findViewById(R.id.textViewDetailDate);
        textViewDetailTime = (TextView) view.findViewById(R.id.textViewDetailTime);
        textViewDetailLocation = (TextView) view.findViewById(R.id.textViewDetailLocation);
        textViewDetailSeats = (TextView) view.findViewById(R.id.textViewDetailSeats);
        buttonDetailJoin = (Button) view.findViewById(R.id.buttonDetailJoin);
        buttonDetailFavorite = (Button) view.findViewById(R.id.buttonDetailFavorite);

        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESCRIPTION);
            category = getArguments().getString(ARG_CATEGORY);
            date = getArguments().getString(ARG_DATE);
            time = getArguments().getString(ARG_TIME);
            location = getArguments().getString(ARG_LOCATION);
            seats = getArguments().getInt(ARG_SEATS);
        }

        textViewDetailTitle.setText(title);
        textViewDetailDescription.setText("Description: " + description);
        textViewDetailCategory.setText("Category: " + category);
        textViewDetailDate.setText("Date: " + date);
        textViewDetailTime.setText("Time: " + time);
        textViewDetailLocation.setText("Location: " + location);
        textViewDetailSeats.setText("Available Seats: " + seats);

        buttonDetailJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), JoinEventActivity.class);
                intent.putExtra("eventName", title);
                startActivity(intent);
            }
        });

        buttonDetailFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Favorite favorite = new Favorite();
                favorite.setmFavoriteId(Favorite.favoritesArrayList.size() + 1);
                favorite.setmEventName(title);
                favorite.setmCategory(category);
                favorite.setmDate(date);
                Favorite.favoritesArrayList.add(favorite);
                Toast.makeText(getActivity(),
                        title + " added to favorites",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}