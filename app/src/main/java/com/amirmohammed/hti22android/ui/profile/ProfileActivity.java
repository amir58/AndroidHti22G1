package com.amirmohammed.hti22android.ui.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.databinding.ActivityProfileBinding;
import com.amirmohammed.hti22android.ui.auth.login.model.User;
import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    ActivityProfileBinding binding;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    EditText editTextUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getUser();

        Glide.with(this).load(R.drawable.profile).circleCrop().into(binding.ivProfilePicture);

        binding.ivProfilePicture.setOnClickListener(view -> selectImage());

        binding.btnUpdate.setOnClickListener(view -> updateUser());
    }

    private void getUser() {
        firestore.collection("androidHti22Users")
                .document(firebaseAuth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User user = documentSnapshot.toObject(User.class);

                        updateUi(user);
                        Log.i(TAG, "onSuccess: " + user.toString());
                    }
                });
    }

    private void updateUi(User user){
        Glide.with(this).load(user.getImageUrl()).circleCrop().into(binding.ivProfilePicture);

        binding.etUsername.setText(user.getUsername());
        binding.etPhone.setText(user.getPhone());
        binding.etEmail.setText(user.getEmail());
        binding.etGender.setText(user.getGender());
    }

    private void updateUser(){
        Map<String, Object> userData= new HashMap<>();
        userData.put("username", binding.etUsername.getText().toString());
        userData.put("phone", binding.etPhone.getText().toString());
        userData.put("email", binding.etEmail.getText().toString());
        userData.put("gender", binding.etGender.getText().toString());

        firestore.collection("androidHti22Users")
                .document(firebaseAuth.getUid())
                .update(userData)
                .addOnSuccessListener(runnable -> Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show());
    }


    private void selectImage() {
        ImagePicker.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();

            Glide.with(this).load(uri).circleCrop().into(binding.ivProfilePicture);
            uploadUserPicture(uri);

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadUserPicture(Uri imageUri) {
        storageReference.child("AndroidHti22").child(firebaseAuth.getUid()).putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    Toast.makeText(ProfileActivity.this, "Image uploaded", Toast.LENGTH_SHORT).show();
                    getUserPictureUrl();

                });

    }

    private void getUserPictureUrl() {
        storageReference.child("AndroidHti22").child(firebaseAuth.getUid()).getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String imageUrl = uri.toString();
                        Log.i(TAG, "onSuccess: imageURL => " + imageUrl);

                        saveImageUrlInFirestore(imageUrl);
                    }
                });
    }

    private void saveImageUrlInFirestore(String imageUrl) {
        Map<String, Object> map = new HashMap<>();
        map.put("imageUrl", imageUrl);

        firestore.collection("androidHti22Users")
                .document(firebaseAuth.getUid())
                .update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(ProfileActivity.this, "Image updated", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}

