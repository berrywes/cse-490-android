package com.example.berry.helpcustomers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

public class ProductInfoFragment extends Fragment {

    private int product_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product_id = getArguments().getInt("ID");
    }
}
