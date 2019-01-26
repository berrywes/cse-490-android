package com.example.berry.helpcustomers.api;

import com.example.berry.helpcustomers.models.DefaultResponse;
import com.example.berry.helpcustomers.models.LoginResponse;
import com.example.berry.helpcustomers.models.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name

    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("password") String name
            );


    @FormUrlEncoded
    @GET("getproducts/{id}/")
    Call<ProductsResponse> getProducts(
            @Path("id") int id
    );

    @FormUrlEncoded
    @POST("createproduct/")
    Call<DefaultResponse> createProduct(
            @Field("user_id") int id,
            @Field("name") String name,
            @Field("category") String category,
            @Field("price") String price,
            @Field("description") String description,
            @Field("location") String location,
            @Field("status") String status

            );

}
