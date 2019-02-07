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

import com.example.berry.helpcustomers.R;
import com.example.berry.helpcustomers.adapters.ProductsAdapter;
import com.example.berry.helpcustomers.adapters.ResultsAdapter;
import com.example.berry.helpcustomers.api.RetrofitClient;
import com.example.berry.helpcustomers.interfaces.FragmentCommunication;
import com.example.berry.helpcustomers.models.Product;
import com.example.berry.helpcustomers.models.ProductsResponse;
import com.example.berry.helpcustomers.models.User;
import com.example.berry.helpcustomers.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultsFragment extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private ResultsAdapter adapter;
    private List<Product> productList;
    private int user_id;
    private String query;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        query = getArguments().getString("Query");
        User user = SharedPrefManager.getInstance(getActivity()).getUser();
        user_id = user.getId();
        Log.i("search info", String.valueOf(user_id));
        Log.i("search info", String.valueOf(query));

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_results_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Call<ProductsResponse> call =RetrofitClient.getInstance().getApi().searchProducts(user_id, query);

        call.enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                //Log.e("ProductError", "Products Response made");

                if(response.code() == 200) {
                    Log.e("Search results", "200 passed");
                    Log.e("Search results", String.valueOf(response.body()));

                    productList = response.body().getProducts();
                    adapter = new ResultsAdapter(getActivity(), productList, communication);
                    recyclerView.setAdapter(adapter);

                }else if (response.code() == 201){
                    Log.i("Search results", "201  passed");
                } else{
                    Log.i("Search results", "200 and 201 not passed");

                }

            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.i("Search results", "Response failed");

            }
        });
    }




    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.selectProductButton:
                fragment = new ProductInfoFragment();
                break;
        }
        if(fragment != null){
            displayFragment(fragment);
        }

    }

    private void displayFragment(Fragment fragment){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayoutCustomer, fragment)
                .commit();
    }

    FragmentCommunication communication = new FragmentCommunication() {
        @Override
        public void respond( int product_id) {

            ProductInfoFragment productInfoFragment = new ProductInfoFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("ID", product_id);
            productInfoFragment.setArguments(bundle);
            displayFragment(productInfoFragment);


        }
    };
}
