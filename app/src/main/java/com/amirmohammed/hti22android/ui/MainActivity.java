package com.amirmohammed.hti22android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.amirmohammed.hti22android.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void navigateToCalculate(View view) {
        Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
        startActivity(intent);
    }

    public void navigateToIntentsActivity(View view) {
        Intent intent = new Intent(MainActivity.this, IntentsActivity.class);
        intent.putExtra("email", "ali@gmail.com");
        startActivity(intent);
    }

    public void navigateToLifecycleActivity(View view) {
        Intent intent = new Intent(MainActivity.this, LifecycleActivity.class);
        startActivity(intent);
    }
}