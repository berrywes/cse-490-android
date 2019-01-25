package com.example.berry.helpcustomers.models;

import java.util.List;

public class ProductsResponse {

    private boolean error;
    private List<Product> products;

    public ProductsResponse(boolean error, List<Product> products) {
        this.error = error;
        this.products = products;
    }

    public boolean isError() {
        return error;
    }

    public List<Product> getProducts() {
        return products;
    }
}
