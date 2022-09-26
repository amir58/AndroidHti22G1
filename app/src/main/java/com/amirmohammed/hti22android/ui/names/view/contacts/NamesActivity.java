package com.amirmohammed.hti22android.ui.names.view.contacts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.models.Company;
import com.amirmohammed.hti22android.ui.names.db.ContactsDatabase;
import com.amirmohammed.hti22android.ui.names.model.MyContact;
import com.amirmohammed.hti22android.ui.names.adapters.NamesAdapter;
import com.amirmohammed.hti22android.ui.names.view.AddContactActivity;
import com.amirmohammed.hti22android.ui.names.view.IUpdateContact;
import com.amirmohammed.hti22android.ui.names.view.UpdateContactActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Data : ArrayList
// CustomItem -> layout
// Adapter -> bind data on ui
// NamesActivity -> java code , ui

public class NamesActivity extends AppCompatActivity {
    private final int ADD_CONTACT_REQUEST_CODE = 5;

    NamesAdapter namesAdapter; // Make adapter as a global variable
    List<MyContact> names = new ArrayList<>();
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    ContactsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        viewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.rvNames);

        viewModel.getContactsFromRoomDB(NamesActivity.this)
                .observe(this, new Observer<List<MyContact>>() {
                    @Override
                    public void onChanged(List<MyContact> myContacts) {
                        names = myContacts;
                        namesAdapter = new NamesAdapter(names, iUpdateContact);
                        recyclerView.setAdapter(namesAdapter);
                    }
                });


        viewModel.getContactChangedState().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer contactPosition) {
                namesAdapter.notifyItemChanged(contactPosition);
            }
        });

//        names.add(new MyContact("Amir", "01116036522"));
//        names.add(new MyContact("Ali", "01030936083"));
//        names.add(new MyContact("Ahmed", "01256052932"));
//        names.add(new MyContact("Amir", "01116036522"));
//        names.add(new MyContact("Ali", "01030936083"));
//        names.add(new MyContact("Ahmed", "01256052932"));
//        names.add(new MyContact("Amir", "01116036522"));
//        names.add(new MyContact("Ali", "01030936083"));
//        names.add(new MyContact("Ahmed", "01256052932"));
//        names.add(new MyContact("Amir", "01116036522"));
//        names.add(new MyContact("Ali", "01030936083"));
//        names.add(new MyContact("Ahmed", "01256052932"));


//        getNamesFromFirebase();

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

        if (requestCode == ADD_CONTACT_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String contactName = data.getStringExtra("contactName");
            String contactPhone = data.getStringExtra("contactPhone");

            Company company = new Company("SeniorSteps", "0111");

            MyContact myContact = new MyContact(contactName, contactPhone, company);

            viewModel.addNewContact(myContact);


        } else if (requestCode == 6 && resultCode == RESULT_OK && data != null) {
            String contactName = data.getStringExtra("contactName");
            String contactPhone = data.getStringExtra("contactPhone");
            int contactPosition = data.getIntExtra("contactPosition", -1);

            MyContact myContact = names.get(contactPosition);
            myContact.setName(contactName);
            myContact.setPhone(contactPhone);

            viewModel.updateContact(contactPosition, myContact);
        }


    }
}