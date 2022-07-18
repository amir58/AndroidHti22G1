package com.amirmohammed.hti22android.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;

public class PickImageActivity extends AppCompatActivity {

    ImageView imageView;
    private final int PICK_IMAGE_REQUEST_CODE = 1;
    private final int PICK_VIDEO_REQUEST_CODE = 2;

    private Uri uri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image);

        imageView = findViewById(R.id.ivPickImage);
    }

    public void pickImageFromGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }


    public void pickVideoFromGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(intent, PICK_VIDEO_REQUEST_CODE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            System.out.println(uri);

            imageView.setImageURI(uri);
        }

    }

    public void shareImage(View view) {
        if (uri == null){
            Toast.makeText(this, "Please select image!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_TEXT, "Test text with image");
        startActivity(Intent.createChooser(intent, "Share Image"));
    }
}