package com.amirmohammed.hti22android.ui.e;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EApis {

    @POST("login")
    Call<LoginResponse> login(@Body Login login);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);


    @GET("profile")
    Call<LoginResponse> getProfile(
            @Header("Authorization") String token,
            @Header("lang") String language
    );

    @PUT("update-profile")
    Call<LoginResponse> updateProfile(@Body RegisterRequest registerRequest);


    @POST("favorites")
    Call<Void> addOrDeleteInFavorite(
            @Field("product_id") int productId,
            @Header("Authorization") String token,
            @Header("lang") String language
    );

    @DELETE("favorites/{productId}")
    Call<Void> deleteFromFavorite(
            @Path("productId") int productId,
            @Header("Authorization") String token,
            @Header("lang") String language
    );

}
