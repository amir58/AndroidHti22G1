package com.amirmohammed.hti22android.ui.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.databinding.ActivityLoginBinding;
import com.amirmohammed.hti22android.ui.MainActivity;
import com.amirmohammed.hti22android.ui.e.Login;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        binding.btnLogin.setOnClickListener(view -> getDataFromUi());
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

        login(email, password);
    }

    private void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                        Toast.makeText(LoginActivity.this, e.getLocalizedMessage()
                                , Toast.LENGTH_SHORT).show();
                    }
                });
    }


}