package com.example.berry.helpcustomers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.adapters.ProductsAdapter;
import com.example.berry.helpcustomers.api.RetrofitClient;
import com.example.berry.helpcustomers.models.Product;
import com.example.berry.helpcustomers.models.ProductsResponse;
import com.example.berry.helpcustomers.models.User;
import com.example.berry.helpcustomers.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductsFragment extends Fragment implements View.OnClickListener  {

    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private List<Product> productList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.products_fragment, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        User user = SharedPrefManager.getInstance(getActivity()).getUser();


        view.findViewById(R.id.addProductButton).setOnClickListener(this);


        Call<ProductsResponse> call =RetrofitClient.getInstance().getApi().getProducts(user.getId());

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                Log.e("Product Error", "Products Response made");
                if(response.code() == 200) {
                    Log.e("Product Error", "200 passed");

                    productList = response.body().getProducts();
                    adapter = new ProductsAdapter(getActivity(), productList);
                    recyclerView.setAdapter(adapter);
                }else{
                    Log.e("Product Error", "200 not passed");

                    Toast.makeText(getActivity().getApplicationContext(),"Test", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.e("Product Error", "Response failed");

            }
        });
    }

    @Override
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

    private void displayFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }
}
