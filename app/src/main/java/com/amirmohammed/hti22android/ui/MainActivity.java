package com.amirmohammed.hti22android.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.models.Company;
import com.amirmohammed.hti22android.models.MyContact;
import com.amirmohammed.hti22android.ui.apis.NewsActivity;
import com.amirmohammed.hti22android.ui.auth.LoginActivity;
import com.amirmohammed.hti22android.ui.fragments.MainFActivity;
import com.amirmohammed.hti22android.ui.names.NamesActivity;
import com.amirmohammed.hti22android.ui.profile.ProfileActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFcmTokenAndUpdateIt();

//        startActivity(new Intent(this, LayoutsActivity.class));
//        finish();

//        arrayListOfObjects();
//        saveObject();

        getUserData();
    }


    void getUserData() {
        firestore.collection("androidHti22Users")
                .document(firebaseAuth.getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String username = documentSnapshot.getString("username");
                        String email = documentSnapshot.getString("email");
                        String gender = documentSnapshot.getString("gender");
                        String phone = documentSnapshot.getString("phone");

                        Log.i(TAG, "onSuccess: " + username);
                        Log.i(TAG, "onSuccess: " + email);
                        Log.i(TAG, "onSuccess: " + gender);
                        Log.i(TAG, "onSuccess: " + phone);
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show());

    }


    private void saveObject() {
        Company company = new Company("SeniorSteps", "0111");
        MyContact myContact = new MyContact("Ahmed", "010", company);
        // {"name":"Ahmed", "phone":"010"}

        Gson gson = new Gson();
        String myContactJson = gson.toJson(myContact);

        SharedPreferences sharedPreferences =
                getSharedPreferences("userData", MODE_PRIVATE);

        sharedPreferences.edit()
                .putString("myContact", myContactJson).apply();
    }

    private void arrayList() {
        // String[] names = new String[10];
        // String[] names = {"",""};

        // ArrayList<DataType> varName = new ArrayList();
        ArrayList<String> names = new ArrayList<>();

        // CRUD Operations => Create , Read , Update , Delete

        names.add("Ali");
        names.add("Ahmed");
        names.add("Mohammed");

//        names.addAll();

        System.out.println(names.get(0));

        for (String name : names) {
            System.out.println(name);
        }

        names.clear();

//        names.remove("Ahmed");
//        names.remove(0);

        names.add(0, "Sayed"); // update

        names.contains("Ali");

        names.indexOf("Ali");

        names.isEmpty();

//        names.size() > 0;

        names.size();

    }

    private void arrayListOfObjects() {
        // ArrayList<ClassName> varName = new ArrayList();
        ArrayList<MyContact> myContacts = new ArrayList<>();
        Company company = new Company("SeniorSteps", "0111");

        MyContact myContact1 = new MyContact("Ali", "010", company);
        myContacts.add(myContact1);

        MyContact myContact2 = new MyContact("Mahmoud", "011", company);
        myContacts.add(myContact2);

        // 0 -> Ali
        // 1 -> Ahmed
        // 2 -> Mohammed

        // 0 -> {"name":"Ali", "phone":"010"}
        // 1 -> {"name":"Mahmoud", "phone":"011"}

        // user_id

        System.out.println("- - - - - -");
        System.out.println(myContacts.get(0).getName());
        System.out.println(myContacts.get(0).getPhone());
        System.out.println("- - - - - -");

        myContacts.get(1).printContactData();
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

    public void navigateToMenusActivity(View view) {
        Intent intent = new Intent(MainActivity.this, MenusActivity.class);
        startActivity(intent);
    }

    public void showAlertDialog(View view) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Something wrong contact with developer")
                .setCancelable(false)
                .show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Are you sure to exit from app")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void navigateToNamesActivity(View view) {
        Intent intent = new Intent(MainActivity.this, NamesActivity.class);
        startActivity(intent);
    }

    public void navigateToNewsActivity(View view) {
        Intent intent = new Intent(MainActivity.this, NewsActivity.class);
        startActivity(intent);
    }

    public void navigateToFragmentsActivity(View view) {
        Intent intent = new Intent(MainActivity.this, MainFActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void navigateToProfileActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }


    private void getFcmTokenAndUpdateIt() {
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String token) {
                        Log.i(TAG, "onSuccess: " + token);

                        Map<String, Object> map = new HashMap<>();
                        map.put("fcmToken", token);

                        FirebaseFirestore.getInstance()
                                .collection("androidHti22Users")
                                .document(FirebaseAuth.getInstance().getUid())
                                .update(map);

                    }
                });
    }

}