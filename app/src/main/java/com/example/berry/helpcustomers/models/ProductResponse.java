package com.example.berry.helpcustomers.models;

import android.util.Log;

import java.util.List;

public class ProductResponse {

    // initialize variables
    private boolean error;
    private Product product;
    private String message;
    // constructor method: parameters are response data from API user sub-array


    public ProductResponse(boolean error, Product product, String message) {
        this.error = error;
        this.product = product;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public Product getProduct() {
        return product;
    }

    public String getMessage() {
        return message;
    }
}
