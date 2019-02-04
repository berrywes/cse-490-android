package com.example.berry.helpcustomers.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.fragments.AddProductFragment;
import com.example.berry.helpcustomers.fragments.HomeFragment;
import com.example.berry.helpcustomers.fragments.ProductsFragment;
import com.example.berry.helpcustomers.fragments.SettingsFragment;
import com.example.berry.helpcustomers.models.User;
import com.example.berry.helpcustomers.storage.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // initialize bottom menu navigation
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomeFragment());
    }

    private void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch(menuItem.getItemId()){
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_products:
                fragment = new ProductsFragment();
                break;
            case R.id.menu_settings:
                fragment = new SettingsFragment();
                break;

        }
        if(fragment != null){
            displayFragment(fragment);
        }

        return false;
    }

    public void onClick(View v) {
        Fragment fragment = null;
        switch(v.getId()){
            case R.id.addProductButton:
                fragment = new AddProductFragment();
                break;
        }
        if(fragment!=null){
            displayFragment(fragment);
        }
    }
}

