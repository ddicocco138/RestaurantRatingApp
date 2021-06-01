package com.example.dicoccohw3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class restaurantAdapter extends RecyclerView.Adapter<restaurantAdapter.MyViewHolder> {





        public List<restaurant> restaurantList;

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView restaurantNameRecycler;
            public TextView restaurantLocationRecycler;
            public RatingBar restaurantRatingRecycler;
            public MyViewHolder(View view) {
                super(view);
                restaurantNameRecycler =  view.findViewById(R.id.recyclerName);
                restaurantLocationRecycler = view.findViewById(R.id.recyclerLocation);
                restaurantRatingRecycler = view.findViewById(R.id.recyclerRating);

            }
        }

        public restaurantAdapter(List<restaurant> restaurantList) {
            this.restaurantList = restaurantList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.restaurant_recycler_view, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            restaurant restaurant = restaurantList.get(position);
            String restaurantName = restaurant.getName();
            String restaurantLocation = restaurant.getLocation();
            int restaurantRating = restaurant.getRating();
            holder.restaurantNameRecycler.setText(restaurantName);
            holder.restaurantLocationRecycler.setText(restaurantLocation);
            holder.restaurantRatingRecycler.setRating(restaurantRating);

        }

        @Override
        public int getItemCount() {
            return restaurantList.size();
        }

        public void setData(List<restaurant> restaurantList) {
            this.restaurantList = restaurantList;
            notifyDataSetChanged();
        }

    }


