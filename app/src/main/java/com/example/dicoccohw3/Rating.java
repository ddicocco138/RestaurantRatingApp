package com.example.dicoccohw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Rating extends AppCompatActivity {

    private List<restaurant> restaurantList = new ArrayList<>();
    private RecyclerView recyclerView;
    private restaurantAdapter adapter;
    private int FilterRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.RestaurantRecyclerView);
        adapter = new restaurantAdapter(restaurantList);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        FilterRating = getIntent().getIntExtra("filterRating",0);



        db.collection("Restaurants")
                .whereGreaterThanOrEqualTo("Rating",FilterRating)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();
                            Toast.makeText(getApplicationContext(),"Query Made",Toast.LENGTH_LONG).show();
                            for (int i=0; i<myListOfDocuments.size(); i++) {
                                String resName = myListOfDocuments.get(i).get("Name").toString();
                                String resLocation = myListOfDocuments.get(i).get("Location").toString();
                                int resRating = Integer.parseInt(myListOfDocuments.get(i).get("Rating").toString());
                                restaurantList.add(new restaurant(resName,resLocation,resRating));
                                adapter.notifyDataSetChanged();
                            }



                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filterrating, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {
            case R.id.clear_filter_menu_item:

                restaurantList.clear();
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Restaurants")
                        .whereGreaterThanOrEqualTo("Rating", 0)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();
                                    for (int i = 0; i < myListOfDocuments.size(); i++) {
                                        String resName = myListOfDocuments.get(i).get("Name").toString();
                                        String resLocation = myListOfDocuments.get(i).get("Location").toString();
                                        int resRating = Integer.parseInt(myListOfDocuments.get(i).get("Rating").toString());
                                        restaurantList.add(new restaurant(resName, resLocation, resRating));
                                        adapter.notifyDataSetChanged();
                                    }


                                }
                            }
                        });
                return true;

            case R.id.app_info_menu_item:
// Code for help goes here…
                Intent f = new Intent(getApplicationContext(), About.class);
                startActivity(f);
                return true;

            case R.id.add_rest_menu_item:
// Code for help goes here…
                Intent i = new Intent(getApplicationContext(), AddRestaurant.class);
                startActivity(i);
                return true;
            case R.id.filter_rating_menu_item:
// Code for help goes here…
                Intent g = new Intent(getApplicationContext(), FilterRating.class);
                startActivity(g);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}