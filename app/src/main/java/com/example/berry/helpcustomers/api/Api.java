package com.example.berry.helpcustomers.api;

import com.example.berry.helpcustomers.models.AddProductResponse;
import com.example.berry.helpcustomers.models.DefaultResponse;
import com.example.berry.helpcustomers.models.LoginResponse;
import com.example.berry.helpcustomers.models.Product;
import com.example.berry.helpcustomers.models.ProductResponse;
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
    @POST("createuser/")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name

    );

    @FormUrlEncoded
    @POST("userlogin/")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("updateuser/{id}/")
    Call<LoginResponse> updateUser(
        @Path("id") int id,
        @Field("email") String email,
        @Field("name") String name

    );

    @FormUrlEncoded
    @PUT("updatepassword/")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email

    );

    @GET("getproducts/{id}/")
    Call<ProductsResponse> getProducts(
            @Path("id") int id);

    @GET("getproduct/{id}/")
    Call<ProductResponse> getProduct(
            @Path("id") int id);


    @FormUrlEncoded
    @POST("createproduct/")
    Call<DefaultResponse> createProduct(
            @Field("user_id") int user_id,
            @Field("name") String name,
            @Field("category") String category,
            @Field("price") String price,
            @Field("description") String description,
            @Field("location") String location,
            @Field("status") String status
    );

    @FormUrlEncoded
    @PUT("updateproduct/{id}/")
    Call<DefaultResponse> updateProduct(
            @Path("id") int id,
            @Field("name") String name,
            @Field("category") String category,
            @Field("price") String price,
            @Field("description") String description,
            @Field("location") String location,
            @Field("status") String status
    );


    @GET("searchproducts/{user_id}/{query}/")
    Call<ProductsResponse> searchProducts(
            @Path("user_id") int user_id,
            @Path("query") String query
    );


}
