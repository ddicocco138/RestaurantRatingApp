package com.example.dicoccohw3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

public class FilterRating extends AppCompatActivity {

    private RatingBar filterRatingBar;
    private Button filterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_rating);

        filterRatingBar = findViewById(R.id.filterRatingBar);
        filterButton = findViewById(R.id.filterButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int filterRating = Math.round(filterRatingBar.getRating());

                Intent intent = new Intent(getBaseContext(), Rating.class);
                intent.putExtra("filterRating", filterRating);
                startActivity(intent);
            }
        });
    }
}