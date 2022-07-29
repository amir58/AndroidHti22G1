package com.amirmohammed.hti22android.ui.apis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amirmohammed.hti22android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IApis iApis = retrofit.create(IApis.class);

        iApis.getNews().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                System.out.println(response.body().getArticles().size());

                for (ArticlesItem article : response.body().getArticles()) {
                    System.out.println("- - - - - - - - - - -");
                    System.out.println(article.getTitle());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                System.out.println("Apis => "+ t.getLocalizedMessage());
            }
        });

    }
}