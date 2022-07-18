package com.amirmohammed.hti22android.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.amirmohammed.hti22android.R;
import com.google.android.material.textfield.TextInputEditText;

public class CalculateActivity extends AppCompatActivity {

    TextInputEditText etNumberOne;
    TextInputEditText etNumberTwo;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        etNumberOne = findViewById(R.id.etNumberOne);
        etNumberTwo = findViewById(R.id.etNumberTwo);
        tvResult = findViewById(R.id.tvResult);
    }

    public void sum(View view) {
        String strNumberOne = etNumberOne.getText().toString();

        String strNumberTwo = etNumberTwo.getText().toString();

        double numberOne = Double.parseDouble(strNumberOne);
        double numberTwo = Double.parseDouble(strNumberTwo);

        double result = numberOne + numberTwo;

        tvResult.setText("Result : " + result);
    }

    public void sub(View view) {

    }

    public void mul(View view) {

    }


    public void div(View view) {

    }
}