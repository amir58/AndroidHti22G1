package com.amirmohammed.hti22android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;

public class IntentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);

        if (getIntent().hasExtra("email")) {
            String email = getIntent().getStringExtra("email");

            System.out.println(email);
            System.out.println(email.isEmpty());
        }

    }

    public void openUrl(View view) {
//        Uri uri = Uri.parse("https://www.fb.com");
//        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        startActivity(intent);

        String url = "http://www.google.com";
        if (url.startsWith("https://") || url.startsWith("http://")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
        else {
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_LONG).show();
        }

    }

    public void shareText(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Welcome in our app");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "My Chooser");
        startActivity(shareIntent);
    }

    public void pickImage(View view) {
        Intent intent = new Intent(IntentsActivity.this, PickImageActivity.class);
        startActivity(intent);
    }
}