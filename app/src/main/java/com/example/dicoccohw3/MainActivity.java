package com.example.dicoccohw3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private EditText usernameText;
    private EditText passwordText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.loginButton);
        usernameText = findViewById(R.id.editTextUsername);
        passwordText = findViewById(R.id.editTextTextPassword);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }

            private void loginUserAccount()
            {



                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                // validations for input email and password
                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                // signin existing user
                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(
                                            @NonNull Task<AuthResult> task)
                                    {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                                    .show();



                                            // if sign-in is successful
                                            // intent to home activity
                                            Intent intent
                                                    = new Intent(MainActivity.this,
                                                    Rating.class);
                                            startActivity(intent);
                                        }

                                        else {

                                            // sign-in failed
                                            Toast.makeText(getApplicationContext(),
                                                    "Login not successful!!",
                                                    Toast.LENGTH_LONG)
                                                    .show();


                                        }
                                    }
                                });
            }



        });








    }
}