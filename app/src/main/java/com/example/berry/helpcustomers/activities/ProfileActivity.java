package com.example.berry.helpcustomers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.models.User;
import com.example.berry.helpcustomers.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // initialize textView
        textView = findViewById(R.id.textView);

        // call SharedPrefManager to get user and assign to user
        User user = SharedPrefManager.getInstance(this).getUser();

        // display welcome text and get name from user
        textView.setText("Welcome back " + user.getName());
    }
    @Override
    protected void onStart() {
        super.onStart();

        // if user is not logged in return to MainActivity
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {

            // initialize intent to go to MainActivity
            Intent intent = new Intent(this, MainActivity.class);

            // set flag for MainActivity to be next activity
            // and set flag to clear current activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // start MainActivity
            startActivity(intent);
        }
    }

    }

