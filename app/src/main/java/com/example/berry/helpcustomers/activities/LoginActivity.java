package com.example.berry.helpcustomers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.api.RetrofitClient;
import com.example.berry.helpcustomers.models.DefaultResponse;
import com.example.berry.helpcustomers.models.LoginResponse;
import com.example.berry.helpcustomers.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // initialize variables
    private EditText editTextEmail;
    private EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // assign variables to respective objects
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        // find button and register objects and set click listeners
        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // determine if user is currently logged in - if yes, go to ProfileActivity
        if(SharedPrefManager.getInstance(this).isLoggedIn()){

            // initialize intent to ProfileActivity
            Intent intent= new Intent(this, ProfileActivity.class);

            // set ProfileActivity as next task and clear  current activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // start ProfileActivity
            startActivity(intent);

        }
    }

    private void userLogin(){

        // getText from email and password fields, convert to string, trim spaces from begining
        // and ends then assign to respective variables
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // determine if email is empty - if yes, display error
        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        // determine if user input is in email format - if yes, display error
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        // determine if password is empty - if yes, display error
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        // determine if password length is greater than 6 characters - if yes, display error
        if(password.length() < 6){
            editTextPassword.setError("Password needs to be at least 6 characters");
            editTextPassword.requestFocus();
            return;

        }

        // initialize Call - <LoginResponse> initializes the response array
        Call<LoginResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(email, password);

        // call Retrofit API
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                // convert response to body and cast to LoginResponse class
                LoginResponse loginResponse = response.body();
                Log.e("LoginResponse", String.valueOf(loginResponse));
                // determine if there is an error
                if (response.code() == 202) {

                    // call SharedPrefManager from current instance - get user from loginResponse
                    // by calling getUser method from LoginResponse.java class then call saveUser
                    // from SharePrefManager.java class
                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(loginResponse.getUser());

                    // initialize and assign intent to start ProfileActivity
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);

                    // set flag that ProfileActivity is next and set flag to clear current activity
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    // start ProfileActivity
                    startActivity(intent);

                }else if(response.code() == 401){
                    Toast.makeText(LoginActivity.this, "An error occurred",
                            Toast.LENGTH_LONG).show();
                }

                // else executes if an error occurred - display toast
                else if(response.code() == 402){
                    Toast.makeText(LoginActivity.this, "Incorrect password.",
                            Toast.LENGTH_LONG).show();
                }else if(response.code() == 403){
                    Toast.makeText(LoginActivity.this, "Incorrect email.",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "There was an error.",
                            Toast.LENGTH_LONG).show();
                }
            }

            // if the API call fails log error and display error toast
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("API fail1", t.getMessage());
                Log.e("API fail2", String.valueOf(t));

                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){

            // if buttonLogin is clicked run userLogin
            case R.id.buttonLogin:
                userLogin();
                break;

            // if register link is clicked take user to MainActivity
            case R.id.textViewRegister:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }
}
