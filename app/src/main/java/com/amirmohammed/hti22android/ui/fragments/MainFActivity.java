package com.amirmohammed.hti22android.ui.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.amirmohammed.hti22android.R;

public class MainFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_factivity);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, new ChatsFragment())
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout2, new StatusFragment())
                .commit();


    }

    public void showChats(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, new ChatsFragment())
                .commit();

    }

    public void showStatus(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, new StatusFragment())
                .commit();
    }

    public void showCalls(View view) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, new CallsFragment())
                .commit();
    }


}