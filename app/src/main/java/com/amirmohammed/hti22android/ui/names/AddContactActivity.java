package com.amirmohammed.hti22android.ui.names;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amirmohammed.hti22android.R;
import com.google.android.material.textfield.TextInputEditText;

public class AddContactActivity extends AppCompatActivity {

    TextInputEditText editTextName, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editTextName = findViewById(R.id.etContactName);
        editTextPhone = findViewById(R.id.etContactPhone);

    }

    public void addContact(View view) {
        String contactName = editTextName.getText().toString();

        if (contactName.isEmpty()){
            Toast.makeText(this, "Please write contact name!", Toast.LENGTH_SHORT).show();
            return;
        }

        String contactPhone = editTextPhone.getText().toString();

        if (contactPhone.isEmpty()){
            Toast.makeText(this, "Please write contact phone!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("contactName", contactName);
        intent.putExtra("contactPhone", contactPhone);

        setResult(RESULT_OK, intent);

        finish();
    }

}