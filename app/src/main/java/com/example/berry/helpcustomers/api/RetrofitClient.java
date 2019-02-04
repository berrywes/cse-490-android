package com.example.berry.helpcustomers.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // initalize variables - assign BASE_URL to slim framework application location URL
    private static final String BASE_URL = "http://192.168.1.2/cse-490-php/public/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();

        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
