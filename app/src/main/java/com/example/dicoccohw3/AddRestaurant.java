package com.example.dicoccohw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRestaurant extends AppCompatActivity {

    private Button addButton;
    private EditText resName;
    private EditText resLocation;
    private RatingBar resRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myAddIntent = new Intent (AddRestaurant.this, Rating.class);
        setContentView(R.layout.activity_add_restaurant);

        addButton = findViewById(R.id.addButton);
        resName = findViewById(R.id.editTextResName);
        resLocation = findViewById(R.id.editTextResLocation);
        resRating = findViewById(R.id.resRating);



        FirebaseFirestore db = FirebaseFirestore.getInstance();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newResName = resName.getText().toString();
                String newResLocation = resLocation.getText().toString();
                int newResRating = (int) Math.round( resRating.getRating());

                Map<String, Object> restaurant = new HashMap<>();
                restaurant.put("Name", newResName);
                restaurant.put("Location", newResLocation);
                restaurant.put("Rating", newResRating);







// Add a new document with a generated ID
                db.collection("Restaurants")
                        .add(restaurant)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"DocumentSnapshot added with ID: " + documentReference.getId(),Toast.LENGTH_LONG).show();
                                startActivity(myAddIntent);

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Error adding document",Toast.LENGTH_LONG).show();
                            }
                        });

            }


        });
    }
}