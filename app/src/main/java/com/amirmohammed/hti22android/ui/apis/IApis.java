package com.amirmohammed.hti22android.ui.apis;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApis {
    // https://newsapi.org/v2/top-headlines?country=eg&category=business&apiKey=fa72aea7f1af46a6a45be8aa23e21b64
    // baseUrl -> https://newsapi.org/
    // endPoint -> v2/top-headlines?country=eg&category=business&apiKey=fa72aea7f1af46a6a45be8aa23e21b64

    @GET("v2/top-headlines?country=eg&category=business&apiKey=fa72aea7f1af46a6a45be8aa23e21b64")
    Call<NewsResponse> getNews();

}
