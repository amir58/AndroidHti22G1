package com.amirmohammed.hti22android.ui.names;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.amirmohammed.hti22android.R;
import com.amirmohammed.hti22android.models.Company;
import com.amirmohammed.hti22android.models.MyContact;
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

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    List<MyContact> names = new ArrayList<>();
    NamesAdapter namesAdapter; // Make adapter as a global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);

        names = ContactsDatabase.init(NamesActivity.this)
                .contactsDao().getContacts();

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

        RecyclerView recyclerView = findViewById(R.id.rvNames);

        namesAdapter = new NamesAdapter(names, iUpdateContact);

        recyclerView.setAdapter(namesAdapter);

//        getNamesFromFirebase();
    }

    void getNamesFromFirebase() {
        firestore.collection("htiContacts").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot query) {
                        for (DocumentSnapshot document : query.getDocuments()) {
                            MyContact myContact = document.toObject(MyContact.class);
                            names.add(myContact);
                        }
                        namesAdapter.notifyDataSetChanged();
                    }
                });
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

            Company company = new Company("SeniorSteps", "0111");

            MyContact myContact = new MyContact(contactName, contactPhone, company);

            names.add(myContact);

            namesAdapter.notifyDataSetChanged();

            long contactId = ContactsDatabase.init(NamesActivity.this)
                    .contactsDao().insertContact(myContact);

            myContact.setId((int) contactId);

            firestore.collection("htiContacts")
                    .document(String.valueOf(contactId))
                    .set(myContact)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.i("NamesActivity", "onSuccess: ");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("NamesActivity", "onFailure: " + e);
                        }
                    });

        } else if (requestCode == 6 && resultCode == RESULT_OK && data != null) {
            String contactName = data.getStringExtra("contactName");
            String contactPhone = data.getStringExtra("contactPhone");
            int contactPosition = data.getIntExtra("contactPosition", -1);

            MyContact myContact = names.get(contactPosition);
            myContact.setName(contactName);
            myContact.setPhone(contactPhone);

            names.remove(contactPosition);
            names.add(contactPosition, myContact);

            namesAdapter.notifyItemChanged(contactPosition);

            ContactsDatabase.init(NamesActivity.this)
                    .contactsDao().updateContact(myContact);

            Map<String, Object> map = new HashMap<>();
            map.put("name", contactName);
            map.put("phone", contactPhone);

            firestore.collection("htiContacts")
                    .document(String.valueOf(myContact.getId()))
                    .update(map);
        }


    }
}