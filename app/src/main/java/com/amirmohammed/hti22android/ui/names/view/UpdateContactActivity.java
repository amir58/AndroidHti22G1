package com.amirmohammed.hti22android.ui.names.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateContactActivity extends AppCompatActivity {
    TextInputEditText editTextName, editTextPhone;

    String contactName;
    String contactPhone;
    int contactPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        editTextName = findViewById(R.id.etUpdateContactName);
        editTextPhone = findViewById(R.id.etUpdateContactPhone);

        contactName = getIntent().getStringExtra("contactName");
        contactPhone = getIntent().getStringExtra("contactPhone");
        contactPosition = getIntent().getIntExtra("contactPosition", -1);

        editTextName.setText(contactName);
        editTextPhone.setText(contactPhone);

    }

    public void updateContact(View view) {
        String contactName = editTextName.getText().toString();

        if (contactName.isEmpty()) {
            Toast.makeText(this, "Please write contact name!", Toast.LENGTH_SHORT).show();
            return;
        }

        String contactPhone = editTextPhone.getText().toString();

        if (contactPhone.isEmpty()) {
            Toast.makeText(this, "Please write contact phone!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("contactName", contactName);
        intent.putExtra("contactPhone", contactPhone);
        intent.putExtra("contactPosition", contactPosition);

        setResult(RESULT_OK, intent);

        finish();
    }
}