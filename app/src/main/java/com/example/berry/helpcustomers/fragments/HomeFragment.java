package com.example.berry.helpcustomers.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.activities.CustomerModeActivity;
import com.example.berry.helpcustomers.activities.MainActivity;
import com.example.berry.helpcustomers.activities.ProfileActivity;
import com.example.berry.helpcustomers.storage.SharedPrefManager;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private TextView textViewEmail, textViewName;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewName = view.findViewById(R.id.textViewNameUser);

        view.findViewById(R.id.customerModeButton).setOnClickListener(this);

        textViewEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());
        textViewName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());



    }

    public void onClick(View v) {

        switch(v.getId()){

            case R.id.customerModeButton:
                Intent intent = new Intent(getActivity(), CustomerModeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

        }
    }
}
