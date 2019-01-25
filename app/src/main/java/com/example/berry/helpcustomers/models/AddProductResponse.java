package com.example.berry.helpcustomers.models;

public class AddProductResponse {
    private boolean error;
    private String message;

    public AddProductResponse(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
