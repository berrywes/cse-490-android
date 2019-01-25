package com.example.berry.helpcustomers.models;

public class Product {

    // initialize variables
    private int product_id;
    private String name, category, price, description, location, status;

    // constructor method: parameters are response data from API user sub-array


    public Product(int product_id, String name, String category, String price, String description, String location, String status) {
        this.product_id = product_id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
        this.location = location;
        this.status = status;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
