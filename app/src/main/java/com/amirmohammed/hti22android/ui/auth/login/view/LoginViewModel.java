package com.amirmohammed.hti22android.ui.auth.login.view;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amirmohammed.hti22android.ui.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

   private final MutableLiveData<Void> loginSuccessState = new MutableLiveData<>();
   private final MutableLiveData<String> loginFailureState = new MutableLiveData<>();


    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> loginSuccessState.postValue(null))
                .addOnFailureListener(e -> loginFailureState.setValue(e.getLocalizedMessage()));
    }

    LiveData<Void> getLoginSuccessState(){
        return loginSuccessState;
    }

    LiveData<String> getLoginFailureState(){
        return loginFailureState;
    }


}
