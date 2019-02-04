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
import android.widget.TextView;
import android.widget.Toast;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.api.RetrofitClient;
import com.example.berry.helpcustomers.models.DefaultResponse;
import com.example.berry.helpcustomers.models.Product;
import com.example.berry.helpcustomers.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProductFragment extends Fragment implements View.OnClickListener {
    private TextView textView;
    private int product_id;
    String name, category, price, description, location, status;
    private EditText editTextProductName, editTextProductCategory, editTextProductDescription,
            editTextProductLocation, editTextProductPrice, editTextAvailability;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product_id = getArguments().getInt("ID");
        Log.i("EditProduct", String.valueOf(product_id));
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.editproduct_fragment, container, false);
    }


    @Nullable
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextProductName = view.findViewById(R.id.editTextProductName);
        editTextProductCategory = view.findViewById(R.id.editTextProductCategory);
        editTextProductDescription = view.findViewById(R.id.editTextProductDescription);
        editTextProductLocation = view.findViewById(R.id.editTextProductLocation);
        editTextProductPrice = view.findViewById(R.id.editTextProductPrice);
        editTextAvailability = view.findViewById(R.id.editTextAvailability);


        view.findViewById(R.id.buttonEditProduct).setOnClickListener(this);
        view.findViewById(R.id.buttonCancelProduct).setOnClickListener(this);
        Log.i("GetProducts", "view created");
        Log.i("GetProducts", String.valueOf(product_id));

        Call<ProductResponse> call = RetrofitClient.getInstance()
                .getApi().getProduct(product_id);

        call.enqueue(new Callback<ProductResponse>(){
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.code() == 200) {
                    Log.e("GetProduct", String.valueOf(response));

                    String name = response.body().getProduct().getName();
                    String category = response.body().getProduct().getCategory();
                    String price = response.body().getProduct().getPrice();
                    String description = response.body().getProduct().getDescription();
                    String location = response.body().getProduct().getLocation();
                    String status = response.body().getProduct().getStatus();

                    Log.i("GetProducts", String.valueOf(name));


                    editTextProductName.setText(name);
                    editTextProductCategory.setText(category);
                    editTextProductDescription.setText(price);
                    editTextProductLocation.setText(description);
                    editTextProductPrice.setText(location);
                    editTextAvailability.setText(status);


                } else if (response.code() == 201){
                    Log.i("GetProducts", "201");

                } else if (response.code() == 401){
                    Log.i("GetProducts", "401");

                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.i("GetProducts", "failure");
                Log.i("GetProducts", String.valueOf(t));


            }
        });


    }
    private void displayFragment(Fragment fragment){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }
    @Override
    public void onClick(View v) {
        Fragment fragment = null;

        switch(v.getId()){
            case R.id.buttonEditProduct:
                editProduct();
                break;
            case R.id.buttonCancelProduct:
                fragment = new ProductsFragment();
                break;
            //case R.id.editProductButton:
            // break;
        }
        if(fragment!=null){
            displayFragment(fragment);
        }
    }

    private void editProduct() {
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

        Call<DefaultResponse> call = RetrofitClient.getInstance()
                .getApi().updateProduct(
                        product_id,
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
                Log.i("EditProductError", String.valueOf(response));

                DefaultResponse dr = response.body();
                Log.i("EditProductError", String.valueOf(dr));

                if (response.code() == 200) {
                    Toast.makeText(getActivity().getApplicationContext(),"Product edit successful.", Toast.LENGTH_LONG).show();
                    Fragment fragment = null;
                    fragment = new ProductsFragment();
                    displayFragment(fragment);

                } else if (response.code() == 401){
                    Toast.makeText(getActivity().getApplicationContext(),"Product could not be added.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(),"Sorry, there was an error.", Toast.LENGTH_LONG).show();

                }

            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Log.e("EditProductError", t.toString());

            }
        });



    }
}
