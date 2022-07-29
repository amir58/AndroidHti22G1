package com.amirmohammed.hti22android.ui.names;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.models.MyContact;

import java.util.ArrayList;

// Data : ArrayList
// CustomItem -> layout
// Adapter -> bind data on ui
// NamesActivity -> java code , ui
public class NamesActivity extends AppCompatActivity {

    ArrayList<MyContact> names = new ArrayList<>();
    NamesAdapter namesAdapter; // Make adapter as a global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        names.add(new MyContact("Amir", "01116036522"));
        names.add(new MyContact("Ali", "01030936083"));
        names.add(new MyContact("Ahmed", "01256052932"));
        names.add(new MyContact("Amir", "01116036522"));
        names.add(new MyContact("Ali", "01030936083"));
        names.add(new MyContact("Ahmed", "01256052932"));
        names.add(new MyContact("Amir", "01116036522"));
        names.add(new MyContact("Ali", "01030936083"));
        names.add(new MyContact("Ahmed", "01256052932"));
        names.add(new MyContact("Amir", "01116036522"));
        names.add(new MyContact("Ali", "01030936083"));
        names.add(new MyContact("Ahmed", "01256052932"));

        RecyclerView recyclerView = findViewById(R.id.rvNames);

        namesAdapter = new NamesAdapter(names, iUpdateContact);

        recyclerView.setAdapter(namesAdapter);

    }

    IUpdateContact iUpdateContact = new IUpdateContact() {
        @Override
        public void onUpdateContactClick(String contactName, String contactPhone, int contactPosition) {
            Intent intent = new Intent(NamesActivity.this, UpdateContactActivity.class);
            intent.putExtra("contactName", contactName);
            intent.putExtra("contactPhone", contactPhone);
            intent.putExtra("contactPosition", contactPosition);
            startActivityForResult(intent, 6);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(NamesActivity.this, AddContactActivity.class);
        startActivityForResult(intent, 5);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("onActivityResult");

        if (requestCode == 5 && resultCode == RESULT_OK && data != null) {
            String contactName = data.getStringExtra("contactName");
            String contactPhone = data.getStringExtra("contactPhone");

            MyContact myContact = new MyContact(contactName, contactPhone);

            names.add(myContact);

            namesAdapter.notifyDataSetChanged();

        } else if (requestCode == 6 && resultCode == RESULT_OK && data != null) {
            String contactName = data.getStringExtra("contactName");
            String contactPhone = data.getStringExtra("contactPhone");
            int contactPosition = data.getIntExtra("contactPosition", -1);

            MyContact myContact = new MyContact(contactName, contactPhone);

            names.add(contactPosition, myContact);

            namesAdapter.notifyItemChanged(contactPosition);
        }


    }
}