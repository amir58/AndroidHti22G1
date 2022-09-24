package com.amirmohammed.hti22android.ui.auth.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.amirmohammed.hti22android.databinding.ActivityLoginBinding;
import com.amirmohammed.hti22android.ui.MainActivity;
import com.amirmohammed.hti22android.ui.auth.RegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

// MVVM => Model, View, ViewModel
// Model => User
// View => LoginActivity
// ViewModel => LoginViewModel => database and logic

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    ActivityLoginBinding binding;
    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);


        binding.btnRegister.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));

        binding.btnLogin.setOnClickListener(view -> getDataFromUi());

        loginObservers();
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

        viewModel.login(email, password);
    }

    private void loginObservers() {
        viewModel.loginSuccessState.observe(this, unused -> onLoginSuccess());

        viewModel.loginFailureState.observe(this, errorMessage -> onLoginFailure(errorMessage));
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void onLoginFailure(String errorMessage) {
        Log.e(TAG, "onFailure: " + errorMessage);
        Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
    }


}