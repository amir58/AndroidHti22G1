package com.amirmohammed.hti22android.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    ActivityRegisterBinding binding;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(view -> getDataFromUi());
    }

    private void getDataFromUi() {
        String email = binding.etEmail.getText().toString();

        if (email.isEmpty()) {
            Toast.makeText(this, "email required", Toast.LENGTH_LONG).show();
            return;
        }

        String password = binding.etPassword.getText().toString();

        if (password.isEmpty()) {
            Toast.makeText(this, "password required", Toast.LENGTH_LONG).show();
            return;
        }

        createNewAccount(email, password);
    }

    private void createNewAccount(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(RegisterActivity.this, "Account created", Toast.LENGTH_SHORT).show();
                        addUserNameToUser(authResult.getUser());
                        addUserToFirestore(authResult.getUser());
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onFailure: ", e);
                    }
                });
    }

    private void addUserToFirestore(FirebaseUser user) {
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("username", "Amir");
        userData.put("email", "amir@gmail.com");
        userData.put("gender", "male");
        userData.put("phone", "01116036522");

        firestore.collection("androidHti22Users")
                .document(user.getUid())
                .set(userData)
                .addOnSuccessListener(runnable -> Toast.makeText(this, "Firestore", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(exception -> Toast.makeText(this, exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show())
        ;

    }

    private void addUserNameToUser(FirebaseUser user) {
        String username = "username";

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(username)
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        } else {

                        }
                    }
                });

        firebaseAuth.signOut();
    }


}