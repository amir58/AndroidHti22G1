package com.amirmohammed.hti22android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.databinding.ActivityCalculateBinding;
import com.google.android.material.textfield.TextInputEditText;

public class CalculateActivity extends AppCompatActivity {

    ActivityCalculateBinding binding;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);

        String strNumberOne = sharedPreferences.getString("strNumberOne","");
        binding.etNumberOne.setText(strNumberOne);

        String strNumberTwo = sharedPreferences.getString("strNumberTwo","");
        binding.etNumberTwo.setText(strNumberTwo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String strNumberOne = binding.etNumberOne.getText().toString();
        sharedPreferences.edit().putString("strNumberOne", strNumberOne).apply();

        String strNumberTwo = binding.etNumberTwo.getText().toString();
        sharedPreferences.edit().putString("strNumberTwo", strNumberTwo).apply();

    }

    public void sum(View view) {
        String strNumberOne = binding.etNumberOne.getText().toString();

        String strNumberTwo = binding.etNumberTwo.getText().toString();

        double numberOne = Double.parseDouble(strNumberOne);
        double numberTwo = Double.parseDouble(strNumberTwo);

        double result = numberOne + numberTwo;

        binding.tvResult.setText("Result : " + result);

        binding.etNumberOne.setText("");
        binding.etNumberTwo.setText("");
    }

    public void sub(View view) {

    }

    public void mul(View view) {

    }


    public void div(View view) {

    }
}