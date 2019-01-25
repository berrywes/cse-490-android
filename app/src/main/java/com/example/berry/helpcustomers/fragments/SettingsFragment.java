package com.example.berry.helpcustomers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.api.RetrofitClient;
import com.example.berry.helpcustomers.models.LoginResponse;
import com.example.berry.helpcustomers.models.User;
import com.example.berry.helpcustomers.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingsFragment extends Fragment implements View.OnClickListener {

    private EditText editTextEmail, editTextName;
    private EditText editTextCurrentPassword, editTextNewPassword;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextName = view.findViewById(R.id.editTextName);
        editTextCurrentPassword = view.findViewById(R.id.editTextCurrentPassword);
        editTextNewPassword = view.findViewById(R.id.editTextNewPassword);

        view.findViewById(R.id.buttonSave).setOnClickListener(this);
        view.findViewById(R.id.buttonChangePassword).setOnClickListener(this);
        view.findViewById(R.id.buttonLogout).setOnClickListener(this);
        view.findViewById(R.id.buttonDelete).setOnClickListener(this);

    }

    private void updateProfile() {

        String email = editTextEmail.getText().toString().trim();
        String name = editTextName.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (name.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }


        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        Call<LoginResponse> call = RetrofitClient.getInstance()
                .getApi().updateUser(
                        user.getId(),
                        email,
                        name
                );

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                Log.e("Setting Error", "Response made");

                if (response.code() == 200) {
                    SharedPrefManager.getInstance(getActivity()).saveUser(response.body().getUser());
                    Toast.makeText(getActivity().getApplicationContext(),response.body().getMessage(), Toast.LENGTH_LONG).show();

                }
                else if (response.code() == 401) {
                    Toast.makeText(getActivity().getApplicationContext(), "An error occurred.",
                            Toast.LENGTH_LONG).show();
                }
                else if (response.code() == 403) {
                    Toast.makeText(getActivity().getApplicationContext(), "This email is taken.",
                            Toast.LENGTH_LONG).show();
                }
                else if (response.code() == 401) {
                    Toast.makeText(getActivity().getApplicationContext(), "An error occurred.",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Sorry there was an error. Please try again later.",
                        Toast.LENGTH_LONG).show();


            }
        });
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSave:
                updateProfile();
                break;
            case R.id.buttonChangePassword:
                break;
            case R.id.buttonLogout:
                break;
            case R.id.buttonDelete:
                break;
        }
    }
}
