package com.example.berry.helpcustomers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.api.RetrofitClient;
import com.example.berry.helpcustomers.models.AddProductResponse;
import com.example.berry.helpcustomers.models.DefaultResponse;
import com.example.berry.helpcustomers.models.User;
import com.example.berry.helpcustomers.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductFragment extends Fragment implements View.OnClickListener{
    private EditText editTextProductName, editTextProductCategory, editTextProductDescription,
            editTextProductLocation, editTextProductPrice, editTextAvailability;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.addproduct_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextProductName = view.findViewById(R.id.editTextProductName);
        editTextProductCategory = view.findViewById(R.id.editTextProductCategory);
        editTextProductDescription = view.findViewById(R.id.editTextProductDescription);
        editTextProductLocation = view.findViewById(R.id.editTextProductLocation);
        editTextProductPrice = view.findViewById(R.id.editTextProductPrice);
        editTextAvailability = view.findViewById(R.id.editTextAvailability);


        view.findViewById(R.id.buttonAddProduct).setOnClickListener(this);
        view.findViewById(R.id.buttonCancelProduct).setOnClickListener(this);
    }

    private void addProduct() {
        String name = editTextProductName.getText().toString().trim();
        String category = editTextProductCategory.getText().toString().trim();
        String price = editTextProductPrice.getText().toString().trim();
        String description = editTextProductDescription.getText().toString().trim();
        String location = editTextProductLocation.getText().toString().trim();
        String status = editTextAvailability.getText().toString().trim();

        if (name.isEmpty()) {
            editTextProductName.setError("Name is required");
            editTextProductName.requestFocus();
            return;
        }
        if (category.isEmpty()) {
            editTextProductCategory.setError("Category is required");
            editTextProductCategory.requestFocus();
            return;
        }
        if (price.isEmpty()) {
            editTextProductPrice.setError("Price is required");
            editTextProductPrice.requestFocus();
            return;
        }
        if (description.isEmpty()) {
            editTextProductDescription.setError("Descripition is required");
            editTextProductDescription.requestFocus();
            return;
        }
        if (location.isEmpty()) {
            editTextProductLocation.setError("Location is required");
            editTextProductLocation.requestFocus();
            return;
        }
        if (status.isEmpty()) {
            editTextAvailability.setError("Availability is required");
            editTextAvailability.requestFocus();
            return;
        }

        User user = SharedPrefManager.getInstance(getActivity()).getUser();
        Log.e("PrefManager", String.valueOf(user.getId()));

        Call<DefaultResponse> call = RetrofitClient.getInstance()
                .getApi().createProduct(
                        user.getId(),
                        name,
                        category,
                        price,
                        description,
                        location,
                        status
                );

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                Log.e("AddProductError", String.valueOf(response));

                DefaultResponse dr = response.body();
                Log.e("AddProductError", String.valueOf(dr));

                if (response.code() == 201) {
                    Toast.makeText(getActivity().getApplicationContext(),"test1", Toast.LENGTH_LONG).show();

                } else if (response.code() == 402){
                    Toast.makeText(getActivity().getApplicationContext(),"test2", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, there was an error.", Toast.LENGTH_LONG).show();

                }

        }


        @Override
        public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonAddProduct:
                addProduct();
                break;
            case R.id.buttonCancelProduct:
                break;

        }
    }


}
